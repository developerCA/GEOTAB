/**
 * 
 */
package com.tecnolpet.ot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tecnolpet.ot.constant.SotApp;
import com.tecnolpet.ot.dto.ProductoDto;
import com.tecnolpet.ot.model.Catalogo;
import com.tecnolpet.ot.model.Categoria;
import com.tecnolpet.ot.model.Cliente;
import com.tecnolpet.ot.model.PerfilEmpresa;
import com.tecnolpet.ot.model.Producto;
import com.tecnolpet.ot.model.Tarea;
import com.tecnolpet.ot.model.TipoProducto;
import com.tecnolpet.ot.repository.CatalogoRepository;
import com.tecnolpet.ot.repository.CategoriaRepository;
import com.tecnolpet.ot.repository.ClienteRepository;
import com.tecnolpet.ot.repository.PerfilEmpresaRepository;
import com.tecnolpet.ot.repository.ProductoRepository;
import com.tecnolpet.ot.repository.TareaRepository;
import com.tecnolpet.ot.repository.TipoProductoRepository;
import com.tecnolpet.ot.seguridad.UsuarioAuthenticate;

/**
 * @author administrador
 *
 */
@Service
public class ProductoService {

	@Autowired
	private ProductoRepository productoRepository;

	@Autowired
	private Environment env;

	@Autowired
	private CatalogoRepository catalogoRepository;

	@Autowired
	private TipoProductoRepository tipoProductoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private TareaRepository tareaRepository;
	
	@Autowired
	private PerfilEmpresaRepository perfilEmpresaRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public List<ProductoDto> getProductosActivos(Integer idCliente) {
		Cliente cliente = clienteRepository.findOne(idCliente);
		List<Producto> productos = productoRepository
				.findProductoByCatalogoAndCliente(catalogoRepository
						.findCatalogoBySigla("ACTIVO").get(0), cliente);
		ProductoDto productoDto;
		List<ProductoDto> listaDto = new ArrayList<ProductoDto>();
		for (Producto producto : productos) {
			productoDto = new ProductoDto();
			productoDto.setProducto(producto);
			if (producto.getRenovable()) {

				productoDto.setProductoRenovacion(producto.getProducto());
			}
			listaDto.add(productoDto);
		}
		return listaDto;
	}

	public List<Producto> getProductos(Integer idCliente) {
		Cliente cliente = clienteRepository.findOne(idCliente);
		List<Producto> productos = productoRepository
				.findProductoByCatalogoAndCliente(catalogoRepository
						.findCatalogoBySigla("ACTIVO").get(0), cliente);

		return productos;
	}
	
	public List<Categoria> traerCategorias(UsuarioAuthenticate usuario){
		PerfilEmpresa perfilEmpresa=perfilEmpresaRepository.findOne(usuario.getPerfil_empresa());
		
		List<Categoria > lista=categoriaRepository.findCategoriaByEstadoAndEmpresa(true, perfilEmpresa.getEmpresa());
		
		return lista;
	}

	public List<TipoProducto> getTipoProductos() {
		List<TipoProducto> tipoProductos = tipoProductoRepository.findAll();
		return tipoProductos;
	}

	public List<Producto> getProductosActualizables() {
		List<Producto> productos = productoRepository.findByActualizables();
		return productos;
	}

	@Transactional
	public void guardar(final Producto productoDto) throws Exception {

		productoDto.setCatalogo(catalogoRepository
				.findCatalogoBySigla("ACTIVO").get(0));

		productoRepository.save(productoDto);

	}

	@Transactional
	public void guardarTipo(final TipoProducto tipoProducto) throws Exception {

		tipoProductoRepository.save(tipoProducto);

	}

	@Transactional
	public void guardarServicio(final Tarea tarea,UsuarioAuthenticate usuario) throws Exception {
		Catalogo catalogo=catalogoRepository.findCatalogoBySigla(SotApp.EstadosGenerales.ACTIVO).get(0);
		PerfilEmpresa perfilEmpresa=perfilEmpresaRepository.findOne(usuario.getPerfil_empresa());
		tarea.setCatalogo(catalogo);
		tarea.setEmpresa(perfilEmpresa.getEmpresa());
		tareaRepository.save(tarea);

	}
	
	@Transactional
	public void eliminarServicio(final Tarea tarea) throws Exception {

		Catalogo catalogo=catalogoRepository.findCatalogoBySigla(SotApp.EstadosGenerales.ELIMINADO).get(0);
		tarea.setCatalogo(catalogo);
		tareaRepository.save(tarea);

	}

	@Transactional
	public void actualizar(Producto producto) throws Exception {

		productoRepository.save(producto);

	}

	public void eliminar(final Producto productoDto) {

		productoDto.setCatalogo(catalogoRepository
				.findCatalogoBySigla("ELIMIN").get(0));
		productoRepository.save(productoDto);
	}
}
