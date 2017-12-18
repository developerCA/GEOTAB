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
import com.tecnolpet.ot.model.Empresa;
import com.tecnolpet.ot.model.PerfilEmpresa;
import com.tecnolpet.ot.service.EmpresaService;



//se crea el webservice que trae los datos
@RestController
@RequestMapping("api/empresa")
public class EmpresaController {

	private final EmpresaService empresaService;
	
	
	@Autowired
	public EmpresaController(EmpresaService empresaService) {
		// TODO Auto-generated constructor stub
		
		this.empresaService=empresaService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Empresa> traerProductos(){
		
		return empresaService.traerEmpresa();
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.CREATED)
	public RespuestaDto guardarEmpresa(@RequestBody Empresa empresa){
		RespuestaDto respuesta=new RespuestaDto();
		try{
			
			empresaService.guardar(empresa,"create");
			respuesta.setEstado(Boolean.TRUE);
			
		}catch (Exception ex){
			
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
			
		}
		
		return respuesta;
		
	}
	
	
	
	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	public RespuestaDto actualizaEmpresa(@RequestBody Empresa empresa){
		RespuestaDto respuesta=new RespuestaDto();
		try{
			
			empresaService.guardar(empresa,"update");
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setObjeto(empresa);
			
		}catch (Exception ex){
			
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
			
		}
		
		return respuesta;
		
	}
	
	
	
	@RequestMapping(value="/desactivar",method = RequestMethod.PUT)
	public RespuestaDto eliminarEmpresa(@RequestBody Empresa empresa){
		RespuestaDto respuesta=new RespuestaDto();
		try{
			
			empresaService.eliminar(empresa);
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("ok");
			
		}catch (Exception ex){
			
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
			
		}
		
		return respuesta;
		
	}


	@RequestMapping(value="/agregarPerfilProducto",method = RequestMethod.PUT)
	public RespuestaDto agregarPerfilEmpresa(@RequestBody PerfilEmpresa perfilEmpresa){
		RespuestaDto respuesta=new RespuestaDto();
	
		try{
			
			empresaService.guardarPerfilEmpresa(perfilEmpresa); 
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setObjeto(perfilEmpresa);			
			 
		}catch (Exception ex){
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}
		 
		return respuesta;
	}  	
 

	@RequestMapping(value="/removerPerfilProducto",method = RequestMethod.PUT)
	public RespuestaDto removerPerfilEmpresa(@RequestBody PerfilEmpresa perfilEmpresa){ 
		RespuestaDto respuesta=new RespuestaDto();
	  
		try{
			empresaService.desactivarPerfilEmpresa(perfilEmpresa); 
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setObjeto(perfilEmpresa);			
			 
		}catch (Exception ex){
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}
		
		return respuesta;
	} 
	
	
	
}



