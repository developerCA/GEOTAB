package com.tecnolpet.ot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tecnolpet.ot.model.Renovacion;
import com.tecnolpet.ot.model.RenovacionDetalle;

public interface RenovacionDetalleRepository extends JpaRepository<RenovacionDetalle, Integer>{
	
	List<RenovacionDetalle> findByRenovacion(Renovacion renovacion);
	
}
