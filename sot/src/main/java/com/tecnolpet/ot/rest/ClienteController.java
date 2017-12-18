/**
 * 
 */
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

import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.dto.RespuestaDto;
import com.tecnolpet.ot.jview.ViewOT;
import com.tecnolpet.ot.model.Cliente;
import com.tecnolpet.ot.model.Enlace;
import com.tecnolpet.ot.seguridad.UsuarioAuthenticate;
import com.tecnolpet.ot.service.ClienteService;

/**
 * @author Armando Ariel Suárez Pons
 *
 */
@RestController
@RequestMapping("api/cliente")
public class ClienteController {
	
	private final ClienteService clienteService;
	
	@Autowired
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@JsonView(ViewOT.PublicView.class)
	@RequestMapping(method = RequestMethod.GET)
	public List<Cliente> traerClientesActivos(@AuthenticationPrincipal UsuarioAuthenticate usuario){
		
		
		return clienteService.getClientesActivos(usuario);
	}

	@JsonView(ViewOT.PublicView.class)
	@RequestMapping(value="/porAtributos", method = RequestMethod.GET)
	public List<Cliente> traerClientesPorAtributos(@RequestParam String identificacion, @RequestParam String nombres, @RequestParam String apellidos, @AuthenticationPrincipal UsuarioAuthenticate usuario){
		return clienteService.getClientesPorAtributos(identificacion, nombres, apellidos, usuario);
	}
	
	@RequestMapping(value="/enlace", method = RequestMethod.GET)
	public List<Enlace> traerEnlacesCliente(@RequestParam Integer idCliente){
		return clienteService.getEnlacesByCliente(idCliente);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.CREATED)
	public RespuestaDto guardarCliente(@RequestBody Cliente cliente,@AuthenticationPrincipal UsuarioAuthenticate usuario){
		RespuestaDto respuestaDto = new RespuestaDto();
		
		try{
			
			clienteService.guardar(cliente,usuario);
			respuestaDto.setEstado(Boolean.TRUE);
			respuestaDto.setMensaje("El cliente se ha insertado exitosamente...");
			respuestaDto.setObjeto(cliente);
		}catch(Exception ex){
			respuestaDto.setEstado(Boolean.FALSE);
			respuestaDto.setMensaje(ex.getMessage());
		}
		
		return respuestaDto;
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public RespuestaDto actualizarCliente(@RequestBody Cliente cliente,@AuthenticationPrincipal UsuarioAuthenticate usuario){
		RespuestaDto respuestaDto = new RespuestaDto();
		
		try{
			
			
			clienteService.guardar(cliente,usuario);
			respuestaDto.setEstado(Boolean.TRUE);
			respuestaDto.setMensaje("El cliente se ha actualizado correctamente..");
			respuestaDto.setObjeto(cliente);
		}catch(Exception ex){
			respuestaDto.setEstado(Boolean.FALSE);
			respuestaDto.setMensaje(ex.getMessage());			
		}
		
		return respuestaDto; 
	}
	
	@RequestMapping(value="/eliminar", method = RequestMethod.PUT)
	public RespuestaDto eliminarCliente(@RequestBody Cliente cliente){
		RespuestaDto respuestaDto = new RespuestaDto();
		
		try{
			clienteService.eliminar(cliente);
			respuestaDto.setEstado(Boolean.TRUE);
			respuestaDto.setMensaje("El cliente se ha eliminado correctamente..");
			respuestaDto.setObjeto(cliente);
		}catch(Exception ex){
			respuestaDto.setEstado(Boolean.FALSE);
			respuestaDto.setMensaje(ex.getMessage());						
		}
		return respuestaDto;
	}
	
	
	

}
