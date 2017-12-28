package com.tecnolpet.ot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tecnolpet.ot.model.GeotabGrupo;

public interface GeoTabGrupoRepository extends JpaRepository<GeotabGrupo, Integer>{ 

	@Query("SELECT g FROM GeotabGrupo g WHERE g.identificador = :identificador")
	GeotabGrupo buscarPorIdentificador(@Param("identificador") String identificador);
	
	@Query("SELECT g FROM GeotabGrupo g WHERE g.identificador = :identificador")
	List<GeotabGrupo> buscarGruposHijos(@Param("identificador") String identificador);
	
	public List<GeotabGrupo> findByNivel(Integer nivel);

}
