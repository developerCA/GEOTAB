/**
 * 
 */
package com.tecnolpet.ot.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tecnolpet.ot.dto.AvisoLineaProductoDto;
import com.tecnolpet.ot.dto.AvisoRecibidoDto;
import com.tecnolpet.ot.dto.RespuestaDto;
import com.tecnolpet.ot.model.Aviso;
import com.tecnolpet.ot.model.FechasRenovacion;
import com.tecnolpet.ot.seguridad.UsuarioAuthenticate;
import com.tecnolpet.ot.service.AvisoService;

/**
 * @author Armando Ariel Suárez Pons
 *
 */

@RestController
@RequestMapping("api/aviso")
public class AvisoController {

	private final AvisoService avisoService;
	
	@Autowired
	public AvisoController(AvisoService avisoService) {
		this.avisoService = avisoService;
	}
	
	@RequestMapping(value="/porFechas", method=RequestMethod.PUT)
	public AvisoLineaProductoDto traerSuscripcionesPorFechas(@RequestBody Integer idFechaRenovacion, @AuthenticationPrincipal UsuarioAuthenticate usuario) throws Exception{
		
		return avisoService.traerSuscripcionesPorFechas(idFechaRenovacion,usuario);
	}
	
	@RequestMapping(value="/avisos", method = RequestMethod.GET)
	public List<Aviso> traerAvisos(){
		return avisoService.getAvisos();
	}
	
	@RequestMapping(value = "/avisoFechaProceso", method = RequestMethod.PUT)
	public RespuestaDto traerFechasRenovacionRegistradas(@AuthenticationPrincipal UsuarioAuthenticate usuario){
		RespuestaDto respuesta = new RespuestaDto();
		
		try{				
			FechasRenovacion fechaRenovacion=  avisoService.traerFechasRenovacionRegistradas(usuario);
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("Los registros se procesaron exitosamente");
			respuesta.setObjeto(fechaRenovacion);
		}catch(Exception ex){
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}
		return respuesta;
		
		
	}
	
	@RequestMapping(value = "/procesar", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public RespuestaDto procesar(@RequestBody AvisoLineaProductoDto avisoDto, @AuthenticationPrincipal UsuarioAuthenticate usuario){
		RespuestaDto respuesta = new RespuestaDto();
		try{				
			avisoService.procesarAviso(avisoDto, usuario);
			
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("Los registros se procesaron exitosamente");
		}catch(Exception ex){
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje("Error al procesar los avisos de renovación");
		}
		return respuesta;
	}
	
	@RequestMapping(value = "/codigo", method = RequestMethod.PUT)
	public List<Aviso> traerAviso(@RequestBody Integer id){
		return avisoService.getAvisoPorId(id);
	}
	
	@RequestMapping(value = "/modificar", method = RequestMethod.PUT)
	public RespuestaDto modificarAvisoProcesado(@RequestBody AvisoRecibidoDto aviso, @AuthenticationPrincipal UsuarioAuthenticate usuario){
		RespuestaDto respuesta = new RespuestaDto();
		try{
			avisoService.actualizarAvisoProcesado(aviso, usuario.getId());
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("Los registros se procesaron exitosamente");
			respuesta.setObjeto(aviso);
		}catch(Exception ex){
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje("Error al procesar los avisos de renovación");
		}
		return respuesta;
	}
	
}
