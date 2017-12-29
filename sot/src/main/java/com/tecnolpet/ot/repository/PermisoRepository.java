package com.tecnolpet.ot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tecnolpet.ot.model.Permiso;

public interface PermisoRepository extends JpaRepository<Permiso, Integer> {

	

	@Query("Select p from Permiso p where p.permiso.id is null")
	public List<Permiso> buscarPermisosPadre();

	@Query("Select p from Permiso p where  p.permiso.id = :idPadre")
	public List<Permiso> buscarPermisosHijo(@Param("idPadre") Integer idPadre);

	@Query("Select p from Permiso p where  p.permiso.estado = true and p.permiso.id is null")
	public List<Permiso> findByEmpresaPadreActivo();

	@Query("Select p from Permiso p where p.permiso.id = :idPadre and p.permiso.estado = true")
	public List<Permiso> findByEmpresaHijoActivo(
			@Param("idPadre") Integer idPadre);

}
