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
import com.tecnolpet.ot.model.Rol;
import com.tecnolpet.ot.service.RolService;



@RestController
@RequestMapping("api/rol")
public class RolController {

	private final RolService rolService;

	@Autowired
	public RolController(RolService rolService) {
		this.rolService = rolService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Rol> traeRoles() {
		return rolService.traeRoles();
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public RespuestaDto guardarRol(@RequestBody Rol rol) {
		RespuestaDto respuesta = new RespuestaDto();

		try {

			rolService.guardar(rol,"create");
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setObjeto(rol);

		} catch (Exception ex) {
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());

		}
		
		return respuesta;
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public RespuestaDto actualizarRol(@RequestBody Rol rol){
		RespuestaDto respuesta = new RespuestaDto();

		try {

			rolService.guardar(rol,"update");
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setObjeto(rol);

		} catch (Exception ex) {
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());

		}
		
		return respuesta;
	}
	
	@RequestMapping(value="/desactivar", method = RequestMethod.PUT)
	public RespuestaDto eliminarRol(@RequestBody Rol rol){
		RespuestaDto respuesta = new RespuestaDto();
		
		try{
			rolService.eliminarRol(rol);
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("Ok");
		}catch(Exception ex){
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}
		
		return respuesta;		
	}
}
