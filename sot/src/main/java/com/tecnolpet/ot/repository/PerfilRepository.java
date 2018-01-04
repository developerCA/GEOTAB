package com.tecnolpet.ot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tecnolpet.ot.model.Perfil;


public interface PerfilRepository extends JpaRepository<Perfil, Integer> {

	public List<Perfil> findByEstadoPerfil(Boolean estado);

}
