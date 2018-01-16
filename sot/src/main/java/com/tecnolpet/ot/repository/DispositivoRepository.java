package com.tecnolpet.ot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tecnolpet.ot.model.Dispositivo;
import com.tecnolpet.ot.model.Ruta;

public interface DispositivoRepository extends JpaRepository<Dispositivo, Integer>{



	List<Dispositivo> findByRuta(Ruta ruta);
	
	List<Dispositivo> findByCodigoDispositivo(String codigoDispositivo);
	
	@Query("SELECT d FROM Dispositivo d WHERE d.ruta = :ruta and d.codigoDispositivo = :codigo ")
	Dispositivo findDispositivo(@Param("ruta") Ruta ruta,@Param("codigo") String codigo);
	
}
