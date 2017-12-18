package com.tecnolpet.ot.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tecnolpet.ot.dto.RespuestaDto;
import com.tecnolpet.ot.model.Enlace;
import com.tecnolpet.ot.model.NotaPedido;
import com.tecnolpet.ot.model.Producto;
import com.tecnolpet.ot.model.Sucursal;
import com.tecnolpet.ot.model.Suscripcion;
import com.tecnolpet.ot.model.Telerenovador;
import com.tecnolpet.ot.model.Vendedor;
import com.tecnolpet.ot.seguridad.UsuarioAuthenticate;
import com.tecnolpet.ot.service.ConsultaService;

@RestController
@RequestMapping("api/consulta")
public class ConsultaController {

	private final ConsultaService consultaService;
	
	@Autowired
	public ConsultaController(ConsultaService consultaService) {
		this.consultaService = consultaService;
	}
	
	@RequestMapping(value="/suscripcion/inicial", method = RequestMethod.GET)
	public List<Suscripcion> traerSuscripcionesPorSuscripcionInicialDescendente(@RequestParam Integer suscripcionInicial, @AuthenticationPrincipal UsuarioAuthenticate usuario){
		return consultaService.getSuscripcionesBySuscripcionInicialDescendente(suscripcionInicial, usuario);
	}
	
	@RequestMapping(value="/notaPedido/id", method = RequestMethod.PUT)
	public RespuestaDto traerNotaPedidoPorId(@RequestBody Integer id){
		RespuestaDto respuesta = new RespuestaDto();
		
		try{
			NotaPedido np = consultaService.getNotaPedidoById(id);
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("Nota de Pedido recuperada exitosamente..");
			respuesta.setObjeto(np);
		}catch(Exception ex){
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje("Error al recuperar la Nota de Pedido");
		}
		
		return respuesta;
	}
	
	@RequestMapping(value="/vendedor/id", method = RequestMethod.PUT)
	public RespuestaDto traerVendedorPorId(@RequestBody Integer id){
		RespuestaDto respuesta = new RespuestaDto();
		try{
			Vendedor v = consultaService.getVendedorById(id);
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("Vendedor recuperado exitosamente..");
			respuesta.setObjeto(v);
		}catch(Exception ex){
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje("Error al recuperar el Vendedor");
		}
		
		return respuesta;
	}

	@RequestMapping(value="/sucursal/id", method = RequestMethod.PUT)
	public RespuestaDto traerSucursalPorId(@RequestBody Integer id){
		RespuestaDto respuesta = new RespuestaDto();
		try{
			Sucursal s = consultaService.getSucursalById(id);
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("Sucursal recuperada exitosamente..");
			respuesta.setObjeto(s);
		}catch(Exception ex){
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje("Error al recuperar la Sucursal");
		}
		
		return respuesta;
	}

	@RequestMapping(value="/renovador/id", method = RequestMethod.PUT)
	public RespuestaDto traerRenovadorPorId(@RequestBody Integer id){
		RespuestaDto respuesta = new RespuestaDto();
		try{
			Telerenovador t = consultaService.getTelerenovadorById(id);
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("Telerenovador recuperado exitosamente..");
			respuesta.setObjeto(t);
		}catch(Exception ex){
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje("Error al recuperar al Terenovador");
		}
		
		return respuesta;
	}
	
	@RequestMapping(value="/producto/id", method = RequestMethod.PUT)
	public RespuestaDto traerProductoPorId(@RequestBody Integer id){
		RespuestaDto respuesta = new RespuestaDto();
		try{
			Producto p = consultaService.getProductoById(id);
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("Producto recuperado exitosamente..");
			respuesta.setObjeto(p);
		}catch(Exception ex){
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje("Error al recuperar el Producto");
		}
		
		return respuesta;
	}
	
	@RequestMapping(value="/contacto/id", method = RequestMethod.PUT)
	public RespuestaDto traerContactoPorId(@RequestBody Integer id){
		RespuestaDto respuesta = new RespuestaDto();
		try{
			Enlace e = consultaService.getContactoById(id);
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("Producto recuperado exitosamente..");
			respuesta.setObjeto(e);
		}catch(Exception ex){
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje("Error al recuperar el Producto");
		}
		
		return respuesta;
	}
	
}
