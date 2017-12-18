package com.tecnolpet.ot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tecnolpet.ot.model.Catalogo;
import com.tecnolpet.ot.model.Categoria;
import com.tecnolpet.ot.model.CostoServicio;
import com.tecnolpet.ot.model.Tarea;

public interface CostoServicioRepository extends JpaRepository<CostoServicio, Integer>{

	@Query("SELECT c FROM CostoServicio c WHERE c.catalogo= :estado and c.categoria= :categoria  ORDER BY c.tarea.nombre")
	public List<CostoServicio> findCostosByTipoCategoria(@Param("estado") Catalogo estado,@Param("categoria") Categoria categoria);

	@Query("SELECT c FROM CostoServicio c WHERE c.catalogo= :estado and c.categoria= :categoria and c.tarea= :tarea ORDER BY c.tarea.nombre")
	public CostoServicio findCostosByTipoCategoriaAndTarea(@Param("estado") Catalogo estado,@Param("categoria") Categoria categoria,@Param("tarea") Tarea tarea);

}
