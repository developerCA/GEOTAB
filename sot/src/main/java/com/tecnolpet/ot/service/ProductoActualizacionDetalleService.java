package com.tecnolpet.ot.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tecnolpet.ot.constant.SotApp;
import com.tecnolpet.ot.model.Catalogo;
import com.tecnolpet.ot.model.ProductoActualizacion;
import com.tecnolpet.ot.model.ProductoActualizacionDetalle;
import com.tecnolpet.ot.repository.CatalogoRepository;
import com.tecnolpet.ot.repository.ProductoActualizacionDetalleRepository;

@Service
public class ProductoActualizacionDetalleService {

	@Autowired
	ProductoActualizacionDetalleRepository productoActualizacionDetalleRepository;	
	
	@Autowired
	CatalogoRepository catalogoRepository;	

	public void guardar(ProductoActualizacionDetalle productoActualizacionDetalle, Integer idUsuario) {

		if (productoActualizacionDetalle.getId() == 0) {
			productoActualizacionDetalle.setCreadoPor(idUsuario);
			productoActualizacionDetalle.setFechaCreacion(new Timestamp(Calendar
					.getInstance().getTime().getTime()));
		}

		productoActualizacionDetalle.setActualizadoPor(idUsuario);
		productoActualizacionDetalle.setFechaActualizacion(new Timestamp(Calendar
				.getInstance().getTime().getTime()));

		productoActualizacionDetalleRepository.save(productoActualizacionDetalle);

	}
	
	@Transactional
	public void procesar(ProductoActualizacionDetalle productoActualizacionDetalle, boolean enviado, String observacion, Integer idUsuario){
		
		String sigla = enviado?"ACTPRO":"ACTBAJ";
		Catalogo estado = catalogoRepository.findCatalogoBySigla(sigla).get(0);
		Catalogo estadoGenerado = catalogoRepository.findCatalogoBySigla(SotApp.EstadosActualizacionProductos.GENEREADA).get(0);
		productoActualizacionDetalle.setActualizadoPor(idUsuario);
		productoActualizacionDetalle.setFechaActualizacion(new Timestamp(Calendar
				.getInstance().getTime().getTime()));
		
		productoActualizacionDetalle.setEstado(estado);
		productoActualizacionDetalle.setObservacion(observacion);
		
		productoActualizacionDetalleRepository.save(productoActualizacionDetalle);
		
		//Actualiza los detalles similares al procesado
		List<ProductoActualizacionDetalle> similares = productoActualizacionDetalleRepository.findBySuscripcionAndVersionAndEstado(productoActualizacionDetalle.getSuscripcion(), productoActualizacionDetalle.getVersion(), estadoGenerado);
		
		System.out.println(similares.size() + " ++++ " + estadoGenerado.getId() + " ::::: " + productoActualizacionDetalle.getSuscripcion().getId() + "  >>> " + productoActualizacionDetalle.getVersion());
		
		for(ProductoActualizacionDetalle pad : similares){
			pad.setEstado(estado);
			pad.setActualizadoPor(idUsuario);
			pad.setObservacion(productoActualizacionDetalle.getObservacion() + " |SIMILAR|");
			pad.setFechaActualizacion(new Timestamp(Calendar
					.getInstance().getTime().getTime()));
			productoActualizacionDetalleRepository.save(pad);
		}
		
	}
	
	public String generarCodigoBarras(ProductoActualizacionDetalle productoActualizacionDetalle){
		
		String codigo = null;
		
		codigo = String.format("%04d.", (int)(Math.random() * 9998 + 1));
		codigo += String.format("%05d.", productoActualizacionDetalle.getSuscripcion().getId());
		codigo += String.format("%05d.", productoActualizacionDetalle.getProductoActualizacion().getId());
		codigo += String.format("%03d", productoActualizacionDetalle.getVersion());
		
		return codigo;
		
	}
	
	public List<ProductoActualizacionDetalle> traerPorActualizacionDetalle(ProductoActualizacion productoActualizacion, Integer producto, Integer cliente) {
		
		Catalogo estado = catalogoRepository.findCatalogoBySigla(SotApp.EstadosActualizacionProductos.GENEREADA).get(0);
		Catalogo estadoSuscripcion = catalogoRepository.findCatalogoBySigla(SotApp.EstadosSuscripciones.ACTIVADEFINITIVA).get(0);
		List<ProductoActualizacionDetalle> detalle = productoActualizacionDetalleRepository.findByProductoActualizacion(productoActualizacion.getId(), estado.getId(), producto, cliente, estadoSuscripcion.getId());
		return detalle;  
		
	}
	
	public List<ProductoActualizacionDetalle> traerPorActualizacionDetalle(String codigoBarras) {
		
		Catalogo estado = catalogoRepository.findCatalogoBySigla(SotApp.EstadosActualizacionProductos.GENEREADA).get(0);
		List<ProductoActualizacionDetalle> detalle = productoActualizacionDetalleRepository.findByCodigoBarrasAndEstado(codigoBarras, estado);
		return detalle;  
		
	}
	
	public List<ProductoActualizacionDetalle> traerGeneradasYPorActualizacionDetalle(ProductoActualizacion productoActualizacion) {
		
		Catalogo estado = catalogoRepository.findCatalogoBySigla(SotApp.EstadosActualizacionProductos.GENEREADA).get(0);
		List<ProductoActualizacionDetalle> detalle = productoActualizacionDetalleRepository.findByProductoActualizacionAndEstado(productoActualizacion, estado);
		return detalle;  
		
	}

}
