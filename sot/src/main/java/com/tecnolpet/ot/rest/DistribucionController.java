package com.tecnolpet.ot.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tecnolpet.ot.dto.RespuestaDto;
import com.tecnolpet.ot.model.Distribucion;
import com.tecnolpet.ot.model.Telerenovador;
import com.tecnolpet.ot.seguridad.UsuarioAuthenticate;
import com.tecnolpet.ot.service.DistribucionService;

@RestController
@RequestMapping("api/distribucion")
public class DistribucionController {
	
	private final DistribucionService distribucionService;
	
	@Autowired
	public DistribucionController(DistribucionService distribucionService) {
		this.distribucionService = distribucionService;
	}
	
	@RequestMapping(value="/porFechaRenovacion", method = RequestMethod.GET)
	public List<Distribucion> traerDistribucion(@RequestParam Integer codigoFechaRenovacion,@RequestParam Integer codigoSubcategoria, @AuthenticationPrincipal UsuarioAuthenticate usuario){
		return distribucionService.traerDistribucionPorFechaLinea(codigoFechaRenovacion, codigoSubcategoria,usuario);
	}
	
	@RequestMapping(value="/telerenovadores", method = RequestMethod.GET)
	public List<Telerenovador> traerTelerenovadores(@AuthenticationPrincipal UsuarioAuthenticate usuario){
		return distribucionService.getTelerenovadores(usuario);
	}
	
	@RequestMapping(value="/asignar", method = RequestMethod.PUT)
	public RespuestaDto asignarAvisos(@RequestBody List<Distribucion> listaDistribucion){
		RespuestaDto respuesta = new RespuestaDto();
		try{
			distribucionService.asignarTelerenovador(listaDistribucion);
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("OK");
		}catch(Exception ex){
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());			
		}
		
		return respuesta;
	}
}
