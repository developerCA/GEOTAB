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

import com.tecnolpet.ot.dto.AdminUsuarioDto;
import com.tecnolpet.ot.dto.RespuestaDto;
import com.tecnolpet.ot.model.Usuario;
import com.tecnolpet.ot.seguridad.UsuarioAuthenticate;
import com.tecnolpet.ot.service.UserService;

@RestController
@RequestMapping("api/admUsuario")
public class AdmUserController {

	private UserService userService;

	@Autowired
	public AdmUserController(UserService userService) {
		this.userService = userService;

	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Usuario> getUsers() {
		return userService.traerUsuariosPorEstado(true);
	}

	@RequestMapping(value = "/porPerfilProducto", method = RequestMethod.GET)
	public List<Usuario> traerUsuariosPerfilProducto(
			@RequestParam Integer idPerfil,@RequestParam Integer idEmpresa) {
		return userService.listarUsuariosPorPerfilEmpresa(idPerfil,idEmpresa);
	}

	@RequestMapping(value = "/porProducto", method = RequestMethod.GET)
	public List<Usuario> traerUsuariosProducto(@RequestParam Integer idProducto) {
		return userService.traerUsuariosEmpresa(idProducto);
	}

	@RequestMapping(value = "/generarPassword", method = RequestMethod.PUT)
	public RespuestaDto generarPassword() {
		RespuestaDto respuesta = new RespuestaDto();

		try {
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("");
			respuesta.setObjeto(userService.generarPassword());
		} catch (Exception ex) {
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}
		return respuesta;
	}

	@RequestMapping(value = "/cambiarPassword", method = RequestMethod.PUT)
	public RespuestaDto cambiarPassword(
			@AuthenticationPrincipal UsuarioAuthenticate usuario, String clave) {
		RespuestaDto respuesta = new RespuestaDto();

		try {

			userService.cambiaClave(usuario, clave);
			respuesta.setEstado(Boolean.TRUE);
		} catch (Exception ex) {
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}
		return respuesta;
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public RespuestaDto guardarUsuario(
			@RequestBody AdminUsuarioDto adminUsuarioDto) {
		RespuestaDto respuesta = new RespuestaDto();

		try {
			userService.guardar(adminUsuarioDto, "create");

			respuesta.setEstado(Boolean.TRUE);
		} catch (Exception ex) {
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}
		return respuesta;
	}

	@RequestMapping(value = "/editar", method = RequestMethod.PUT)
	public RespuestaDto editarUsuario(
			@RequestBody AdminUsuarioDto adminUsuarioDto) {
		RespuestaDto respuesta = new RespuestaDto();

		try {
			userService.guardar(adminUsuarioDto, "update");

			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("ok");
		} catch (Exception ex) {
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}

		return respuesta;
	}

}
