/**
 * 
 */
package com.tecnolpet.ot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecnolpet.ot.model.TipoCliente;
import com.tecnolpet.ot.repository.TipoClienteRepository;

/**
 * @author administrador
 *
 */
@Service
public class TipoClienteService {

	@Autowired
	private TipoClienteRepository tipoClienteRepository;
	
	public List<TipoCliente> traerClientes(){
		return tipoClienteRepository.findAll();
	}
}
