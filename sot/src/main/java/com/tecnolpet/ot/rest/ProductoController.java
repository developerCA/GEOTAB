/**
 * 
 */
package com.tecnolpet.ot.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tecnolpet.ot.dto.ProductoDto;
import com.tecnolpet.ot.dto.RespuestaDto;
import com.tecnolpet.ot.model.Categoria;
import com.tecnolpet.ot.model.Producto;
import com.tecnolpet.ot.model.Tarea;
import com.tecnolpet.ot.model.TipoProducto;
import com.tecnolpet.ot.seguridad.UsuarioAuthenticate;
import com.tecnolpet.ot.service.ProductoService;
import com.tecnolpet.ot.service.TareaService;

/**
 * @author Armando Ariel Suárez Pons
 *
 */
@RestController
@RequestMapping("api/producto")
public class ProductoController {

	private final ProductoService productoService;
	private final TareaService tareaService;

	@Autowired
	public ProductoController(ProductoService productoService,
			TareaService tareaService) {
		this.productoService = productoService;
		this.tareaService = tareaService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<ProductoDto> traerProductosActivos(
			@RequestParam Integer idCliente) {
		return productoService.getProductosActivos(idCliente);
	}

	
	@RequestMapping(value = "listado", method = RequestMethod.GET)
	public List<Producto> traerProductos(@RequestParam Integer idCliente) {
		return productoService.getProductos(idCliente);
	}

	@RequestMapping(value = "categorias", method = RequestMethod.GET)
	public List<Categoria> traerCategorias(@AuthenticationPrincipal UsuarioAuthenticate usuario) {
		return productoService.traerCategorias(usuario);
	}
	@RequestMapping(value = "/tipoProducto", method = RequestMethod.GET)
	public List<TipoProducto> traerTipoProductos() {
		return productoService.getTipoProductos();
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public RespuestaDto guardarProducto(@RequestBody Producto productoDto) {
		RespuestaDto respuestaDto = new RespuestaDto();

		try {

			productoService.guardar(productoDto);
			respuestaDto.setEstado(Boolean.TRUE);
			respuestaDto
					.setMensaje("El producto se ha insertado exitosamente...");
			respuestaDto.setObjeto(productoDto);
		} catch (Exception ex) {
			respuestaDto.setEstado(Boolean.FALSE);
			respuestaDto.setMensaje(ex.getMessage());
		}

		return respuestaDto;
	}

	@RequestMapping(value = "/actualizar", method = RequestMethod.PUT)
	public RespuestaDto actualizarProducto(@RequestBody Producto productoDto) {
		RespuestaDto respuestaDto = new RespuestaDto();

		try {
			productoService.guardar(productoDto);
			respuestaDto.setEstado(Boolean.TRUE);
			respuestaDto
					.setMensaje("El producto se ha actualizado correctamente..");
			respuestaDto.setObjeto(productoDto);
		} catch (Exception ex) {
			respuestaDto.setEstado(Boolean.FALSE);
			respuestaDto.setMensaje(ex.getMessage());
		}

		return respuestaDto;
	}

	@RequestMapping(value = "/crear", method = RequestMethod.PUT)
	public RespuestaDto crearProducto(@RequestBody Producto productoDto) {
		RespuestaDto respuestaDto = new RespuestaDto();

		try {

			productoService.guardar(productoDto);
			respuestaDto.setEstado(Boolean.TRUE);
			respuestaDto
					.setMensaje("El producto se ha actualizado correctamente..");
			respuestaDto.setObjeto(productoDto);
		} catch (Exception ex) {
			respuestaDto.setEstado(Boolean.FALSE);
			respuestaDto.setMensaje(ex.getMessage());
		}

		return respuestaDto;
	}

	@RequestMapping(value = "/crearTipo", method = RequestMethod.PUT)
	public RespuestaDto crearTipoProducto(@RequestBody TipoProducto tipoProducto) {
		RespuestaDto respuestaDto = new RespuestaDto();

		try {

			productoService.guardarTipo(tipoProducto);
			respuestaDto.setEstado(Boolean.TRUE);
			respuestaDto
					.setMensaje("El producto se ha actualizado correctamente..");
			respuestaDto.setObjeto(tipoProducto);
		} catch (Exception ex) {
			respuestaDto.setEstado(Boolean.FALSE);
			respuestaDto.setMensaje(ex.getMessage());
		}

		return respuestaDto;
	}

	@RequestMapping(value = "/crearServicio", method = RequestMethod.PUT)
	public RespuestaDto crearTarea(@AuthenticationPrincipal UsuarioAuthenticate usuario,@RequestBody Tarea tarea) {
		RespuestaDto respuestaDto = new RespuestaDto();

		try {

			productoService.guardarServicio(tarea,usuario);
			respuestaDto.setEstado(Boolean.TRUE);
			respuestaDto
					.setMensaje("El servicio se ha actualizado correctamente..");
			respuestaDto.setObjeto(tarea);
		} catch (Exception ex) {
			respuestaDto.setEstado(Boolean.FALSE);
			respuestaDto.setMensaje(ex.getMessage());
		}

		return respuestaDto;
	}
	
	@RequestMapping(value = "/eliminarServicio", method = RequestMethod.PUT)
	public RespuestaDto eliminarTarea(@RequestBody Tarea tarea) {
		RespuestaDto respuestaDto = new RespuestaDto();

		try {

			productoService.eliminarServicio(tarea);
			respuestaDto.setEstado(Boolean.TRUE);
			respuestaDto
					.setMensaje("El servicio se ha actualizado correctamente..");
			respuestaDto.setObjeto(tarea);
		} catch (Exception ex) {
			respuestaDto.setEstado(Boolean.FALSE);
			respuestaDto.setMensaje(ex.getMessage());
		}

		return respuestaDto;
	}


	@RequestMapping(value = "/eliminar", method = RequestMethod.PUT)
	public RespuestaDto eliminarProducto(@RequestBody Producto productoDto) {
		RespuestaDto respuestaDto = new RespuestaDto();

		try {
			productoService.eliminar(productoDto);
			respuestaDto.setEstado(Boolean.TRUE);
			respuestaDto
					.setMensaje("El equipo se ha eliminado correctamente..");
			respuestaDto.setObjeto(productoDto);
		} catch (Exception ex) {
			respuestaDto.setEstado(Boolean.FALSE);
			respuestaDto.setMensaje(ex.getMessage());
		}
		return respuestaDto;
	}

	@RequestMapping(value = "/tareas", method = RequestMethod.GET)
	public List<Tarea> traerTareas(@AuthenticationPrincipal UsuarioAuthenticate usuario) {
		return tareaService.traerTareas(usuario);
	}

}
