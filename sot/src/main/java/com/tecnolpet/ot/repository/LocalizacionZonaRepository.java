package com.tecnolpet.ot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.tecnolpet.ot.model.Dispositivo;
import com.tecnolpet.ot.model.LocalizacionZona;

public interface LocalizacionZonaRepository extends JpaRepository<LocalizacionZona, Integer> {

	@Query(value= "select nextval('proceso_localizacion_seq') ",nativeQuery=true)
	public Integer traerNumeroProceso();
	
	@Transactional
	@Modifying()
    @Query("UPDATE LocalizacionZona lz SET lz.estado = 'PRO' WHERE lz.proceso = :proceso")
    int procesarLocalizacionZona(@Param("proceso") Integer proceso);
	
	@Transactional
	@Modifying()
    @Query("DELETE FROM LocalizacionZona lz WHERE lz.dispositivo = :dispositivo")
    int eliminarLocalizacionZona(@Param("dispositivo") Dispositivo dispositivo);
	
}
