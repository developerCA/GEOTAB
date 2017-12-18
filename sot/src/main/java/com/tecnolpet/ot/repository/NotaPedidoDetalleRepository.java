/**
 * 
 */
package com.tecnolpet.ot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tecnolpet.ot.model.Catalogo;
import com.tecnolpet.ot.model.DetalleNotaPedido;
import com.tecnolpet.ot.model.NotaPedido;

/**
 * @author administrador
 *
 */
public interface NotaPedidoDetalleRepository extends JpaRepository<DetalleNotaPedido, Integer>{
	
	List<DetalleNotaPedido> findDetalleNotaPedidoByCatalogo(Catalogo idCatalogo);
	
	@Query("SELECT dp FROM DetalleNotaPedido dp WHERE dp.notaPedido = ? and dp.catalogo.sigla = 'REGIST'")
	List<DetalleNotaPedido> findDetalleNotaPedidoByNotaPedidoAndCatalogo(NotaPedido notaPedido);

}
