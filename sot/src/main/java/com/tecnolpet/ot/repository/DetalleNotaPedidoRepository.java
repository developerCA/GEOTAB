package com.tecnolpet.ot.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tecnolpet.ot.model.Catalogo;
import com.tecnolpet.ot.model.DetalleNotaPedido;
import com.tecnolpet.ot.model.NotaPedido;

public interface DetalleNotaPedidoRepository extends JpaRepository<DetalleNotaPedido, Integer>{

	@Query("SELECT d FROM DetalleNotaPedido d WHERE d.notaPedido= :notaPedido and d.catalogo= :registrado  ORDER BY d.id")
	public List<DetalleNotaPedido> findDetalleNotaPedido(@Param("notaPedido") NotaPedido notaPedido,@Param("registrado") Catalogo registrado);
	
}
