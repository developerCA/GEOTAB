package com.tecnolpet.ot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tecnolpet.ot.model.Ruta;
import com.tecnolpet.ot.model.Zona;

public interface ZonaRepository extends JpaRepository<Zona, Integer> {
	
	Zona findByIdentificador(String identificador);
	
	List<Zona> findByRutaAndEstadoOrderByOrdenAsc(Ruta ruta,Boolean estado);
}
