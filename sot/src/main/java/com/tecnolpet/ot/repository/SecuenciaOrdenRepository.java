package com.tecnolpet.ot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tecnolpet.ot.model.Empresa;
import com.tecnolpet.ot.model.Secuencia;
import com.tecnolpet.ot.model.SecuenciaOrden;

public interface SecuenciaOrdenRepository extends
		JpaRepository<SecuenciaOrden, Integer> {

	public List<SecuenciaOrden> findByEmpresaAndSecuencia(Empresa empresa,
			Secuencia secuencia);
	
	
}
