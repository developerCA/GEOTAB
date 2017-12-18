package com.tecnolpet.ot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tecnolpet.ot.model.AccesosRenovacion;
import com.tecnolpet.ot.model.RenovacionDetalle;

public interface AccesosRenovacionRepository extends JpaRepository<AccesosRenovacion, Integer>{
	List<AccesosRenovacion> findByRenovacionDetalle(RenovacionDetalle renovacionDetalle);
}
