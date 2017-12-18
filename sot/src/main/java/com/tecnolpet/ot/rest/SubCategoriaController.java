package com.tecnolpet.ot.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tecnolpet.ot.model.SubCategoria;
import com.tecnolpet.ot.service.SubCategoriaService;

@RestController
@RequestMapping("api/subcategoria")
public class SubCategoriaController {

	
private final SubCategoriaService subCategoriaService;
	
	@Autowired
	public SubCategoriaController(SubCategoriaService subCategoriaService) {
		this.subCategoriaService = subCategoriaService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<SubCategoria> traeSubCategorias(){
		return subCategoriaService.traerSubCategorias();
	}
	
	
}
