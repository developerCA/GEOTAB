/**
 * 
 */
package com.tecnolpet.ot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tecnolpet.ot.model.Aviso;
import com.tecnolpet.ot.model.FechasRenovacion;
import com.tecnolpet.ot.model.Sucursal;

/**
 * @author administrador
 *
 */
public interface AvisoRepository extends JpaRepository<Aviso, Integer>{
	List<Aviso> findById(Integer id);
	
	@Query("SELECT a FROM Aviso a WHERE a.codigoFechasRenovacion= :fechaRenovacion and a.sucursal= :sucursal")
	List<Aviso> findAvisoFechaAndSucursal(@Param("fechaRenovacion") FechasRenovacion fechaRenovacion,@Param("sucursal") Sucursal sucursal);

}
