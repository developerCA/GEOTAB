package com.tecnolpet.ot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tecnolpet.ot.model.Localizacion;

public interface LocalizacionRepository extends JpaRepository<Localizacion, Integer> {
	
	@Query(value= "select nextval('proceso_localizacion_seq') ",nativeQuery=true)
	public Integer traerNumeroProceso();
	
	public List<Localizacion> findByProceso(Integer proceso);
}
