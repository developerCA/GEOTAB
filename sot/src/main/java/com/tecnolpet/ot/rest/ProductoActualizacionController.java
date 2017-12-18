package com.tecnolpet.ot.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tecnolpet.ot.dto.ActualizacionProductoDto;
import com.tecnolpet.ot.dto.RespuestaDto;
import com.tecnolpet.ot.model.Producto;
import com.tecnolpet.ot.seguridad.UsuarioAuthenticate;
import com.tecnolpet.ot.service.ProductoActualizacionService;
import com.tecnolpet.ot.service.ProductoService;
import com.tecnolpet.ot.service.SuscripcionService;

@RestController
@RequestMapping("/api/productoactualizacion")
public class ProductoActualizacionController {

	private final ProductoActualizacionService productoActualizacionService;
	private final ProductoService productoService;
	private final SuscripcionService suscripcionService;

	@Autowired
	public ProductoActualizacionController(
			ProductoActualizacionService productoActualizacionService,
			ProductoService productoService,
			SuscripcionService suscripcionService) {
		this.productoActualizacionService = productoActualizacionService;
		this.productoService = productoService;
		this.suscripcionService = suscripcionService;
	}

	@RequestMapping(value = "/productos", method = RequestMethod.GET)
	public List<Producto> traerProductosActualizables() {
		return productoService.getProductosActualizables();
	}

	@RequestMapping(value = "/suscripciones", method = RequestMethod.PUT)
	public ActualizacionProductoDto traerSuscripcionesPorProducto(
			@RequestBody int idSuscripcion) {
		return suscripcionService.traerPorProductoActualizable(idSuscripcion);
	}

	@RequestMapping(value = "/actualizar", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public RespuestaDto actualizarProducto(
			@RequestBody ActualizacionProductoDto dto,
			@AuthenticationPrincipal UsuarioAuthenticate usuario) {

		RespuestaDto respuesta = new RespuestaDto();
		
		try {

			productoActualizacionService.guardar(dto.getProductoActualizacion(), dto.getListaProductoActualizacionDetalle(), usuario.getId());
			
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("Ok");
			respuesta.setObjeto(dto.getProductoActualizacion());

		} catch (Exception ex) {
			respuesta.setEstado(false);
			respuesta.setMensaje(ex.getMessage());
		}
		
		return respuesta;
		
	}

}
