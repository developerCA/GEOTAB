package com.tecnolpet.ot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tecnolpet.ot.model.Instrumento;
import com.tecnolpet.ot.model.Ruta;

public interface InstrumentoRepository extends JpaRepository<Instrumento, Integer>{



	List<Instrumento> findByRuta(Ruta ruta);
	
	@Query("SELECT d FROM Instrumento d WHERE d.ruta = :ruta and d.codigoDispositivo = :codigo ")
	Instrumento findDispositivo(@Param("ruta") Ruta ruta,@Param("codigo") String codigo);
	
}
