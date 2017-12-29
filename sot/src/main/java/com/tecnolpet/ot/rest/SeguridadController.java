package com.tecnolpet.ot.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tecnolpet.ot.dto.MenuDto;
import com.tecnolpet.ot.model.Usuario;
import com.tecnolpet.ot.repository.UsuarioRepository;
import com.tecnolpet.ot.service.UsuarioService;

@RestController
@RequestMapping("/seguridad")
public class SeguridadController {

	private final UsuarioRepository userRepository;
	private final UsuarioService usuarioService;

	@Autowired
	public SeguridadController(UsuarioRepository userRepository, UsuarioService usuarioService) {
		this.userRepository = userRepository;
		this.usuarioService = usuarioService;

	}

	@RequestMapping(value = "/user/{username}/{producto}", method = RequestMethod.GET)
	public Usuario getUsuario(@PathVariable String username) {

		Usuario userPermiso = null;

		try {
			userPermiso = userRepository.findByUsername(username);

		} catch (Exception ex) {

		}
		return userPermiso;
	}

	@RequestMapping(value = "/grants/{username}/{producto}", method = RequestMethod.GET)
	private List<MenuDto> getPermisos(@PathVariable String username) {

		Usuario user = userRepository.findByUsername(username);

		List<MenuDto> permisos;
		permisos = usuarioService.getPermisos(user.getPerfil());

		return permisos;
	}

}
