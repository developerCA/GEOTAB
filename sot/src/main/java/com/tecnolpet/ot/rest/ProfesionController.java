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
import com.tecnolpet.ot.model.Profesion;
import com.tecnolpet.ot.service.ProfesionService;

@RestController
@RequestMapping("api/profesion")
public class ProfesionController {

	private final ProfesionService profesionService;

	@Autowired
	public ProfesionController(ProfesionService profesionService) {
		this.profesionService = profesionService;

	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Profesion> traerProfesiones() {

		return profesionService.traerProfesiones();
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public RespuestaDto guardarProfession(@RequestBody Profesion profesion) {
		RespuestaDto respuesta = new RespuestaDto();

		try {

			profesionService.guardar(profesion,"create");
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("OK");

		} catch (Exception ex) {
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());

		}

		return respuesta;

	}

	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public RespuestaDto actualizarProfesion(@RequestBody Profesion profesion) {
		RespuestaDto respuesta = new RespuestaDto();

		try {

			profesionService.guardar(profesion,"update");
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("OK");

		} catch (Exception ex) {
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());

		}
		return respuesta;
	}

}
