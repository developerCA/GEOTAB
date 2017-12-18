package com.tecnolpet.ot.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tecnolpet.ot.dto.RespuestaDto;
import com.tecnolpet.ot.dto.VendedorDto;
import com.tecnolpet.ot.model.Sucursal;
import com.tecnolpet.ot.model.Vendedor;
import com.tecnolpet.ot.service.VendedorService;

@RestController
@RequestMapping("api/vendedor")
public class VendedorController {

	private final VendedorService vendedorService;

	@Autowired
	public VendedorController(VendedorService vendedorService) {
		this.vendedorService = vendedorService;
	}
	
	@RequestMapping(value = "/lista", method = RequestMethod.GET)
	public List<Vendedor> traeVendedores() {
		return vendedorService.traeVendedores();
	}

	@RequestMapping(value = "/listaSucursales", method = RequestMethod.GET)
	public List<Sucursal> traeSucursales() {
		return vendedorService.traeSucursales();
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public RespuestaDto guardarVendedor(@RequestBody VendedorDto vendedorDto) {

		RespuestaDto respuesta = new RespuestaDto();

		try {

			vendedorService.guardar(vendedorDto, "insertar");
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("El vendedor se ha insertado exitosamente...");
			respuesta.setObjeto(vendedorDto);

		} catch (Exception ex) {
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());

		}

		return respuesta;

	}

	@RequestMapping(value = "/actualizar", method = RequestMethod.PUT)
	public RespuestaDto actualizarVendedor(@RequestBody VendedorDto vendedorDto) {
		RespuestaDto respuestaDto = new RespuestaDto();

		try {
			vendedorService.guardar(vendedorDto, "actualizar");
			respuestaDto.setEstado(Boolean.TRUE);
			respuestaDto
					.setMensaje("el vendedor se ha actualizado correctamente..");
			respuestaDto.setObjeto(vendedorDto);
		} catch (Exception ex) {
			respuestaDto.setEstado(Boolean.FALSE);
			respuestaDto.setMensaje(ex.getMessage());
		}

		return respuestaDto;
	}

	@RequestMapping(value = "/eliminar", method = RequestMethod.PUT)
	public RespuestaDto eliminarProducto(@RequestBody VendedorDto vendedorDto) {
		System.out.print("llego hasta aqui controler");
		RespuestaDto respuestaDto = new RespuestaDto();

		try {
			vendedorService.eliminar(vendedorDto);
			respuestaDto.setEstado(Boolean.TRUE);
			respuestaDto
					.setMensaje("El vendedor se ha eliminado correctamente..");
			respuestaDto.setObjeto(vendedorDto);
		} catch (Exception ex) {
			respuestaDto.setEstado(Boolean.FALSE);
			respuestaDto.setMensaje(ex.getMessage());
		}
		return respuestaDto;
	}
}
