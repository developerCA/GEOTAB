package com.tecnolpet.ot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tecnolpet.ot.model.FechaDispositivo;

public interface FechaDispositivoRepository extends JpaRepository<FechaDispositivo, Integer> {

	FechaDispositivo findByNemonico(String nemonico);
}
