package com.tecnolpet.ot.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.dto.ClienteSuscripcionDto;
import com.tecnolpet.ot.dto.ConsultaOtDto;
import com.tecnolpet.ot.dto.RespuestaDto;
import com.tecnolpet.ot.jview.ViewOT;
import com.tecnolpet.ot.model.Acceso;
import com.tecnolpet.ot.model.Seguimiento;
import com.tecnolpet.ot.model.Suscripcion;
import com.tecnolpet.ot.model.view.VistaProcesoSuscripcion;
import com.tecnolpet.ot.model.view.VistaVendidos;
import com.tecnolpet.ot.seguridad.UsuarioAuthenticate;
import com.tecnolpet.ot.service.SuscripcionService;

@RestController
@RequestMapping("api/suscripcion")
public class SuscripcionController {

	private final SuscripcionService suscripcionService;

	@Autowired
	public SuscripcionController(SuscripcionService suscripcionService) {
		this.suscripcionService = suscripcionService;

	}

	@RequestMapping(method = RequestMethod.GET)
	public List<ClienteSuscripcionDto> clientesActivos() {
		return suscripcionService.traeClientesActivos();
	}

	@RequestMapping(value = "/cliente", method = RequestMethod.GET)
	public List<Suscripcion> suscripcionesActivasPorCliente(
			@RequestParam Integer idCliente) {

		return suscripcionService.traeSuscripcionesByCliente(idCliente);
	}

	@RequestMapping(value = "/accesos", method = RequestMethod.GET)
	public List<Acceso> suscripcionesActivas(@RequestParam Integer idSuscripcion) {

		return suscripcionService.generarAccesos(idSuscripcion);
	}

	@RequestMapping(value = "/accesos/gratis", method = RequestMethod.PUT)
	public Acceso generarAccesoGratis(@RequestBody Integer idSuscripcion) {

		return suscripcionService.generarAccesoGratis(idSuscripcion);
	}

	@JsonView(ViewOT.PublicView.class)
	@RequestMapping(value = "/ots", method = RequestMethod.PUT)
	public RespuestaDto listaOrdenes(@RequestBody ConsultaOtDto consultaDto,
			@AuthenticationPrincipal UsuarioAuthenticate usuario) {
		RespuestaDto respuesta = new RespuestaDto();
		try {

			respuesta.setEstado(Boolean.TRUE);
			respuesta.setObjeto(suscripcionService.traerPedidos(consultaDto,
					usuario));
		} catch (Exception ex) {
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}
		return respuesta;

	}
	
	@JsonView(ViewOT.PublicView.class)
	@RequestMapping(value = "/otsServicios", method = RequestMethod.PUT)
	public RespuestaDto listaServicios(@RequestBody ConsultaOtDto consultaDto,
			@AuthenticationPrincipal UsuarioAuthenticate usuario) {
		RespuestaDto respuesta = new RespuestaDto();
		try {

			
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setObjeto(suscripcionService.traerPedidosServicio(consultaDto,
					usuario));
		} catch (Exception ex) {
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}
		return respuesta;

	}
	
	@RequestMapping(value = "/otsTecnicos", method = RequestMethod.PUT)
	public RespuestaDto listaServiciosTecnicos(@RequestBody ConsultaOtDto consultaDto,
			@AuthenticationPrincipal UsuarioAuthenticate usuario) {
		RespuestaDto respuesta = new RespuestaDto();
		try {

			
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setObjeto(suscripcionService.traerExperiencia(consultaDto, usuario)); 
		} catch (Exception ex) {
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}
		return respuesta;

	}

	@RequestMapping(value = "/seguimiento", method = RequestMethod.PUT)
	public RespuestaDto guardarSeguimiento(
			@RequestBody Seguimiento seguimiento,
			@AuthenticationPrincipal UsuarioAuthenticate usuario) {

		RespuestaDto respuesta = new RespuestaDto();
		
		try {

			respuesta.setEstado(Boolean.TRUE);
			suscripcionService.guardarSeguimiento(seguimiento);
			respuesta.setObjeto(seguimiento);
		} catch (Exception ex) {
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}
		return respuesta;

	}

	@RequestMapping(value = "/traerSeguimiento", method = RequestMethod.GET)
	public List<Seguimiento> traerSeguimientos(
			@RequestParam Integer idTareaDetallePedido) {

		return suscripcionService.traerSeguimientos(idTareaDetallePedido);
	}

	@RequestMapping(value = "/generar/accesos", method = RequestMethod.PUT)
	public RespuestaDto generaAccesos(@RequestBody List<Acceso> listaAccesos) {

		RespuestaDto respuesta = new RespuestaDto();
		try {
			suscripcionService.registrarAccesos(listaAccesos);
			respuesta.setEstado(Boolean.TRUE);

		} catch (Exception ex) {
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}
		return respuesta;
	}

