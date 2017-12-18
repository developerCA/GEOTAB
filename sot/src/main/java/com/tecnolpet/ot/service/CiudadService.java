/**
 * 
 */
package com.tecnolpet.ot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecnolpet.ot.model.Ciudad;
import com.tecnolpet.ot.repository.CiudadRepository;

/**
 * @author administrador
 *
 */
@Service
public class CiudadService {
	@Autowired
	private CiudadRepository ciudadRepository;
	
	public List<Ciudad> getCiudades(){
		return ciudadRepository.findAll();
	}
}
