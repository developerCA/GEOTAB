package com.tecnolpet.ot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tecnolpet.ot.model.Rol;



public interface RolRepository  extends JpaRepository<Rol, Long>{

	public List<Rol> findByEstadoRol(Boolean estado);
}

