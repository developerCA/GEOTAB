package com.tecnolpet.ot.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tecnolpet.ot.dto.ActualizacionProductoDto;
import com.tecnolpet.ot.dto.AvisoProcesadoDto;
import com.tecnolpet.ot.dto.RespuestaDto;
import com.tecnolpet.ot.model.Cliente;
import com.tecnolpet.ot.model.ProductoActualizacionDetalle;
import com.tecnolpet.ot.repository.ProductoActualizacionDetalleRepository;
import com.tecnolpet.ot.seguridad.UsuarioAuthenticate;
import com.tecnolpet.ot.service.ProductoActualizacionDetalleService;
import com.tecnolpet.ot.service.ProductoActualizacionService;

@RestController
@RequestMapping("/api/gestionactualizacion")
public class GestionProductoActualizacionController {

	private final ProductoActualizacionService productoActualizacionService;
	private final ProductoActualizacionDetalleRepository productoActualizacionDetalleRepository;
	private final ProductoActualizacionDetalleService productoActualizacionDetalleService;
	
	@Autowired
	public GestionProductoActualizacionController(ProductoActualizacionService productoActualizacionService, ProductoActualizacionDetalleRepository productoActualizacionDetalleRepository, ProductoActualizacionDetalleService productoActualizacionDetalleService){
		this.productoActualizacionService = productoActualizacionService;
		this.productoActualizacionDetalleRepository = productoActualizacionDetalleRepository;
		this.productoActualizacionDetalleService = productoActualizacionDetalleService;
	}
	
	@RequestMapping(value = "/traerdto", method = RequestMethod.GET)
	public AvisoProcesadoDto traerAvistoDto() {
		return new AvisoProcesadoDto();
	}
	
	@RequestMapping(value = "/generadas", method = RequestMethod.GET)
	public List<ActualizacionProductoDto> traerActualizacionesGeneradas() {
		return productoActualizacionService.traerGeneradas();
	}
	
	@RequestMapping(value = "/clientesautocomplete", method = RequestMethod.GET)
	public List<Cliente> traerClientesPorTermino(@RequestParam("termino") String termino) {
		//return clienteService.traerClientesPorTermino(termino, 4);
		return null;
	}
	
	@RequestMapping(value = "/filtrargeneradas", method = RequestMethod.GET)
	public List<ActualizacionProductoDto> traerActualizacionesFiltradasGeneradas(@RequestParam("codigoBarras") String codigoBarras, @RequestParam("cliente") Integer idCliente, @RequestParam("producto") Integer idProducto) {
		return productoActualizacionService.traerGeneradasPorFiltros(codigoBarras, idCliente, idProducto);
	}
	
	@RequestMapping(value = "/procesar", method = RequestMethod.PUT)
	public RespuestaDto procesarAviso(@RequestBody AvisoProcesadoDto avisoDto, @AuthenticationPrincipal UsuarioAuthenticate usuario) {
		
		RespuestaDto respuesta = new RespuestaDto();
		ProductoActualizacionDetalle productoActualizacionDetalle = productoActualizacionDetalleRepository.findOne(avisoDto.getAviso());

		try {
			productoActualizacionDetalleService.procesar(productoActualizacionDetalle, avisoDto.getEnviado(), avisoDto.getObservacion(), usuario.getId());
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("Ok");
		} catch (Exception ex) {
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}

		return respuesta;
	}
	
}
