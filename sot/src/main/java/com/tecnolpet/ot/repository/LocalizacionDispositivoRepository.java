package com.tecnolpet.ot.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.tecnolpet.ot.model.Dispositivo;
import com.tecnolpet.ot.model.LocalizacionDispositivo;
import com.tecnolpet.ot.model.Zona;

public interface LocalizacionDispositivoRepository extends JpaRepository<LocalizacionDispositivo, Long> {

	List<LocalizacionDispositivo> findByDispositivoAndZonaAndFechaAndNumeroVuelta(Dispositivo dispositivo, Zona zona,Date fecha,Integer numeroVuelta);

	List<LocalizacionDispositivo> findByDispositivoAndZonaAndNumeroVuelta(Dispositivo dispositivo, Zona zona,Integer numeroVuelta);
	
	@Transactional
	@Modifying()
    @Query("DELETE FROM LocalizacionDispositivo ld WHERE ld.dispositivo = :dispositivo")
    int eliminarLocalizacionDipositivo(@Param("dispositivo") Dispositivo dispositivo);
	
	
}
