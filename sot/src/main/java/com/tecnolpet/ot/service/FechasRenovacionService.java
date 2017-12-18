/**
 * 
 */
package com.tecnolpet.ot.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecnolpet.ot.constant.SotApp;
import com.tecnolpet.ot.dto.AvisoFechasDto;
import com.tecnolpet.ot.model.Catalogo;
import com.tecnolpet.ot.model.FechasRenovacion;
import com.tecnolpet.ot.repository.CatalogoRepository;
import com.tecnolpet.ot.repository.FechasRenovacionRepository;
import com.tecnolpet.ot.seguridad.UsuarioAuthenticate;
import com.tecnolpet.ot.utils.FechasUtils;

/**
 * @author administrador
 *
 */
@Service
public class FechasRenovacionService {
	
	@Autowired
	private FechasRenovacionRepository fechasRenovacionRepository;
	
	@Autowired
	private CatalogoRepository catalogoRepository;
	
	
	public List<FechasRenovacion> getFechasRenovacionList(UsuarioAuthenticate usuario){
		Catalogo cancelada = catalogoRepository.findCatalogoBySigla(SotApp.EstadosFechasRenovacion.CANCELADA).get(0);
		
		return fechasRenovacionRepository.findFechasRenovacionRegistradasAndProcesadas(cancelada);
	}
	
	public List<FechasRenovacion> getFechasRenovacionRegistradas(UsuarioAuthenticate usuario){
		Catalogo registradas = catalogoRepository.findCatalogoBySigla(SotApp.EstadosFechasRenovacion.REGISTRADA).get(0);
		
		return fechasRenovacionRepository.findByEstado(registradas);
	}
	
	public void guardar(FechasRenovacion fechasRenovacion, final UsuarioAuthenticate usuario) throws Exception{
		
		
		
		Catalogo estado=catalogoRepository.findCatalogoBySigla(SotApp.EstadosFechasRenovacion.REGISTRADA).get(0);
		
		List<FechasRenovacion> listaFechasRegistradas=fechasRenovacionRepository.findByEstado(estado);
		
		if (listaFechasRegistradas.size()>0){
			throw new Exception("Ya existe una fecha registrada");
		}
		fechasRenovacion.setUsuarioRegistro(usuario.getId());
		fechasRenovacion.setFechaRegistro(new Timestamp(Calendar.getInstance().getTime().getTime()));
		fechasRenovacion.setEstado(estado);
		
		fechasRenovacionRepository.save(fechasRenovacion);
	}
	
	public void eliminar(FechasRenovacion fechasRenovacion, final Integer usuario){
		Catalogo canceladas = catalogoRepository.findCatalogoBySigla(SotApp.EstadosFechasRenovacion.CANCELADA).get(0);
		fechasRenovacion.setFechaRegistro(new Timestamp(Calendar.getInstance().getTime().getTime()));
		fechasRenovacion.setUsuarioRegistro(usuario);
		fechasRenovacion.setEstado(canceladas);
		fechasRenovacionRepository.save(fechasRenovacion);
	}
	
	public void activar(FechasRenovacion fechasRenovacion, final Integer usuario){
		Catalogo activada = catalogoRepository.findCatalogoBySigla(SotApp.EstadosFechasRenovacion.ACTIVADA).get(0);
		fechasRenovacion.setFechaRegistro(new Timestamp(Calendar.getInstance().getTime().getTime()));
		fechasRenovacion.setUsuarioRegistro(usuario);
		fechasRenovacion.setEstado(activada);
		fechasRenovacionRepository.save(fechasRenovacion);
	}
	
	public void finalizar(FechasRenovacion fechasRenovacion, final Integer usuario){
		Catalogo activada = catalogoRepository.findCatalogoBySigla(SotApp.EstadosFechasRenovacion.FINALIZADA).get(0);
		fechasRenovacion.setFechaRegistro(new Timestamp(Calendar.getInstance().getTime().getTime()));
		fechasRenovacion.setUsuarioProcesa(usuario);
		fechasRenovacion.setEstado(activada);
		fechasRenovacionRepository.save(fechasRenovacion);
	}
	
	@SuppressWarnings("unchecked")
	public List<FechasRenovacion> getUltimaRangoFechasRenovacion(UsuarioAuthenticate usuario){
		List<FechasRenovacion> res = Collections.EMPTY_LIST;
		Catalogo procesada = catalogoRepository.findCatalogoBySigla(SotApp.EstadosFechasRenovacion.ACTIVADA).get(0);
		List<FechasRenovacion> fechasRenovacion = fechasRenovacionRepository.findByfechaProcesoDesc(procesada);
		if(fechasRenovacion != null){
			res = fechasRenovacion; 
		}
		return res;
	}
	
	public List<FechasRenovacion> traerFechasActivas(){
		List<FechasRenovacion> fechasRenovacion;
		Catalogo activa = catalogoRepository.findCatalogoBySigla(SotApp.EstadosFechasRenovacion.ACTIVADA).get(0);
		Catalogo finalizada = catalogoRepository.findCatalogoBySigla(SotApp.EstadosFechasRenovacion.FINALIZADA).get(0);
		
		fechasRenovacion = fechasRenovacionRepository.findFechasRenovacionActivas(activa, finalizada);
		
		return fechasRenovacion;
	}
	
	public Date getFechaSugerida(UsuarioAuthenticate usuario){
		Date res = null;
		Calendar sugerencia = Calendar.getInstance();
		Catalogo procesada = catalogoRepository.findCatalogoBySigla(SotApp.EstadosFechasRenovacion.ACTIVADA).get(0);

		List<FechasRenovacion> fechasRenovacion = fechasRenovacionRepository.findByfechaProcesoDesc(procesada);
		if(!fechasRenovacion.isEmpty() && fechasRenovacion.size() > 0 ){
			sugerencia.setTime(fechasRenovacion.get(0).getFechaFin());
			sugerencia.add(Calendar.DAY_OF_MONTH, 1);
			res = sugerencia.getTime();
		}
		return res;
	}
	
	public boolean getValidarFechas(AvisoFechasDto avisoFechasDto){
		boolean res = false;
		Date fechaInicio = avisoFechasDto.getFechaInicio();
		Date fechaFin = avisoFechasDto.getFechaFin();
		
		res = FechasUtils.validaFechas(fechaInicio, fechaFin);
		return res;
	}
	
	
	public List<FechasRenovacion> getFechasRenovacionProcesadas(UsuarioAuthenticate usuario){	
		Catalogo procesada = catalogoRepository.findCatalogoBySigla(SotApp.EstadosFechasRenovacion.ACTIVADA).get(0);
		
		return fechasRenovacionRepository.findByEstadoOrderByIdDesc(procesada);
	}

}
