/**
 * 
 */
package com.tecnolpet.ot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecnolpet.ot.model.Pais;
import com.tecnolpet.ot.repository.PaisRepository;

/**
 * @author administrador
 *
 */
@Service
public class PaisService {
	
	@Autowired
	private PaisRepository paisRepository;
	
	public List<Pais> getPaises(){
		return paisRepository.findAll();
	}
	
}
