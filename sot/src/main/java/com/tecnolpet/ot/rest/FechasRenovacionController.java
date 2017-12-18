/**
 * 
 */
package com.tecnolpet.ot.rest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tecnolpet.ot.dto.AvisoFechasDto;
import com.tecnolpet.ot.dto.FechasRenovacionDto;
import com.tecnolpet.ot.dto.RespuestaDto;
import com.tecnolpet.ot.model.FechasRenovacion;
import com.tecnolpet.ot.seguridad.UsuarioAuthenticate;
import com.tecnolpet.ot.service.FechasRenovacionService;

/**
 * @author administrador
 *
 */
@RestController
@RequestMapping("api/fechas")
public class FechasRenovacionController {
	
	private final FechasRenovacionService fechasRenovacionService;
	
	@Autowired
	public FechasRenovacionController(final FechasRenovacionService fechasRenovacionService) {
		this.fechasRenovacionService = fechasRenovacionService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<FechasRenovacion> traerFechasRenovacion(@AuthenticationPrincipal UsuarioAuthenticate usuario){
		return fechasRenovacionService.getFechasRenovacionList(usuario);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.CREATED)	
	public RespuestaDto guardarFechasRenovacion(@RequestBody FechasRenovacionDto fechasRenovacionDto, @AuthenticationPrincipal UsuarioAuthenticate usuario) {
		RespuestaDto respuesta = new RespuestaDto();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		FechasRenovacion fechasRenovacion = new FechasRenovacion();
		
		try{
			fechasRenovacion.setFechaInicio(formatter.parse(fechasRenovacionDto.getFechaInicio()));
			fechasRenovacion.setFechaFin(formatter.parse(fechasRenovacionDto.getFechaFin()));
			
			fechasRenovacionService.guardar(fechasRenovacion, usuario);
			
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("El registro de fechas de renovacion se ha creado correctamente..");
			respuesta.setObjeto(fechasRenovacion);			
		}catch(Exception ex){
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}
		return respuesta;
	}
	
	@RequestMapping(value = "/eliminar", method = RequestMethod.PUT)
	public RespuestaDto eliminarFechasRenovacion(@RequestBody FechasRenovacion fechasRenovacion, @AuthenticationPrincipal UsuarioAuthenticate usuario){
		RespuestaDto respuesta = new RespuestaDto();
		try{
			fechasRenovacionService.eliminar(fechasRenovacion, usuario.getId());
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("El registro de fechas de renovacion se ha creado correctamente..");
		}catch(Exception ex){
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());			
		}
		
		return respuesta;
	}
	

	@RequestMapping(value = "/activar", method = RequestMethod.PUT)
	public RespuestaDto activarFechasRenovacion(@RequestBody FechasRenovacion fechasRenovacion, @AuthenticationPrincipal UsuarioAuthenticate usuario){
		RespuestaDto respuesta = new RespuestaDto();
		try{
			fechasRenovacionService.activar(fechasRenovacion, usuario.getId());
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("El registro de fechas de renovacion se ha creado correctamente..");
		}catch(Exception ex){
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());			
		}
		
		return respuesta;
	}
	@RequestMapping(value = "/finalizar", method = RequestMethod.PUT)
	public RespuestaDto finalizarFechasRenovacion(@RequestBody FechasRenovacion fechasRenovacion, @AuthenticationPrincipal UsuarioAuthenticate usuario){
		RespuestaDto respuesta = new RespuestaDto();
		try{
			fechasRenovacionService.finalizar(fechasRenovacion, usuario.getId());
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("El registro de fechas de renovacion se ha creado correctamente..");
		}catch(Exception ex){
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());			
		}
		
		return respuesta;
	}
	
	@RequestMapping(value="/ultimaFechaRenovacion", method = RequestMethod.GET)
	public List<FechasRenovacion> traerUltimoRangoFechasRenovacion(@AuthenticationPrincipal UsuarioAuthenticate usuario){
		return fechasRenovacionService.getUltimaRangoFechasRenovacion(usuario);
	}
	
	
	@RequestMapping(value="/fechasActivas", method = RequestMethod.GET)
	public List<FechasRenovacion> traerFechasActivas(){
		return fechasRenovacionService.traerFechasActivas();
	}
	
	
	@RequestMapping(value="/fechaSugerida", method = RequestMethod.PUT)
	public Date traerFechaSugerencia(@AuthenticationPrincipal UsuarioAuthenticate usuario){
		return fechasRenovacionService.getFechaSugerida(usuario);
	}
	
	@RequestMapping(value="/validarFechas", method = RequestMethod.PUT)
	public boolean traerFechaValidar(@RequestBody AvisoFechasDto avisoFechasDto){
		return fechasRenovacionService.getValidarFechas(avisoFechasDto);
	}
	
	@RequestMapping(value="/fechasProcesadas", method = RequestMethod.GET)
	public List<FechasRenovacion> traerFechasRenovacionProcesadas(@AuthenticationPrincipal UsuarioAuthenticate usuario){
		return fechasRenovacionService.getFechasRenovacionProcesadas(usuario);
	}
	
}
