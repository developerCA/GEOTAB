/**
 * 
 */
package com.tecnolpet.ot.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.dto.PedidoDto;
import com.tecnolpet.ot.dto.ReporteDto;
import com.tecnolpet.ot.dto.RespuestaDto;
import com.tecnolpet.ot.dto.SuscripcionPedidoDto;
import com.tecnolpet.ot.jview.ViewOT;
import com.tecnolpet.ot.model.DetalleNotaPedido;
import com.tecnolpet.ot.model.Enlace;
import com.tecnolpet.ot.model.NotaPedido;
import com.tecnolpet.ot.model.Tarea;
import com.tecnolpet.ot.model.TareaDetalleNotaPedido;
import com.tecnolpet.ot.seguridad.UsuarioAuthenticate;
import com.tecnolpet.ot.service.NotaPedidoService;
import com.tecnolpet.ot.service.SuscripcionService;
import com.tecnolpet.ot.service.TareaService;

/**
 * @author Armando Ariel Suárez Pons
 *
 */
@RestController
@RequestMapping("api/notaPedido")
@PropertySources(value = { @PropertySource("classpath:/application.properties") })
public class NotaPedidoController {

	private final NotaPedidoService notaPedidoService;
	private final SuscripcionService suscripcionService;
	private final TareaService tareaService;

	@Autowired
	private Environment env;

	@Autowired
	public NotaPedidoController(NotaPedidoService notaPedidoService,
			SuscripcionService suscripcionService, TareaService tareaService) {
		this.notaPedidoService = notaPedidoService;
		this.suscripcionService = suscripcionService;
		this.tareaService = tareaService;

	}

	@JsonView(ViewOT.PublicView.class)
	@RequestMapping(method = RequestMethod.GET)
	public List<NotaPedido> traerNotaPedidoRegistradasRevisadas(
			@AuthenticationPrincipal UsuarioAuthenticate usuario) {
		List<NotaPedido> lista = notaPedidoService
				.getNotasPedidoRegistradasRevisadas(usuario);

		return lista;
	}
	@JsonView(ViewOT.PublicView.class)
	@RequestMapping(value = "/registradas", method = RequestMethod.GET)
	public List<NotaPedido> traerNotaPedidoRegistradas(
			@AuthenticationPrincipal UsuarioAuthenticate usuario) {
		return notaPedidoService.getNotasPedidoRegistradas(usuario);
	}

	@RequestMapping(value = "/datainicial", method = RequestMethod.GET)
	public List<Object> traerData(
			@AuthenticationPrincipal UsuarioAuthenticate usuario) {
		return notaPedidoService.traerDataInicial(usuario);
	}
	@JsonView(ViewOT.PublicView.class)
	@RequestMapping(value = "/ordenes", method = RequestMethod.GET)
	public List<NotaPedido> traerOrdenes(
			@AuthenticationPrincipal UsuarioAuthenticate usuario) {
		return notaPedidoService.traerOrdenes(usuario);
	}

	@RequestMapping(value = "/ticks", method = RequestMethod.GET)
	public List<Object> traerTicks(
			@AuthenticationPrincipal UsuarioAuthenticate usuario) {
		return notaPedidoService.traerDataTick(usuario);
	}

	@RequestMapping(value = "/valueticks1", method = RequestMethod.GET)
	public List<Object> traerValueTicksOrdenes(
			@AuthenticationPrincipal UsuarioAuthenticate usuario) {
		return notaPedidoService.traerValueTick(usuario);
	}

	@RequestMapping(value = "/valueticksEquipos", method = RequestMethod.GET)
	public List<Object> traerValueTicksEquipos(
			@AuthenticationPrincipal UsuarioAuthenticate usuario) {
		return notaPedidoService.traerValueEquipos(usuario);
	}

	@RequestMapping(value = "/valueticksServicios", method = RequestMethod.GET)
	public List<Object> traerValueTicksServicios(
			@AuthenticationPrincipal UsuarioAuthenticate usuario) {
		return notaPedidoService.traerValueServicios(usuario);
	}

	@JsonView(ViewOT.PublicView.class)
	@RequestMapping(value = "/aprobadas", method = RequestMethod.GET)
	public List<NotaPedido> traerNotaPedidoAprobadas(
			@AuthenticationPrincipal UsuarioAuthenticate usuario) {
		return notaPedidoService.getNotasPedidoAprobadasSuscripcion(usuario);
	}
	@JsonView(ViewOT.PublicView.class)
	@RequestMapping(value = "/suscripcion", method = RequestMethod.PUT)
	public SuscripcionPedidoDto traerSuscripciones(@RequestBody Integer idPedido) {

		SuscripcionPedidoDto suscripcionDto = suscripcionService
				.generarSuscripcion(idPedido);

		return suscripcionDto;
	}

	@RequestMapping(value = "/ordenInterna", method = RequestMethod.PUT)
	public Integer traerOrdenInterna(
			@AuthenticationPrincipal UsuarioAuthenticate usuario)
			throws Exception {

		Integer numero;

		numero = notaPedidoService.crearOrdenInterna(usuario);

		return numero;
	}

