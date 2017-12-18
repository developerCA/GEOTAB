/**
 * 
 */
package com.tecnolpet.ot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tecnolpet.ot.model.TipoCliente;

/**
 * @author administrador
 *
 */
public interface TipoClienteRepository extends JpaRepository<TipoCliente, Integer>{

	TipoCliente findByNombre(String nombre);
}