	@RequestMapping(value = "/temporal/accesos", method = RequestMethod.PUT)
	public RespuestaDto generaAccesosTemporales(
			@RequestBody List<Acceso> listaAccesosTemporales) {

		RespuestaDto respuesta = new RespuestaDto();
		try {
			suscripcionService
					.registrarAccesosTemporales(listaAccesosTemporales);
			respuesta.setEstado(Boolean.TRUE);

		} catch (Exception ex) {
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}
		return respuesta;
	}

	@RequestMapping(value = "/resetear/accesos", method = RequestMethod.PUT)
	public RespuestaDto resetarAccesos(@RequestBody Acceso acceso) {

		RespuestaDto respuesta = new RespuestaDto();
		try {
			suscripcionService.resetarAccesos(acceso);
			respuesta.setEstado(Boolean.TRUE);

		} catch (Exception ex) {
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}
		return respuesta;
	}

	@RequestMapping(value = "/cantSuscripcionesLatentes", method = RequestMethod.PUT)
	public RespuestaDto cantSuscripcionesLatentes() {
		RespuestaDto respuesta = new RespuestaDto();

		try {

			Integer cantidad = suscripcionService.getSuscripcionesLatentes();
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("Todo ok");
			respuesta.setObjeto(cantidad);
		} catch (Exception ex) {
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}

		return respuesta;
	}

	@RequestMapping(value = "/cantNotaPedido", method = RequestMethod.PUT)
	public RespuestaDto cantNotaPedido(
			@AuthenticationPrincipal UsuarioAuthenticate usuario) {
		RespuestaDto respuesta = new RespuestaDto();
		try {
			Integer cantidad = suscripcionService
					.getCantNotaPedidoRegistradas(usuario);
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("Todo ok");
			respuesta.setObjeto(cantidad);
		} catch (Exception ex) {
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}

		return respuesta;
	}

	@RequestMapping(value = "/cantFacturasPorEnviar", method = RequestMethod.PUT)
	public RespuestaDto cantFacturasEnviar() {
		RespuestaDto respuesta = new RespuestaDto();
		try {
			Integer cantidad = suscripcionService.getCantFacturasEnviar();
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("Todo ok");
			respuesta.setObjeto(cantidad);
		} catch (Exception ex) {
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}

		return respuesta;
	}

	@RequestMapping(value = "/cantNotaPedidoEnviadas", method = RequestMethod.PUT)
	public RespuestaDto cantNotaPedidoEnviadas() {
		RespuestaDto respuesta = new RespuestaDto();
		try {
			Integer cantidad = suscripcionService.cantNotaPedidoEnviadas();
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("Todo ok");
			respuesta.setObjeto(cantidad);
		} catch (Exception ex) {
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}

		return respuesta;
	}

	@RequestMapping(value = "/traerProcesos", method = RequestMethod.GET)
	public List<VistaProcesoSuscripcion> traerProcesos() {

		return suscripcionService.traerProcesos();
	}

	@RequestMapping(value = "/traerLosMasVendidos", method = RequestMethod.GET)
	public List<VistaVendidos> traerLosMasVendidos() {

		return suscripcionService.traerLosMasVendidos();
	}

	@RequestMapping(value = "/ventasAnuales", method = RequestMethod.PUT)
	public RespuestaDto ventasAnuales(@RequestBody String anio) {
		RespuestaDto respuestaDto = new RespuestaDto();

		try {
			// suscripcionVentaAnualDto
			List<Suscripcion> suscrip = suscripcionService.ventasAnuales(anio);
			respuestaDto.setEstado(Boolean.TRUE);
			respuestaDto.setMensaje("ok");
			respuestaDto.setObjeto(suscrip);
		} catch (Exception ex) {
			respuestaDto.setEstado(Boolean.FALSE);
			respuestaDto.setMensaje(ex.getMessage());
		}

		return respuestaDto;
	}

	@RequestMapping(value = "/aprobadas/cliente", method = RequestMethod.GET)
	public List<Suscripcion> traerSuscripcionesAprobadasPorCliente(
			@RequestParam Integer clienteId) {
		return suscripcionService.traerSuscripcionesAprobadas(clienteId);
	}

	@RequestMapping(value = "/accesos/rango", method = RequestMethod.PUT)
	public RespuestaDto guardarAccesoPorRangoIp(@RequestBody Acceso acceso,
			@AuthenticationPrincipal UsuarioAuthenticate usuario) {
		RespuestaDto respuesta = new RespuestaDto();

		try {
			suscripcionService.saveAccesosPorRangoIp(usuario, acceso);
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("El acceso fue modificado exitosamente...");
		} catch (Exception ex) {
			respuesta.setEstado(Boolean.FALSE);
			respuesta
					.setMensaje("Un error se ha sucitado al intentar modificar el acceso");
		}
		return respuesta;
	}

	@RequestMapping(value = "/desactivar/accesos", method = RequestMethod.PUT)
	public RespuestaDto desactivarAccesos(
			@AuthenticationPrincipal UsuarioAuthenticate usuario,
			@RequestBody Integer suscripcionId) {
		RespuestaDto respuesta = new RespuestaDto();
		try {

			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("Verificación de accesos correcta");
		} catch (Exception ex) {
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}

		return respuesta;
	}

}
