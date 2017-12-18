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

import com.tecnolpet.ot.dto.RespuestaDto;
import com.tecnolpet.ot.model.Categoria;
import com.tecnolpet.ot.model.CostoServicio;
import com.tecnolpet.ot.seguridad.UsuarioAuthenticate;
import com.tecnolpet.ot.service.CategoriaService;

/**
 * @author administrador
 *
 */
@RestController
@RequestMapping("api/categoria")
public class CategoriaController {
	
	private final CategoriaService categoriaService;
	
	@Autowired
	public CategoriaController(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Categoria> traerCategorias(@AuthenticationPrincipal UsuarioAuthenticate usuario){
		return categoriaService.traerCategorias(usuario);
	}
	
	@RequestMapping(value="/servicios", method = RequestMethod.GET)
	public List<CostoServicio> traerServiciosPorCategoria(@RequestParam Integer idTipo){
		return categoriaService.traerServicioPorTipoProducto(idTipo);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public RespuestaDto actualizarCategoria(@AuthenticationPrincipal UsuarioAuthenticate usuario,@RequestBody Categoria categoria){
		RespuestaDto respuesta = new RespuestaDto();
		
		try{
		categoriaService.guardar(usuario,categoria,"update");
		respuesta.setEstado(Boolean.TRUE);
		respuesta.setObjeto(categoria);
		}catch(Exception ex){
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}
		return respuesta;
	}
	
	@RequestMapping(value="/guardarServicios", method=RequestMethod.PUT)
	public RespuestaDto guardarServcios(@AuthenticationPrincipal UsuarioAuthenticate usuario,@RequestBody List<CostoServicio> listaServicios){
		RespuestaDto respuesta = new RespuestaDto();
		
		try{
		categoriaService.guardarServicios(listaServicios);
		respuesta.setEstado(Boolean.TRUE);
		respuesta.setObjeto(listaServicios);
		}catch(Exception ex){
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}
		return respuesta;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.CREATED)
	public RespuestaDto guardarCategoria(@AuthenticationPrincipal UsuarioAuthenticate usuario,@RequestBody Categoria categoria){
		RespuestaDto respuesta = new RespuestaDto();
		
		try{
			categoria.setEstado(Boolean.TRUE);
			categoriaService.guardar(usuario,categoria,"create");
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("Ok");
		}catch(Exception ex){
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}
		
		return respuesta;
	}
	
	@RequestMapping(value="/desactivar", method = RequestMethod.PUT)
	public RespuestaDto eliminarCategoria(@RequestBody Categoria categoria){
		RespuestaDto respuesta = new RespuestaDto();
		
		try{
			categoriaService.eliminar(categoria);
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("Ok");
		}catch(Exception ex){
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}
		
		return respuesta;		
	}
}
