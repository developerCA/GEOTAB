package com.tecnolpet.ot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tecnolpet.ot.model.Calibracion;
import com.tecnolpet.ot.model.Catalogo;
import com.tecnolpet.ot.model.Empresa;
import com.tecnolpet.ot.model.Instrumento;

public interface CalibracionRepository extends
		JpaRepository<Calibracion, Integer> {

	@Query("SELECT c FROM Calibracion c WHERE  c.instrumento.empresa= :empresa and c.catalogo= :estado ")
	List<Calibracion> findCalibraciones(@Param("empresa") Empresa empresa,
			@Param("estado") Catalogo catalogo);

	@Query("SELECT c FROM Calibracion c WHERE  c.instrumento.empresa= :empresa and c.catalogo= :estado and c.instrumento= :instrumento")
	Calibracion findCalibracionByEstado(@Param("empresa") Empresa empresa,
			@Param("estado") Catalogo catalogo,
			@Param("instrumento") Instrumento instrumento);

	@Query("SELECT c FROM Calibracion c WHERE  c.instrumento.empresa= :empresa  and c.instrumento= :instrumento order by c.id desc")
	List<Calibracion> findCalibracionByInstrumento(
			@Param("empresa") Empresa empresa,
			@Param("instrumento") Instrumento instrumento);

}
