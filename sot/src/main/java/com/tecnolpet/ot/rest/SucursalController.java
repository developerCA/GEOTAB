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

import com.tecnolpet.ot.dto.RespuestaDto;
import com.tecnolpet.ot.model.Sucursal;
import com.tecnolpet.ot.seguridad.UsuarioAuthenticate;
import com.tecnolpet.ot.service.SucursalService;

@RestController
@RequestMapping("api/sucursal")
public class SucursalController {
	
	private final SucursalService sucursalService;
	
	@Autowired
	public SucursalController(SucursalService sucursalService) {
		this.sucursalService = sucursalService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Sucursal> traeSucursales(){
		return sucursalService.traerSucursales();
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public RespuestaDto actualizarSucursal(@RequestBody Sucursal sucursal){
		RespuestaDto respuesta = new RespuestaDto();
		
		try{
		sucursalService.guardar(sucursal,"update");
		respuesta.setEstado(Boolean.TRUE);
		respuesta.setObjeto(sucursal);
		}catch(Exception ex){
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}
		return respuesta;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public RespuestaDto guardarSucursal(@RequestBody Sucursal sucursal){
		RespuestaDto respuesta = new RespuestaDto();
		
		try{
			sucursalService.guardar(sucursal,"create");
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setObjeto(sucursal);
		}catch(Exception ex){
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}		
		return respuesta;		
	}
	
	@RequestMapping(value="/desactivar", method = RequestMethod.PUT)
	public RespuestaDto eliminarSucursal(@RequestBody Sucursal sucursal){
		RespuestaDto respuesta = new RespuestaDto();
		try{
			sucursal.setEstado(false);
			sucursalService.eliminar(sucursal);
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("Ok");
		}catch(Exception ex){
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}
		
		return respuesta;		
	}
	
	
	@RequestMapping(value="/usuario", method = RequestMethod.PUT)
	public Sucursal traerSucursalUsuario(@AuthenticationPrincipal UsuarioAuthenticate usuario){
		Sucursal sucursal=null;
		try{
			sucursal=sucursalService.traerSucursalUsuario(usuario.getSucursal());
		}catch(Exception ex){
		
		}
		
		return sucursal;		
	}
}
