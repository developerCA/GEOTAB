package com.tecnolpet.ot.geotab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecnolpet.ot.constant.SotApp;
import com.tecnolpet.ot.geotab.dto.DispositivoGeotabDto;
import com.tecnolpet.ot.geotab.dto.GrupoChildrenGeoTabDto;
import com.tecnolpet.ot.geotab.dto.GrupoDispositivoDto;
import com.tecnolpet.ot.geotab.dto.GrupoGeotabDto;
import com.tecnolpet.ot.model.Empresa;
import com.tecnolpet.ot.model.GeotabGrupo;
import com.tecnolpet.ot.model.Instrumento;
import com.tecnolpet.ot.model.Ruta;
import com.tecnolpet.ot.repository.EmpresaRepository;
import com.tecnolpet.ot.repository.GeoTabGrupoRepository;
import com.tecnolpet.ot.repository.InstrumentoRepository;
import com.tecnolpet.ot.repository.RutaRepository;

@Service
public class GeoTabService {

	@Autowired
	private GeoTabGrupoRepository geoTabGrupoRepository;

	@Autowired
	private InstrumentoRepository instrumentoRepository;

	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
	private RutaRepository rutaRepository;

	public void sincronziarGrupos(List<GrupoGeotabDto> grupos) {
		StringBuilder hijos;
		GeotabGrupo geotabGrupo;

		geoTabGrupoRepository.deleteAll();

		for (GrupoGeotabDto grupo : grupos) {
			geotabGrupo = new GeotabGrupo();
			hijos = new StringBuilder();
			for (GrupoChildrenGeoTabDto grupoHijo : grupo.getChildren()) {
				hijos.append(grupoHijo.getId()).append(",");
			}
			geotabGrupo.setIdentificador(grupo.getId());
			geotabGrupo.setNombre(grupo.getName());
			geotabGrupo.setHijos(hijos.toString());
			geoTabGrupoRepository.save(geotabGrupo);
		}

		establecerNiveles();
		sincronizarCooperativas();
	}

	public void sincronziarDispositivos(List<DispositivoGeotabDto> dispositivos) {
		StringBuilder hijos;
		Instrumento geotabDispositivo;

		//instrumentoRepository.deleteAll();

		for (DispositivoGeotabDto dispositivo: dispositivos) {
			geotabDispositivo = new Instrumento();
			for (GrupoDispositivoDto padre: dispositivo.getGroups()) {
				geotabDispositivo.setGrupo_id(padre.getId());
				break;
			}
			geotabDispositivo.setPlaca(dispositivo.getLicensePlate());
			geotabDispositivo.setHabilitacion(dispositivo.getVehicleIdentificationNumber());
			geotabDispositivo.setCodigo_dispositivo(dispositivo.getId());
			geotabDispositivo.setSerie(dispositivo.getSerialNumber());
			//geotabDispositivo.set(dispositivo.get);
			instrumentoRepository.save(geotabDispositivo);
		}
	}

	public List<Instrumento> devolverDispositivos(){
		return instrumentoRepository.findAll();
	}

	public List<Ruta> devolverRutas(Integer codigoEmpresa){
		Empresa empresa=empresaRepository.findOne(codigoEmpresa);
		
		return rutaRepository.findByEmpresa(empresa);
	}

	private void establecerNiveles() {
		GeotabGrupo geotabGrupo = geoTabGrupoRepository.buscarPorIdentificador(SotApp.GeoTab.NIVEL_INICIAL_GRUPO);
		Integer nivel = 1;
		if (null != geotabGrupo) {
			geotabGrupo.setNivel(nivel);
			geoTabGrupoRepository.save(geotabGrupo);
			establecerNivelesHijos(geotabGrupo, nivel);
		}
	}

	private void establecerNivelesHijos(GeotabGrupo geotabGrupo, Integer nivel) {
		Integer siguienteNivel = nivel + 1;
		String[] hijos = geotabGrupo.getHijos().split(",");

		for (String hijo : hijos) {
			List<GeotabGrupo> gruposHijos = geoTabGrupoRepository.buscarGruposHijos(hijo);
			for (GeotabGrupo geotabGrupoHijo : gruposHijos) {
				geotabGrupoHijo.setNivel(siguienteNivel);
				geoTabGrupoRepository.save(geotabGrupoHijo);
				establecerNivelesHijos(geotabGrupoHijo, siguienteNivel);
			}
		}

	}

	private void sincronizarCooperativas() {

		List<GeotabGrupo> listaCooperativas = geoTabGrupoRepository.findByNivel(SotApp.GeoTab.NIVEL_COOPERATIVA);

		for (GeotabGrupo cooperativa : listaCooperativas) {
			Empresa empresa = empresaRepository.findByCodigoCooperativa(cooperativa.getIdentificador());

			if (null == empresa) {
				empresa = new Empresa();
				empresa.setCodigoCooperativa(cooperativa.getIdentificador());
				empresa.setEstado(Boolean.TRUE);
				empresa.setNombre(cooperativa.getNombre());

			} else {
				empresa.setNombre(cooperativa.getNombre());
			}

			empresaRepository.save(empresa);
			sincronizarRutas(cooperativa, empresa);
		}
	}
	
	private void crearPerfiles(Empresa empresa){
		
	}

	private void sincronizarRutas(GeotabGrupo cooperativa, Empresa empresa) {

		String[] hijos = cooperativa.getHijos().split(",");

		for (String identificador : hijos) {
			GeotabGrupo rutaGrupo = geoTabGrupoRepository.buscarPorIdentificador(identificador);
			if (null != rutaGrupo) {
				Ruta ruta = rutaRepository.findByEmpresaAndIdentificador(empresa, rutaGrupo.getIdentificador());
				if (ruta == null) {
					ruta = new Ruta();
					ruta.setEmpresa(empresa);
					ruta.setNombre(rutaGrupo.getNombre());
					ruta.setIdentificador(rutaGrupo.getIdentificador());
				} else {
					ruta.setNombre(rutaGrupo.getNombre());
				}

				rutaRepository.save(ruta);

			}

		}

	}
}
