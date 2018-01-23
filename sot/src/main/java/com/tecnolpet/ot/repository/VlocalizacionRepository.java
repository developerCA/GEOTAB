package com.tecnolpet.ot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tecnolpet.ot.model.VLocalizacionDispositivo;

public interface VlocalizacionRepository extends JpaRepository<VLocalizacionDispositivo,Integer>{

	List<VLocalizacionDispositivo> findByProceso(Integer proceso);
}
