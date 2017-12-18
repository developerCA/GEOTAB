package com.tecnolpet.ot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tecnolpet.ot.model.Seguimiento;
import com.tecnolpet.ot.model.TareaDetalleNotaPedido;

public interface SeguimientoRepository extends JpaRepository<Seguimiento, Integer>{

	public List<Seguimiento> findByTareaDetalleNotaPedidoOrderByIdDesc(TareaDetalleNotaPedido tarea);
	
	@Query(value= "select * from seguimiento where codigo_tarea_detalle_nota_pedido= :tarea and codigo = (select max(codigo) from seguimiento where codigo_tarea_detalle_nota_pedido= :tarea)",nativeQuery=true)
	public Seguimiento findFinalByTarea(@Param("tarea") Integer tarea);
	
}
