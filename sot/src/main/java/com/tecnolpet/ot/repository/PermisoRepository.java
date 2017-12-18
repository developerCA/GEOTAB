package com.tecnolpet.ot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tecnolpet.ot.model.Permiso;



public interface PermisoRepository extends JpaRepository<Permiso, Integer> {

	@Query("Select p from Permiso p where p.empresa.id =:idEmpresa")
	public List<Permiso> findByEmpresaId(
			@Param("idEmpresa") Integer idEmpresa);


	@Query("Select p from Permiso p where p.empresa.id =:idEmpresa and p.permiso.id is null")
	public List<Permiso> findByEmpresaPadre(
			@Param("idEmpresa") Integer idEmpresa);

	@Query("Select p from Permiso p where p.empresa.id =:idEmpresa and p.permiso.id = :idPadre")
	public List<Permiso> findByEmpresaHijo(
			@Param("idEmpresa") Integer idEmpresa,
			@Param("idPadre") Integer idPadre);

	@Query("Select p from Permiso p where p.empresa.id =:idEmpresa  and p.permiso.estado = true and p.permiso.id is null")
	public List<Permiso> findByEmpresaPadreActivo(
			@Param("idEmpresa") Integer idEmpresa);

	@Query("Select p from Permiso p where p.empresa.id =:idEmpresa and p.permiso.id = :idPadre and p.permiso.estado = true")
	public List<Permiso> findByEmpresaHijoActivo(
			@Param("idEmpresa") Integer idEmpresa,
			@Param("idPadre") Integer idPadre);

}
