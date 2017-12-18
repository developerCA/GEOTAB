package com.tecnolpet.ot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tecnolpet.ot.model.Secuencia;

public interface SecuenciaRepository extends JpaRepository<Secuencia, Integer>{

	public List<Secuencia> findByNombre(String nombre);
}
