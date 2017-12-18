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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tecnolpet.ot.dto.RespuestaDto;
import com.tecnolpet.ot.model.DetalleNotaPedido;
import com.tecnolpet.ot.service.NotaPedidoDetalleService;

/**
 * @author administrador
 *
 */
@RestController
@RequestMapping("api/detalleNotaPedido")
public class NotaPedidoDetalleController {

	private final NotaPedidoDetalleService notaPedidoDetalleService;
	
	@Autowired
	public NotaPedidoDetalleController(NotaPedidoDetalleService notaPedidoDetalleService) {
		this.notaPedidoDetalleService = notaPedidoDetalleService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<DetalleNotaPedido> traerDetallesNotaPedido(){
		return notaPedidoDetalleService.findDetalleNotaPedido();
	}
	
	
	@RequestMapping(value="/porNotaPedidoId", method = RequestMethod.GET)
	public List<DetalleNotaPedido> traerDetalleNotaPedidoPorNotaPedidoId(@RequestParam Integer id){
		List<DetalleNotaPedido> detalle = notaPedidoDetalleService.findDetalleNotaPedidoPorNotaPedidoId(id);
		return detalle;
	}	
	
	
	@RequestMapping(value="/traerObjeto", method = RequestMethod.PUT)
	public DetalleNotaPedido traerObjeto(){
		return new DetalleNotaPedido();
	}	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.CREATED)
	public RespuestaDto guardarDetalleNotaPedido(@RequestBody DetalleNotaPedido detalleNotaPedido){
		RespuestaDto respuesta = new RespuestaDto();
		try{
			notaPedidoDetalleService.guardar(detalleNotaPedido);
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("Ok");
			respuesta.setObjeto(detalleNotaPedido);
		}catch(Exception ex){
			respuesta.setEstado(false);
			respuesta.setMensaje(ex.getMessage());
		}
		return respuesta;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public RespuestaDto actualizarNotaPedido(@RequestBody DetalleNotaPedido detalleNotaPedido){
		RespuestaDto respuesta = new RespuestaDto();
		try{
			notaPedidoDetalleService.guardar(detalleNotaPedido);
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("Ok");
			respuesta.setObjeto(detalleNotaPedido);
		}catch(Exception ex){
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}
		return respuesta;
	}
	
	@RequestMapping(value="/eliminar", method = RequestMethod.PUT)
	public RespuestaDto eliminarNotaPedido(@RequestBody DetalleNotaPedido detalleNotaPedido){
		RespuestaDto respuesta = new RespuestaDto();
		try{
			notaPedidoDetalleService.eliminar(detalleNotaPedido);
			respuesta.setEstado(true);
			respuesta.setMensaje("Ok");
			respuesta.setObjeto(detalleNotaPedido);
		}catch(Exception ex){
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}
		return respuesta;
	}
	
}
