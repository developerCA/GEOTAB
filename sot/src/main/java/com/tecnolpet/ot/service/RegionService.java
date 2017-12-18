/**
 * 
 */
package com.tecnolpet.ot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecnolpet.ot.model.Region;
import com.tecnolpet.ot.repository.RegionRepository;

/**
 * @author administrador
 *
 */
@Service
public class RegionService {
	@Autowired
	private RegionRepository regionRepository;
	
	public List<Region> traerRegiones(){
		return regionRepository.findRegionByEstado(true);
	}
	
	public void guardar(Region region, String op ) throws Exception{
		
		if(!op.equals("update")){

			for (Region r : traerRegiones()) {
				
				if (r.getNombre()
						.equalsIgnoreCase(region.getNombre())) {
					throw new Exception(
							"Ya existe una región con ese nombre.");
				}

			}
			}
		
		region.setEstado(Boolean.TRUE);
		regionRepository.save(region);
	}
	
	public void eliminar(Region region){
		region.setEstado(Boolean.FALSE);
		regionRepository.save(region);
	}
	
}
