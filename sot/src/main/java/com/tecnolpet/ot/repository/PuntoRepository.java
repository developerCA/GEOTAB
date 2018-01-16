package com.tecnolpet.ot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tecnolpet.ot.model.Punto;
import com.tecnolpet.ot.model.Zona;

public interface PuntoRepository extends JpaRepository<Punto, Integer>{

	List<Punto> findByZona(Zona zona);
	
}
