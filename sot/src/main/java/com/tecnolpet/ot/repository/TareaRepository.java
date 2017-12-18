package com.tecnolpet.ot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tecnolpet.ot.model.Catalogo;
import com.tecnolpet.ot.model.Empresa;
import com.tecnolpet.ot.model.Tarea;

public interface TareaRepository extends JpaRepository<Tarea, Integer>{

	
	List<Tarea> findByCatalogoAndEmpresa(Catalogo catalogo,Empresa empresa);

}