	@RequestMapping(value = "/reporte", method = RequestMethod.PUT)
	public ReporteDto traerReporteNotaPedido(@RequestBody Integer idPedido) {

		ReporteDto reporte = new ReporteDto();
		String path = env.getProperty("ruta.reportes");
		String url = path.concat("ot.rptdesign&idOrden=").concat(
				String.valueOf(idPedido));
		reporte.setUrl(url);

		return reporte;
	}

	@RequestMapping(value = "/reporteSinCosto", method = RequestMethod.PUT)
	public ReporteDto traerReporteNotaPedidoSinCosto(
			@RequestBody Integer idPedido) {

		ReporteDto reporte = new ReporteDto();
		String path = env.getProperty("ruta.reportes");
		String url = path.concat("otSinCosto.rptdesign&idOrden=").concat(
				String.valueOf(idPedido));
		reporte.setUrl(url);

		return reporte;
	}

	@RequestMapping(value = "/usuario", method = RequestMethod.PUT)
	public UsuarioAuthenticate traerUsuario(
			@AuthenticationPrincipal UsuarioAuthenticate usuario) {

		return usuario;
	}
	@JsonView(ViewOT.PublicView.class)
	@RequestMapping(value = "/suscripcion/aprobadas", method = RequestMethod.PUT)
	public SuscripcionPedidoDto traerSuscripcionesAprobadas(
			@RequestBody Integer idPedido) {

		SuscripcionPedidoDto suscripcionDto = suscripcionService
				.traerSuscripciones(idPedido);

		return suscripcionDto;
	}

