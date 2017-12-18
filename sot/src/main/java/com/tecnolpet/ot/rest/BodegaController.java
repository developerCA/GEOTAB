package com.tecnolpet.ot.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tecnolpet.ot.dto.BodegaDto;
import com.tecnolpet.ot.dto.RespuestaDto;
import com.tecnolpet.ot.model.Bodega;
import com.tecnolpet.ot.service.BodegaService;

@RestController
@RequestMapping("api/bodega")
public class BodegaController {

	private final BodegaService bodegaService;
	
	@Autowired
	public BodegaController(BodegaService bodegaService) {
		this.bodegaService = bodegaService;
	}
	
	@RequestMapping(value="/lista", method = RequestMethod.GET)
	public List<Bodega> obtenerBodegaList(){
		return bodegaService.getBodegaList();
	}
	
	
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.CREATED)
	public RespuestaDto guardar(@RequestBody BodegaDto bodegaDto){
		RespuestaDto respuestaDto = new RespuestaDto();
		
		try{
			 bodegaService.guardarBodega(bodegaDto);
			respuestaDto.setEstado(Boolean.TRUE);
			respuestaDto.setMensaje("La bodega se ha insertado exitosamente...");
			respuestaDto.setObjeto(bodegaDto);
		}catch(Exception ex){
			respuestaDto.setEstado(Boolean.FALSE);
			respuestaDto.setMensaje(ex.getMessage());
		}
		
		return respuestaDto;
	}
	@RequestMapping(value="/actualizar", method = RequestMethod.PUT)
	public RespuestaDto actualizarProducto(@RequestBody BodegaDto bodegaDto){
		RespuestaDto respuestaDto = new RespuestaDto();
		
		try{
			 bodegaService.actualizar(bodegaDto);
			respuestaDto.setEstado(Boolean.TRUE);
			respuestaDto.setMensaje("La bodega se ha actualizado correctamente..");
			respuestaDto.setObjeto(bodegaDto);
		}catch(Exception ex){
			respuestaDto.setEstado(Boolean.FALSE);
			respuestaDto.setMensaje(ex.getMessage());			
		}
		
		return respuestaDto; 
	}
	@RequestMapping(value="/eliminar", method = RequestMethod.PUT)
	public RespuestaDto eliminarProducto(@RequestBody BodegaDto bodegaDto){
		RespuestaDto respuestaDto = new RespuestaDto();
		try{
			bodegaService.eliminar(bodegaDto);
			respuestaDto.setEstado(Boolean.TRUE);
			respuestaDto.setMensaje("la bodega se ha eliminado correctamente..");
			respuestaDto.setObjeto(bodegaDto);
		}catch(Exception ex){
			respuestaDto.setEstado(Boolean.FALSE);
			respuestaDto.setMensaje(ex.getMessage());						
		}
		return respuestaDto;
	}
}

