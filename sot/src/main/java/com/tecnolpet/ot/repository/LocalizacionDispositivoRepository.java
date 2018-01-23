package com.tecnolpet.ot.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tecnolpet.ot.model.Dispositivo;
import com.tecnolpet.ot.model.LocalizacionDispositivo;
import com.tecnolpet.ot.model.Zona;

public interface LocalizacionDispositivoRepository extends JpaRepository<LocalizacionDispositivo, Integer> {

	List<LocalizacionDispositivo> findByProcesoAndDispositivoAndZonaAndFecha(Integer proceso,Dispositivo dispositivo, Zona zona,Date fecha);
}