	@RequestMapping(value = "/suscripcion/crear", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public RespuestaDto crearSuscripciones(
			@RequestBody SuscripcionPedidoDto suscripcionPedidoDto,
			@AuthenticationPrincipal UsuarioAuthenticate usuario) {
		RespuestaDto respuesta = new RespuestaDto();

		try {

			suscripcionService.creaSuscripciones(suscripcionPedidoDto, usuario);
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("Ok");
			respuesta.setObjeto(suscripcionPedidoDto);

		} catch (Exception ex) {
			respuesta.setEstado(false);
			respuesta.setMensaje(ex.getMessage());
		}
		return respuesta;

	}

	@RequestMapping(value = "/suscripcion/aprobar", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public RespuestaDto aprobarSuscripciones(
			@RequestBody SuscripcionPedidoDto suscripcionPedidoDto,
			@AuthenticationPrincipal UsuarioAuthenticate usuario) {
		RespuestaDto respuesta = new RespuestaDto();

		try {

			suscripcionService.aprobarSuscripciones(suscripcionPedidoDto,
					usuario.getId());
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("Ok");
			respuesta.setObjeto(suscripcionPedidoDto);

		} catch (Exception ex) {
			respuesta.setEstado(false);
			respuesta.setMensaje(ex.getMessage());
			respuesta.setObjeto(suscripcionPedidoDto);

		}
		return respuesta;

	}

	@RequestMapping(value = "/suscripcion/crearenlace", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public RespuestaDto crearEnlace(@RequestBody Enlace enlace) {
		RespuestaDto respuesta = new RespuestaDto();

		try {

			suscripcionService.crearEnlance(enlace);
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("Ok");
			respuesta.setObjeto(enlace);

		} catch (Exception ex) {
			respuesta.setEstado(false);
			respuesta.setMensaje(ex.getMessage());
		}
		return respuesta;

	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public RespuestaDto guardarNotaPedido(@RequestBody PedidoDto pedidoDto,
			@AuthenticationPrincipal UsuarioAuthenticate usuario) {
		RespuestaDto respuesta = new RespuestaDto();
		try {

			notaPedidoService.guardar(pedidoDto, usuario);
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("Ok");

		} catch (Exception ex) {
			respuesta.setEstado(false);
			respuesta.setMensaje(ex.getMessage());
		}
		return respuesta;
	}

	@ExceptionHandler({ org.springframework.http.converter.HttpMessageNotReadableException.class })
	@RequestMapping(value = "/calcular", method = RequestMethod.PUT)
	public PedidoDto calcularCostos(@RequestBody PedidoDto pedidoDto) {

		notaPedidoService.calcularCostos(pedidoDto);

		return pedidoDto;
	}

	@RequestMapping(value = "/revision", method = RequestMethod.PUT)
	public RespuestaDto registrarRevision(@RequestBody NotaPedido notaPedido,
			@AuthenticationPrincipal UsuarioAuthenticate usuario) {
		RespuestaDto respuesta = new RespuestaDto();

		try {
			notaPedidoService.registrarRevision(notaPedido);
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("Ok");
			respuesta.setObjeto(notaPedido);
		} catch (Exception ex) {
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}

		return respuesta;
	}

	@RequestMapping(value = "/responsables", method = RequestMethod.PUT)
	public RespuestaDto registrarResponsables(
			@RequestBody TareaDetalleNotaPedido tarea,
			@AuthenticationPrincipal UsuarioAuthenticate usuario) {
		RespuestaDto respuesta = new RespuestaDto();

		try {

			// System.out.println(tarea.getResponsable().getNombres());
			// System.out.println(tarea.getAsistente().getNombres());

			suscripcionService.actualizarResponsables(tarea);
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("Ok");
			respuesta.setObjeto(tarea);
		} catch (Exception ex) {
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}

		return respuesta;
	}

	@RequestMapping(value = "/reversion", method = RequestMethod.PUT)
	public RespuestaDto registrarReversion(@RequestBody NotaPedido notaPedido,
			@AuthenticationPrincipal UsuarioAuthenticate usuario) {
		RespuestaDto respuesta = new RespuestaDto();

		try {
			notaPedidoService.registrarReversion(notaPedido);
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("Ok");
			respuesta.setObjeto(notaPedido);
		} catch (Exception ex) {
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}

		return respuesta;
	}

	@RequestMapping(value = "/actualizar", method = RequestMethod.PUT)
	public RespuestaDto actualizarNotaPedido(@RequestBody PedidoDto pedidoDto,
			@AuthenticationPrincipal UsuarioAuthenticate usuario) {
		RespuestaDto respuesta = new RespuestaDto();
		try {
			notaPedidoService.actualizar(pedidoDto, usuario);
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("Ok");
			respuesta.setObjeto(pedidoDto.getNotaPedido());
		} catch (Exception ex) {
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}
		return respuesta;
	}

	@RequestMapping(value = "/eliminar", method = RequestMethod.PUT)
	public RespuestaDto eliminarNotaPedido(@RequestBody NotaPedido notaPedido,
			@AuthenticationPrincipal UsuarioAuthenticate usuario) {
		RespuestaDto respuesta = new RespuestaDto();
		try {
			notaPedidoService.eliminar(notaPedido, usuario.getId());
			respuesta.setEstado(true);
			respuesta.setMensaje("Ok");
			respuesta.setObjeto(notaPedido);
		} catch (Exception ex) {
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}
		return respuesta;
	}

	@RequestMapping(value = "/eliminar/suscripcion", method = RequestMethod.PUT)
	public RespuestaDto eliminarSuscripcion(@RequestBody NotaPedido notaPedido,
			@AuthenticationPrincipal UsuarioAuthenticate usuario) {
		RespuestaDto respuesta = new RespuestaDto();
		try {
			notaPedidoService.eliminarSuscripcion(notaPedido, usuario.getId());
			respuesta.setEstado(true);
			respuesta.setMensaje("Ok");
			respuesta.setObjeto(notaPedido);
		} catch (Exception ex) {
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}
		return respuesta;
	}

	@RequestMapping(value = "/eliminarDetalle", method = RequestMethod.PUT)
	public RespuestaDto eliminarDetalleNotaPedido(
			@RequestBody DetalleNotaPedido detalleNotaPedido,
			@AuthenticationPrincipal UsuarioAuthenticate usuario) {
		RespuestaDto respuesta = new RespuestaDto();
		PedidoDto pedidoDto = new PedidoDto();

		try {
			notaPedidoService.eliminarDetalle(detalleNotaPedido,
					usuario.getId(), pedidoDto);
			respuesta.setEstado(true);
			respuesta.setMensaje("Ok");
			respuesta.setObjeto(pedidoDto);
		} catch (Exception ex) {
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}
		return respuesta;
	}

	@RequestMapping(value = "/aprobar", method = RequestMethod.PUT)
	public RespuestaDto aprobarNotaPedido(@RequestBody NotaPedido notaPedido,
			@AuthenticationPrincipal UsuarioAuthenticate usuario) {
		RespuestaDto respuesta = new RespuestaDto();
		try {
			notaPedidoService.aprobar(notaPedido, usuario.getId());
			respuesta.setEstado(true);
			respuesta.setMensaje("Ok");
			respuesta.setObjeto(notaPedido);
		} catch (Exception ex) {
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}
		return respuesta;
	}

	@RequestMapping(value = "/cancelar", method = RequestMethod.PUT)
	public RespuestaDto cancelarNotaPedido(@RequestBody NotaPedido notaPedido,
			@AuthenticationPrincipal UsuarioAuthenticate usuario) {
		RespuestaDto respuesta = new RespuestaDto();
		try {
			notaPedidoService.cancelar(notaPedido, usuario.getId());
			respuesta.setEstado(true);
			respuesta.setMensaje("Ok");
			respuesta.setObjeto(notaPedido);
		} catch (Exception ex) {
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}
		return respuesta;
	}

	@RequestMapping(value = "/buscar", method = RequestMethod.PUT)
	public RespuestaDto buscaNotaPedido(@RequestBody Integer id) {
		RespuestaDto respuesta = new RespuestaDto();
		try {
			NotaPedido np = notaPedidoService.buscarNotaPedido(id);
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("Busqueda exitosa");
			respuesta.setObjeto(np);
		} catch (Exception ex) {
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}

		return respuesta;

	}
	@JsonView(ViewOT.PublicView.class)
	@RequestMapping(value = "/tareas", method = RequestMethod.GET)
	public List<Tarea> traerTareas(
			@AuthenticationPrincipal UsuarioAuthenticate usuario) {
		return tareaService.traerTareas(usuario);
	}

}
