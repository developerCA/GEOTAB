package com.tecnolpet.ot.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tecnolpet.ot.geotab.dto.DispositivoTableroDto;
import com.tecnolpet.ot.geotab.dto.ZonaTableroDto;
import com.tecnolpet.ot.model.VTablero;

public interface VTableroRepository extends JpaRepository<VTablero, Integer> {

	List<VTablero> findByCodigoRutaAndFechaOrderByHoraSalidaAsc(Integer codigoRuta,Date fecha);
	
	@Query(value = "select distinct new com.tecnolpet.ot.geotab.dto.ZonaTableroDto( v.codigoZona,v.zona,v.orden,v.tiempo) from VTablero v where v.fecha= :fecha and v.codigoRuta= :codigoRuta order by v.orden ")
	List<ZonaTableroDto> findByCodigoRutaAndFechaZonas(@Param("fecha") Date fecha,@Param("codigoRuta") Integer codigoRuta);
	
	@Query(value = "select distinct new com.tecnolpet.ot.geotab.dto.DispositivoTableroDto( v.codigoDispositivo,v.dispositivo,v.numeroVuelta) from VTablero v where v.fecha= :fecha and v.codigoRuta= :codigoRuta   ")
	List<DispositivoTableroDto> findByCodigoRutaAndFechaDispositivos(@Param("fecha") Date fecha,@Param("codigoRuta") Integer codigoRuta);
	
}
