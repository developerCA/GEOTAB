package com.tecnolpet.ot.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Service;

import com.tecnolpet.ot.constant.SotApp;
import com.tecnolpet.ot.dto.CalibracionDto;
import com.tecnolpet.ot.model.Calibracion;
import com.tecnolpet.ot.model.Catalogo;
import com.tecnolpet.ot.model.Instrumento;
import com.tecnolpet.ot.model.PerfilEmpresa;
import com.tecnolpet.ot.repository.CalibracionRepository;
import com.tecnolpet.ot.repository.CatalogoRepository;
import com.tecnolpet.ot.repository.InstrumentoRepository;
import com.tecnolpet.ot.repository.PerfilEmpresaRepository;
import com.tecnolpet.ot.seguridad.UsuarioAuthenticate;
import com.tecnolpet.ot.utils.FechasUtils;

@Service
@PropertySources(value = { @PropertySource("classpath:/application.properties") })
public class CalibracionService {

	@Autowired
	private PerfilEmpresaRepository perfilEmpresaRepository;

	@Autowired
	private InstrumentoRepository instrumentoRepository;

	@Autowired
	private CatalogoRepository catalogoRepository;

	@Autowired
	private CalibracionRepository calibracionRepository;

	public List<Instrumento> traerInstrumentos(UsuarioAuthenticate usuario) {

		PerfilEmpresa perfilEmpresa = perfilEmpresaRepository.findOne(usuario
				.getPerfil_empresa());

		Catalogo estado = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosGenerales.ACTIVO).get(0);

		List<Instrumento> lista = instrumentoRepository
				.findByEmpresaAndCatalogo(perfilEmpresa.getEmpresa(), estado);

		return lista;
	}

	public void guardar(Instrumento instrumento, UsuarioAuthenticate usuario) {

		Catalogo estado = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosGenerales.ACTIVO).get(0);
		PerfilEmpresa perfilEmpresa = perfilEmpresaRepository.findOne(usuario
				.getPerfil_empresa());

		instrumento.setCatalogo(estado);
		instrumento.setEmpresa(perfilEmpresa.getEmpresa());
		instrumentoRepository.save(instrumento);
	}

	public void eliminar(Instrumento instrumento) {

		Catalogo estado = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosGenerales.ELIMINADO).get(0);
		Instrumento instumentoDel = instrumentoRepository.findOne(instrumento
				.getId());

		instumentoDel.setCatalogo(estado);
		instrumentoRepository.save(instumentoDel);
	}

	public List<CalibracionDto> traerCalibraciones(UsuarioAuthenticate usuario) {

		PerfilEmpresa perfilEmpresa = perfilEmpresaRepository.findOne(usuario
				.getPerfil_empresa());

		Catalogo estado = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosGenerales.ACTIVO).get(0);

		List<Calibracion> list = calibracionRepository.findCalibraciones(
				perfilEmpresa.getEmpresa(), estado);

		List<CalibracionDto> calibracionDtoList = new ArrayList<CalibracionDto>();

		for (Calibracion calibracion : list) {
			CalibracionDto calibracionDto = new CalibracionDto();
			calibracionDto.setCalibracion(calibracion);
			calibracionDto.setDias(FechasUtils.diferenciaEnDias(calibracion.getFechaCalibracionFutura(), calibracion.getFechaCalibracion()));
			calibracionDto.setDiasFaltantes(FechasUtils.diferenciaEnDias( calibracion.getFechaCalibracionFutura(),new Date()));
			calibracionDto.setDiasFaltantesVerificacion(FechasUtils.diferenciaEnDias( calibracion.getFechaVerificacionIntermedia(),new Date()));
			
			if (calibracionDto.getDiasFaltantes()<=0){
				calibracionDto.setSemaforo("badge bg-danger");
			}else{
				if (calibracionDto.getDiasFaltantes()>=1 && calibracionDto.getDiasFaltantes()<=30){
					calibracionDto.setSemaforo("badge bg-warning");
				} else{
					calibracionDto.setSemaforo("badge bg-success");
				}
			}
			
			if (calibracionDto.getDiasFaltantesVerificacion()<=0){
				calibracionDto.setSemaforoVerificacion("badge bg-danger");
			}else{
				if (calibracionDto.getDiasFaltantesVerificacion()>=1 && calibracionDto.getDiasFaltantesVerificacion()<=30){
					calibracionDto.setSemaforoVerificacion("badge bg-warning");
				} else{
					calibracionDto.setSemaforoVerificacion("badge bg-success");
				}
			}
			calibracionDtoList.add(calibracionDto);
			
		}

		return calibracionDtoList;

	}
	
	public List<CalibracionDto> traerCalibracionesInstrumento(Integer calibracionId,UsuarioAuthenticate usuario) {

		PerfilEmpresa perfilEmpresa = perfilEmpresaRepository.findOne(usuario
				.getPerfil_empresa());

		Calibracion calibracionData=calibracionRepository.findOne(calibracionId);
		List<Calibracion> list = calibracionRepository.findCalibracionByInstrumento(perfilEmpresa.getEmpresa(), calibracionData.getInstrumento());
				

		List<CalibracionDto> calibracionDtoList = new ArrayList<CalibracionDto>();

		for (Calibracion calibracion : list) {
			CalibracionDto calibracionDto = new CalibracionDto();
			calibracionDto.setCalibracion(calibracion);
		
			calibracionDtoList.add(calibracionDto);
			
		}

		return calibracionDtoList;

	}
	
	public void actualizarCalibracion(Calibracion calibracion,UsuarioAuthenticate usuario) throws Exception{
		
		calibracionRepository.save(calibracion);
	}
	
	public void guardarCalibracion(Calibracion calibracion,UsuarioAuthenticate usuario) throws Exception{
		
		PerfilEmpresa perfilEmpresa = perfilEmpresaRepository.findOne(usuario
				.getPerfil_empresa());
		
		Catalogo estado = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosGenerales.ACTIVO).get(0);

		Catalogo desactivar = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosGenerales.ELIMINADO).get(0);
	
		
		if (calibracion.getFechaCalibracionFutura().before(calibracion.getFechaCalibracion())){
			throw new Exception("Las fechas de registro de calibración son incorrectas");
		}
		
		if (calibracion.getFechaVerificacionIntermedia().before(calibracion.getFechaCalibracion())){
			throw new Exception("Las fechas de verificación es incorrecta");
		}
		
		if (calibracion.getArchivo()==null){
			throw new Exception("Debe subir el archivo de calibración");
		}
		
		Calibracion calibracionAnterior=calibracionRepository.findCalibracionByEstado(perfilEmpresa.getEmpresa(), estado, calibracion.getInstrumento());
		
		if (calibracionAnterior!=null){
			calibracionAnterior.setCatalogo(desactivar);
			calibracionRepository.save(calibracionAnterior);
		}
		
		calibracion.setCatalogo(estado);
		calibracion.setFechaRegistro(new Date());
		calibracionRepository.save(calibracion);
		
		
		
		
	}

}
