package com.tecnolpet.ot.rest;

import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tecnolpet.ot.seguridad.UsuarioAuthenticate;



@RestController
@RequestMapping("api/users")
public class UsuarioController {

	@RequestMapping(value = "/info", method = RequestMethod.GET)
	private UsuarioAuthenticate getInfo(@AuthenticationPrincipal UsuarioAuthenticate usuario) {
		return usuario;
	}
	
	
}
