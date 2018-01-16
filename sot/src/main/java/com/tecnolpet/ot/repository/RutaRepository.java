package com.tecnolpet.ot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tecnolpet.ot.model.Empresa;
import com.tecnolpet.ot.model.Ruta;

public interface RutaRepository  extends JpaRepository<Ruta, Integer>{ 

	public List<Ruta> findByEmpresa(Empresa empresa);
	
	public Ruta findByEmpresaAndIdentificador(Empresa empresa,String identificador);
	
	public Ruta findByIdentificador(String identificador);
	
}
