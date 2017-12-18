package com.tecnolpet.ot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tecnolpet.ot.model.PermisoPerfilEmpresa;



public interface PermisoPerfilEmpresaRepository extends
		JpaRepository<PermisoPerfilEmpresa, Integer> {

	@Query("Select p from PermisoPerfilEmpresa p where p.perfilEmpresa.id =:idPP and p.permiso.permiso.id is null")
	List<PermisoPerfilEmpresa> buscarPadresPermisoPerfil(
			@Param("idPP") Integer idPerfilEmpresa);

	@Query("Select p from PermisoPerfilEmpresa p where p.perfilEmpresa.id =:idPP and p.permiso.permiso.id = :idPadre")
	List<PermisoPerfilEmpresa> buscarHijosPermisoPerfil(
			@Param("idPP") Integer idPerfilEmpresa,
			@Param("idPadre") Integer idPadre);

	@Query("Select p from PermisoPerfilEmpresa p where p.perfilEmpresa.id =:idPP and p.permiso.id = :idPerfil ")
	List<PermisoPerfilEmpresa> buscarPermisoPerfilEmpresa(
			@Param("idPP") Integer idPerfilEmpresa,
			@Param("idPerfil") Integer idPerfil);

	@Query("Select p from PermisoPerfilEmpresa p where p.perfilEmpresa.id =:idPP and p.permiso.id = :idPadre")
	List<PermisoPerfilEmpresa> buscarPadre(
			@Param("idPP") Integer idPerfilEmpresa,
			@Param("idPadre") Integer idPadre);

}
