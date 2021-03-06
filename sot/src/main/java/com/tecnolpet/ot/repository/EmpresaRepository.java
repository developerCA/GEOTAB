package com.tecnolpet.ot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tecnolpet.ot.model.Empresa;



public interface EmpresaRepository extends JpaRepository<Empresa, Integer>{
	
	public List<Empresa> findByEstado(Boolean estado);
	
	public Empresa findByCodigoCooperativa(String codigoCooperativa);

}

