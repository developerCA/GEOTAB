/**
 * 
 */
package com.tecnolpet.ot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tecnolpet.ot.model.Region;
import com.tecnolpet.ot.model.Sucursal;

/**
 * @author administrador
 *
 */
public interface SucursalRepository extends JpaRepository<Sucursal, Integer>{
	
	public List<Sucursal> findSucursalByEstado(Boolean estado);
	public List<Sucursal> findSucursalByRegionAndEstado(Region region, Boolean estado);
}
