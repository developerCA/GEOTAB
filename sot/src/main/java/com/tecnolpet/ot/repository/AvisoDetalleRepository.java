/**
 * 
 */
package com.tecnolpet.ot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tecnolpet.ot.model.Aviso;
import com.tecnolpet.ot.model.AvisoDetalle;
import com.tecnolpet.ot.model.Suscripcion;

/**
 * @author Armando Ariel Suarez Pons
 *
 */
public interface AvisoDetalleRepository extends JpaRepository<AvisoDetalle, Integer>{
	List<AvisoDetalle> findByAvisoOrderBySuscripcionAsc(Aviso aviso);
	
	AvisoDetalle findBySuscripcion(Suscripcion suscripcion);
}
