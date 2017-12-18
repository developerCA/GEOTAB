package com.tecnolpet.ot.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tecnolpet.ot.dto.RespuestaDto;
import com.tecnolpet.ot.service.MigracionService;

@RestController
@RequestMapping("api/migracion")
public class MigracionController {

	private final MigracionService migracionService;

	@Autowired
	public MigracionController(MigracionService migracionService) {
		this.migracionService = migracionService;
	}

	@RequestMapping(value = "/clientes", method = RequestMethod.PUT)
	public RespuestaDto registrarClientes() {
		RespuestaDto respuesta = new RespuestaDto();

		try {
			migracionService.migrarClientes();
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("Ok");

		} catch (Exception ex) {
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}

		return respuesta;
	}

	@RequestMapping(value = "/contactos", method = RequestMethod.PUT)
	public RespuestaDto registrarContactos() {
		RespuestaDto respuesta = new RespuestaDto();

		try {
			migracionService.migrarContactos();
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("Ok");
		} catch (Exception ex) {
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}

		return respuesta;
	}
	
	@RequestMapping(value = "/productos", method = RequestMethod.PUT)
	public RespuestaDto registrarProductos() {
		RespuestaDto respuesta = new RespuestaDto();

		try {
			migracionService.migrarProductos();
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("Ok");
		} catch (Exception ex) {
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}

		return respuesta;
	}
	
	@RequestMapping(value = "/productosRenovacion", method = RequestMethod.PUT)
	public RespuestaDto registrarProductosRenovacion() {
		RespuestaDto respuesta = new RespuestaDto();

		try {
			migracionService.migrarProductosRenovacion();
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("Ok");
		} catch (Exception ex) {
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}

		return respuesta;
	}

}
