/**
 * 
 */
package com.tecnolpet.ot.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tecnolpet.ot.model.TipoCliente;
import com.tecnolpet.ot.service.TipoClienteService;

/**
 * @author administrador
 *
 */
@RestController
@RequestMapping("api/tipoCliente")
public class TipoClienteController {
	private final TipoClienteService tipoClienteService;
	
	@Autowired
	public TipoClienteController(TipoClienteService tipoClienteService) {
		this.tipoClienteService = tipoClienteService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<TipoCliente> traerTiposCliente(){
		return tipoClienteService.traerClientes();
	}
}
