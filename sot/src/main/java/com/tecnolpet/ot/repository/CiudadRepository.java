/**
 * 
 */
package com.tecnolpet.ot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tecnolpet.ot.model.Ciudad;

/**
 * @author administrador
 *
 */
public interface CiudadRepository extends JpaRepository<Ciudad, Integer>{
	
}
