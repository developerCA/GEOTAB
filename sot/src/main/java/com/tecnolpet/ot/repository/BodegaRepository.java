package com.tecnolpet.ot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tecnolpet.ot.model.Bodega;
import com.tecnolpet.ot.model.Catalogo;

public interface BodegaRepository extends JpaRepository<Bodega, Integer>{
	
	List<Bodega> findByEstado(Catalogo catalogo);
	
}

