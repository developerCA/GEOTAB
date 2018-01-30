/**
 * 
 */
package com.tecnolpet.ot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecnolpet.ot.model.DetalleNotaPedido;
import com.tecnolpet.ot.model.NotaPedido;
import com.tecnolpet.ot.repository.CatalogoRepository;
import com.tecnolpet.ot.repository.NotaPedidoDetalleRepository;

/**
 * @author administrador
 *
 */
@Service
public class NotaPedidoDetalleService {

	@Autowired
	private NotaPedidoDetalleRepository notaPedidoDetalleRepository;
	
	@Autowired
	private CatalogoRepository catalogoRepository;	
	
		
	
	public List<DetalleNotaPedido> findDetalleNotaPedido(){
		return notaPedidoDetalleRepository.findDetalleNotaPedidoByCatalogo(catalogoRepository.findCatalogoBySigla("ACTIVO").get(0));
	}
	
	public List<DetalleNotaPedido> findDetalleNotaPedidoPorNotaPedidoId(Integer id){
		NotaPedido notaPedido = new NotaPedido();
		notaPedido.setId(id);
		List<DetalleNotaPedido> detalles = notaPedidoDetalleRepository.findDetalleNotaPedidoByNotaPedidoAndCatalogo(notaPedido); 
		
		
		return detalles;
	}
	
	public void guardar(DetalleNotaPedido detalleNotaPedido){
		detalleNotaPedido.setCatalogo(catalogoRepository.findCatalogoBySigla("REGIST").get(0));
		notaPedidoDetalleRepository.save(detalleNotaPedido);	
	}
	
	public void eliminar(final DetalleNotaPedido detalleNotaPedido){		
		detalleNotaPedido.setCatalogo(catalogoRepository.findCatalogoBySigla("CANCEL").get(0));
		notaPedidoDetalleRepository.save(detalleNotaPedido);
	}
	
}
