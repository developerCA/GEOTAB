package com.tecnolpet.ot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tecnolpet.ot.model.PermisoPerfil;



public interface PermisoPerfilRepository extends
		JpaRepository<PermisoPerfil, Integer> {

	@Query("Select p from PermisoPerfil p where p.perfil.id =:idPP and p.permiso.permiso.id is null")
	List<PermisoPerfil> buscarPadresPermisoPerfil(
			@Param("idPP") Integer idPerfil);

	@Query("Select p from PermisoPerfil p where p.perfil.id =:idPP and p.permiso.permiso.id = :idPadre")
	List<PermisoPerfil> buscarHijosPermisoPerfil(
			@Param("idPP") Integer idPerfil,
			@Param("idPadre") Integer idPadre);

	@Query("Select p from PermisoPerfil p where p.perfil.id =:idPerfil and p.permiso.id = :idPermiso ")
	List<PermisoPerfil> buscarPermisoPerfil(
			@Param("idPerfil") Integer idPerfil,
			@Param("idPermiso") Integer idPermiso);

	@Query("Select p from PermisoPerfil p where p.perfil.id =:idPP and p.permiso.id = :idPadre")
	List<PermisoPerfil> buscarPadre(
			@Param("idPP") Integer idPerfil,
			@Param("idPadre") Integer idPadre);

}
