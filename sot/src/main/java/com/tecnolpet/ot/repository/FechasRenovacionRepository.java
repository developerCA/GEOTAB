/**
 * 
 */
package com.tecnolpet.ot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tecnolpet.ot.model.Catalogo;
import com.tecnolpet.ot.model.FechasRenovacion;

/**
 * @author administrador
 *
 */
public interface FechasRenovacionRepository extends JpaRepository<FechasRenovacion, Integer>{
	
	@Query("SELECT fr FROM FechasRenovacion fr WHERE   fr.estado <> :cancelada  ORDER BY fr.fechaRegistro DESC")
	List<FechasRenovacion> findFechasRenovacionRegistradasAndProcesadas(@Param("cancelada") Catalogo cancelada);
	
	@Query("SELECT fr FROM FechasRenovacion fr WHERE   fr.estado = :activa or  fr.estado = :finalizada ORDER BY fr.fechaRegistro DESC")
	List<FechasRenovacion> findFechasRenovacionActivas(@Param("activa") Catalogo activa,@Param("finalizada") Catalogo finalizada);
	
	List<FechasRenovacion> findByEstado(Catalogo estado);
	
	List<FechasRenovacion> findByEstadoOrderByIdDesc(Catalogo estado);
	
	@Query("SELECT fr FROM FechasRenovacion fr WHERE fr.fechaProceso is not null and fr.estado = :procesado ORDER BY fr.fechaProceso DESC")
	List<FechasRenovacion> findByfechaProcesoDesc(@Param("procesado") Catalogo procesado);

}
