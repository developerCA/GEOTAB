package com.tecnolpet.ot.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tecnolpet.ot.model.VwProcesoSuscripcion;

public interface VwProcesoSuscripcionRepository extends JpaRepository<VwProcesoSuscripcion, Integer>{
	
	List<VwProcesoSuscripcion> findByFecha(Date fecha);
}
