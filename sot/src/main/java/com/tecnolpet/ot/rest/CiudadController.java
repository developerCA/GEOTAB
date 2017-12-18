/**
 * 
 */
package com.tecnolpet.ot.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tecnolpet.ot.model.Ciudad;
import com.tecnolpet.ot.service.CiudadService;

/**
 * @author administrador
 *
 */
@RestController
@RequestMapping("api/ciudad")
public class CiudadController {
	private final CiudadService ciudadService;
	
	@Autowired
	public CiudadController(CiudadService ciudadService) {
		this.ciudadService = ciudadService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Ciudad> traerCiudades(){
		return ciudadService.getCiudades();
	}	
}
