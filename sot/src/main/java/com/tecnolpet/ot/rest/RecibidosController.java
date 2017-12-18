/**
 * 
 */
package com.tecnolpet.ot.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tecnolpet.ot.dto.CodigoDto;
import com.tecnolpet.ot.model.Distribuidores;
import com.tecnolpet.ot.model.Suscripcion;
import com.tecnolpet.ot.service.RecibidosService;

/**
 * @author administrador
 *
 */
@RestController
@RequestMapping("api/recibidos")
public class RecibidosController {

	private final RecibidosService recibidosService; 
	
	@Autowired
	public RecibidosController(RecibidosService recibidosService) {
		this.recibidosService = recibidosService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Distribuidores> traerDistribuidoresList(){
		return recibidosService.getDistribuidores();
	}
	
	@RequestMapping(value="/suscripciones", method = RequestMethod.PUT)
	public List<Suscripcion> traerSuscripcionesList(@RequestBody String codigo){
		return recibidosService.procesarCodigoBarras(codigo);
	}
	
	@RequestMapping(value="/analizar", method = RequestMethod.PUT)
	public CodigoDto analizarCodigo(@RequestBody String codigo){
		return recibidosService.analizarCodigo(codigo);
	}
	
}
