package com.tecnolpet.ot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tecnolpet.ot.model.TipoZona;

public interface TipoZonaRepository extends JpaRepository<TipoZona, Integer>{
	TipoZona findByIdentificador(String identificador);
}
