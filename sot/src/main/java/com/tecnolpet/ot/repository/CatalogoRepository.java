/**
 * 
 */
package com.tecnolpet.ot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tecnolpet.ot.model.Catalogo;

/**
 * @author administrador
 *
 */
public interface CatalogoRepository extends JpaRepository<Catalogo, Integer>{
	List<Catalogo> findCatalogoBySigla(String sigla);
}
