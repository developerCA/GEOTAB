package com.tecnolpet.ot.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tecnolpet.ot.model.Pais;
import com.tecnolpet.ot.service.PaisService;

@RestController
@RequestMapping("api/paises")
public class PaisesController {

	private final PaisService paisService;
	
	@Autowired
	public PaisesController(PaisService paisService) {
		this.paisService = paisService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Pais> traerPaises(){
		return paisService.getPaises();
	}

}
