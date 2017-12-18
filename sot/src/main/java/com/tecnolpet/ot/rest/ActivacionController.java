package com.tecnolpet.ot.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tecnolpet.ot.dto.RespuestaDto;
import com.tecnolpet.ot.model.Cliente;
import com.tecnolpet.ot.model.Suscripcion;
import com.tecnolpet.ot.seguridad.UsuarioAuthenticate;
import com.tecnolpet.ot.service.ActivacionService;

@RestController
@RequestMapping("api/activacion")
public class ActivacionController {
	private final ActivacionService activacionService;
	
	@Autowired
	public ActivacionController(ActivacionService activacionService) {
		this.activacionService = activacionService;
	}
	
	@RequestMapping(value="/activas/temporal", method = RequestMethod.GET)
	public List<Suscripcion> traerSuscripcionesActivasTemporalmente(@AuthenticationPrincipal UsuarioAuthenticate usuario){
		return activacionService.getSuscripcionesActivadasTemporalmente(usuario);
	}
	
	@RequestMapping(value="/clientesPorSuscripcionesActivas", method = RequestMethod.GET)
	public List<Cliente> traerClientesDeSuscripcionesActivas(){
		return activacionService.getClienteList();
	}
	
	@RequestMapping(value="/activar/suscripcion", method=RequestMethod.PUT)
	public RespuestaDto activarSuscripcion(@RequestBody Integer suscripcionId, @AuthenticationPrincipal UsuarioAuthenticate usuario){
		RespuestaDto respuesta = new RespuestaDto();
		
		try{
			activacionService.guardarSuscripcionActivada(suscripcionId, usuario);
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("La suscripción se activó exitosamente");
		}catch(Exception ex){
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}
		
		return respuesta;
	}
}
