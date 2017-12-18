package com.tecnolpet.ot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tecnolpet.ot.model.Acceso;
import com.tecnolpet.ot.model.Suscripcion;

public interface AccesoRepository extends JpaRepository<Acceso, Long>{

	@Query("Select a from Acceso a where a.suscripcion= :suscripcion order by a.id  ")
	public List<Acceso> findBySuscripcion(@Param("suscripcion") Suscripcion suscripcion);
	
	@Query("Select a from Acceso a where a.suscripcion= :suscripcion and a.catalogoTipoAcceso.sigla <> 'ACCOBS'  order by a.id  ")
	public List<Acceso> findBySuscripcionTipo(@Param("suscripcion") Suscripcion suscripcion);
	
	@Query(value= "select nextval('seq_generarusuario') ",nativeQuery=true)
	public Long getNumeroUsuario();
	
}
