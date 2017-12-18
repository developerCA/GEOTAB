package com.tecnolpet.ot.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tecnolpet.ot.constant.SotApp;
import com.tecnolpet.ot.dto.ActualizacionProductoDto;
import com.tecnolpet.ot.model.Catalogo;
import com.tecnolpet.ot.model.Producto;
import com.tecnolpet.ot.model.ProductoActualizacion;
import com.tecnolpet.ot.model.ProductoActualizacionDetalle;
import com.tecnolpet.ot.repository.CatalogoRepository;
import com.tecnolpet.ot.repository.ClienteRepository;
import com.tecnolpet.ot.repository.ProductoActualizacionRepository;
import com.tecnolpet.ot.repository.ProductoRepository;

@Service
public class ProductoActualizacionService {

	@Autowired
	ProductoActualizacionRepository productoActualizacionRepository;
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	CatalogoRepository catalogoRepository;
	@Autowired
	ProductoRepository productoRepository;
	
	@Autowired
	ProductoActualizacionDetalleService productoActualizacionDetalleService;
	@Autowired
	ProductoService productoService;
	
	@Transactional
	public void guardar(
			ProductoActualizacion productoActualizacion,
			List<ProductoActualizacionDetalle> listaProductoActualizacionDetalle,
			Integer idUsuario) throws Exception {

		Producto producto = productoRepository.findOne(productoActualizacion
				.getProducto().getId());
		Integer version = producto.getNumeroActualizacion() + 1;
		Catalogo estado = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosActualizacionProductos.GENEREADA).get(0);

		if (productoActualizacion.getId() == null) {
			productoActualizacion.setCreadoPor(idUsuario);
			productoActualizacion.setFechaCreacion(new Timestamp(Calendar
					.getInstance().getTime().getTime()));
		}

		productoActualizacion.setActualizadoPor(idUsuario);
		productoActualizacion.setFechaActualizacion(new Timestamp(Calendar
				.getInstance().getTime().getTime()));

		productoActualizacion.setEstado(estado);
		productoActualizacion
				.setProductoActualizacionDetalles(listaProductoActualizacionDetalle);
		productoActualizacionRepository.save(productoActualizacion);

		this.registrarDetalles(productoActualizacion, estado);

		producto.setNumeroActualizacion(version);
		productoService.actualizar(producto);

	}

	private void registrarDetalles(ProductoActualizacion productoActualizacion,
			Catalogo estado) {

		for (ProductoActualizacionDetalle detalle : productoActualizacion
				.getProductoActualizacionDetalles()) {

			detalle.setProductoActualizacion(productoActualizacion);
			detalle.setEstado(estado);
			detalle.setCodigoBarras(productoActualizacionDetalleService
					.generarCodigoBarras(detalle));
			productoActualizacionDetalleService.guardar(detalle,
					productoActualizacion.getActualizadoPor());

		}

	}

	public List<ActualizacionProductoDto> traerGeneradas() {

		Catalogo estado = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosActualizacionProductos.GENEREADA).get(0);
		List<ProductoActualizacion> productosActualizacion = productoActualizacionRepository
				.findByEstado(estado);
		List<ActualizacionProductoDto> generadas = new ArrayList<ActualizacionProductoDto>();
		ActualizacionProductoDto dto = null;
		List<ProductoActualizacionDetalle> detalles = null;

		for (ProductoActualizacion pa : productosActualizacion) {

			dto = new ActualizacionProductoDto();
			dto.setProductoActualizacion(pa);
			detalles = productoActualizacionDetalleService.traerGeneradasYPorActualizacionDetalle(pa);
			dto.setListaProductoActualizacionDetalle(detalles);
			generadas.add(dto);

		}

		return generadas;

	}

	public List<ActualizacionProductoDto> traerGeneradasPorFiltros(String codigoBarras, Integer idCliente, Integer idProducto) {

		Catalogo estado = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosActualizacionProductos.GENEREADA).get(0);
		Catalogo estadoSuscripcion = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosSuscripciones.ACTIVADEFINITIVA).get(0);
		
		List<ProductoActualizacion> productosActualizacion = new ArrayList<ProductoActualizacion>();
		List<ActualizacionProductoDto> generadas = new ArrayList<ActualizacionProductoDto>();
		List<ProductoActualizacionDetalle> detalles = null;
		ActualizacionProductoDto dto = null;
		
		Integer cliente = idCliente == -1 ? 0 : idCliente;
		Integer producto = idProducto == -1 ? 0 : idProducto;
		
		if(!(codigoBarras.isEmpty())){
			productosActualizacion = productoActualizacionRepository.findByCodigoBarras(codigoBarras, estado, estadoSuscripcion);
			
			System.out.println(productosActualizacion.size() + " -----");
			
			for (ProductoActualizacion pa : productosActualizacion) {

				dto = new ActualizacionProductoDto();
				dto.setProductoActualizacion(pa);
				detalles = productoActualizacionDetalleService.traerPorActualizacionDetalle(codigoBarras);
				dto.setListaProductoActualizacionDetalle(detalles);
				generadas.add(dto);

			}
		}else{			
			productosActualizacion = productoActualizacionRepository.findGeneradasByCodigoClienteProducto(producto, cliente, estado, estadoSuscripcion);
			for (ProductoActualizacion pa : productosActualizacion) {

				dto = new ActualizacionProductoDto();
				dto.setProductoActualizacion(pa);
				detalles = productoActualizacionDetalleService.traerPorActualizacionDetalle(pa, producto, cliente);
				dto.setListaProductoActualizacionDetalle(detalles);
				generadas.add(dto);

			}
		}					
		
		return generadas;

	}

}
