package com.tecnolpet.ot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tecnolpet.ot.model.ProcesoLocalizacion;

public interface ProcesoLocalizacionRepository extends JpaRepository<ProcesoLocalizacion, Integer>{

	List<ProcesoLocalizacion> findByEstado(String estado);
	
	@Query(value="SELECT * FROM proceso_localizacion pl WHERE pl.estado='GEN' order by codigo limit 1 ",nativeQuery =true)
	List<ProcesoLocalizacion> findbyestadoGen();
	
}
