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
import com.tecnolpet.ot.model.Perfil;
import com.tecnolpet.ot.service.PerfilService;

//se crea el webservice que trae los datos
@RestController
@RequestMapping("api/perfil")
public class PerfilController {

	private final PerfilService perfilService;

	// private final UsuarioService usuarioService;

	@Autowired
	public PerfilController(PerfilService perfilService) {
		// TODO Auto-generated constructor stub

		this.perfilService = perfilService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Perfil> traerPerfil() {

		return perfilService.traePerfil();

	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public RespuestaDto guardarPerfil(@RequestBody Perfil perfil) {
		RespuestaDto respuesta = new RespuestaDto();

		
		try {

			perfilService.guardar(perfil,"create");
			respuesta.setEstado(Boolean.TRUE);

		} catch (Exception ex) {

			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());

		}

		return respuesta;

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public RespuestaDto actualizaPerfil(@RequestBody Perfil perfil) {
		RespuestaDto respuesta = new RespuestaDto();
		try {

			perfilService.guardar(perfil,"update");
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setObjeto(perfil);

		} catch (Exception ex) {

			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());

		}

		return respuesta;

	}

	@RequestMapping(value = "/desactivar", method = RequestMethod.PUT)
	public RespuestaDto eliminarPerfil(@RequestBody Perfil perfil) {
		RespuestaDto respuesta = new RespuestaDto();

		try {

			perfilService.eliminar(perfil);
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("ok");

		} catch (Exception ex) {

			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());

		}

		return respuesta;

	}

}
