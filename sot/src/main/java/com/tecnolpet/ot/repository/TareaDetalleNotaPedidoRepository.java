package com.tecnolpet.ot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tecnolpet.ot.model.DetalleNotaPedido;
import com.tecnolpet.ot.model.Tarea;
import com.tecnolpet.ot.model.TareaDetalleNotaPedido;

public interface TareaDetalleNotaPedidoRepository extends JpaRepository<TareaDetalleNotaPedido, Integer>{

	@Query("SELECT td.tarea FROM TareaDetalleNotaPedido td WHERE td.detalleNotaPedido= :detalleNotaPedido")
	List<Tarea> findTareasDetalleNotaPedido(@Param("detalleNotaPedido") DetalleNotaPedido detalleNotaPedido);
	
	@Query("SELECT td FROM TareaDetalleNotaPedido td WHERE td.detalleNotaPedido= :detalleNotaPedido")
	List<TareaDetalleNotaPedido> findTareas(@Param("detalleNotaPedido") DetalleNotaPedido detalleNotaPedido);
	
	@Query("SELECT td FROM TareaDetalleNotaPedido td WHERE td.detalleNotaPedido= :detalleNotaPedido and cast(td.codigoReporte as string) like :codigoReporte")
	List<TareaDetalleNotaPedido> findTareasReporte(@Param("detalleNotaPedido") DetalleNotaPedido detalleNotaPedido,@Param("codigoReporte") String codigoReporte);
}
