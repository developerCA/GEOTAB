/**
 * 
 */
package com.tecnolpet.ot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tecnolpet.ot.model.Categoria;
import com.tecnolpet.ot.model.Empresa;

/**
 * @author administrador
 *
 */
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
	
	public List<Categoria> findCategoriaByEstadoAndEmpresa(Boolean estado,Empresa empresa);
}
