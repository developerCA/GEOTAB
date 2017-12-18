/**
 * 
 */
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
import com.tecnolpet.ot.model.Region;
import com.tecnolpet.ot.service.RegionService;

/**
 * @author administrador
 *
 */
@RestController
@RequestMapping("api/region")
public class RegionController {
	
	private final RegionService regionService;
	
	@Autowired
	public RegionController(RegionService regionService) {
		this.regionService = regionService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Region> traerRegiones(){
		return regionService.traerRegiones();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public RespuestaDto actualizarRegion(@RequestBody Region region){
		RespuestaDto respuesta = new RespuestaDto();
		
		try{
		regionService.guardar(region,"update");
		respuesta.setEstado(Boolean.TRUE);
		respuesta.setObjeto(region);
		}catch(Exception ex){
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}
		return respuesta;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.CREATED)
	public RespuestaDto guardarRegion(@RequestBody Region region){
		RespuestaDto respuesta = new RespuestaDto();
		
		try{
			region.setEstado(Boolean.TRUE);
			regionService.guardar(region,"create");
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("Ok");
		}catch(Exception ex){
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}
		
		return respuesta;
	}
	
	@RequestMapping(value="/desactivar", method = RequestMethod.PUT)
	public RespuestaDto eliminarRegion(@RequestBody Region region){
		RespuestaDto respuesta = new RespuestaDto();
		
		try{
			regionService.eliminar(region);
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("Ok");
		}catch(Exception ex){
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}
		
		return respuesta;		
	}
}
