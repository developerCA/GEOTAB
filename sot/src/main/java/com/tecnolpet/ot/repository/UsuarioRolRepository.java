package com.tecnolpet.ot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tecnolpet.ot.model.Rol;
import com.tecnolpet.ot.model.UsuarioRol;



public interface UsuarioRolRepository extends JpaRepository<UsuarioRol, Long>{
	
	public List<UsuarioRol> findUsuarioRolByRol(Rol usuario);

}
