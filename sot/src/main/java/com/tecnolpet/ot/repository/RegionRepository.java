/**
 * 
 */
package com.tecnolpet.ot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tecnolpet.ot.model.Region;

/**
 * @author administrador
 *
 */
public interface RegionRepository extends JpaRepository<Region, Integer> {
	
	public List<Region> findRegionByEstado(Boolean estado);
}
