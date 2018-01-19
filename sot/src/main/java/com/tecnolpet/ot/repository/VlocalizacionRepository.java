package com.tecnolpet.ot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tecnolpet.ot.model.VLocalizacion;

public interface VlocalizacionRepository extends JpaRepository<VLocalizacion,Integer>{

	List<VLocalizacion> findByProceso(Integer proceso);
}
