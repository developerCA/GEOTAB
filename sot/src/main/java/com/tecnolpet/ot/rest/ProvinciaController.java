/**
 * 
 */
package com.tecnolpet.ot.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tecnolpet.ot.model.Provincia;
import com.tecnolpet.ot.service.ProvinciaService;

/**
 * @author administrador
 *
 */
@RestController
@RequestMapping("api/provincias")
public class ProvinciaController {
	
	private final ProvinciaService provinciaService;
	
	@Autowired
	public ProvinciaController(ProvinciaService provinciaService) {
		this.provinciaService = provinciaService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Provincia> traerProvincias(){
		return provinciaService.getProvincias();
	}

}
