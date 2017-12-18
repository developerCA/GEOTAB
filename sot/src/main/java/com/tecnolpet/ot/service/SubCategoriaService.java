package com.tecnolpet.ot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecnolpet.ot.model.SubCategoria;
import com.tecnolpet.ot.repository.SubcategoriaRepository;

@Service
public class SubCategoriaService {

	@Autowired
	private SubcategoriaRepository subcategoriaRepository;
	
	public List<SubCategoria> traerSubCategorias(){
		return subcategoriaRepository.findAll();
	}
}
