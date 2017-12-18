/**
 * 
 */
package com.tecnolpet.ot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecnolpet.ot.model.Provincia;
import com.tecnolpet.ot.repository.ProvinciaRepository;

/**
 * @author administrador
 *
 */
@Service
public class ProvinciaService {
	
	@Autowired
	private ProvinciaRepository provinciaRepository;
	
	public List<Provincia> getProvincias(){
		return provinciaRepository.findAll();
	}
}
