/**
 * 
 */
package com.tecnolpet.ot.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tecnolpet.ot.dto.FiltrosDto;
import com.tecnolpet.ot.dto.RespuestaReporteDto;
import com.tecnolpet.ot.model.SubCategoria;
import com.tecnolpet.ot.seguridad.UsuarioAuthenticate;
import com.tecnolpet.ot.service.EtiquetadoService;

/**
 * @author administrador
 *
 */
@RestController
@RequestMapping("api/etiquetado")
public class EtiquetadoController {
	
	private final EtiquetadoService etiquetadoService; 
	
	@Autowired
	public EtiquetadoController(EtiquetadoService etiquetadoService) {
		this.etiquetadoService = etiquetadoService;
	}
	
	@RequestMapping(value="/url", method = RequestMethod.PUT)
	public RespuestaReporteDto traerUrlConParametros(@RequestBody FiltrosDto filtrosDto, @AuthenticationPrincipal UsuarioAuthenticate usuario) {
		return etiquetadoService.getUrlConParams(filtrosDto, usuario);
	}
	
	@RequestMapping(value="/subcategorias",method = RequestMethod.GET)
	public List<SubCategoria> traerTodasSubcategorias(){
		return etiquetadoService.getSubcategoriaList();
	}
	
}
