package com.tecnolpet.ot.service;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tecnolpet.ot.constant.SotApp;
import com.tecnolpet.ot.dto.ActualizacionProductoDto;
import com.tecnolpet.ot.dto.ClienteSuscripcionDto;
import com.tecnolpet.ot.dto.ConsultaOtDto;
import com.tecnolpet.ot.dto.DetalleTareaDto;
import com.tecnolpet.ot.dto.PedidoDto;
import com.tecnolpet.ot.dto.SeguimientoDto;
import com.tecnolpet.ot.dto.SuscripcionPedidoDto;
import com.tecnolpet.ot.model.Acceso;
import com.tecnolpet.ot.model.Catalogo;
import com.tecnolpet.ot.model.Cliente;
import com.tecnolpet.ot.model.DetalleNotaPedido;
import com.tecnolpet.ot.model.Enlace;
import com.tecnolpet.ot.model.NotaPedido;
import com.tecnolpet.ot.model.PerfilEmpresa;
import com.tecnolpet.ot.model.Producto;
import com.tecnolpet.ot.model.ProductoActualizacion;
import com.tecnolpet.ot.model.ProductoActualizacionDetalle;
import com.tecnolpet.ot.model.Seguimiento;
import com.tecnolpet.ot.model.Suscripcion;
import com.tecnolpet.ot.model.TareaDetalleNotaPedido;
import com.tecnolpet.ot.model.TipoOperacion;
import com.tecnolpet.ot.model.Usuario;
import com.tecnolpet.ot.model.view.VistaProcesoSuscripcion;
import com.tecnolpet.ot.model.view.VistaVendidos;
import com.tecnolpet.ot.repository.AccesoRepository;
import com.tecnolpet.ot.repository.CatalogoRepository;
import com.tecnolpet.ot.repository.ClienteRepository;
import com.tecnolpet.ot.repository.DetalleNotaPedidoRepository;
import com.tecnolpet.ot.repository.EnlaceRepository;
import com.tecnolpet.ot.repository.NotaPedidoRepository;
import com.tecnolpet.ot.repository.PerfilEmpresaRepository;
import com.tecnolpet.ot.repository.ProductoRepository;
import com.tecnolpet.ot.repository.SeguimientoRepository;
import com.tecnolpet.ot.repository.SucursalRepository;
import com.tecnolpet.ot.repository.SuscripcionRepository;
import com.tecnolpet.ot.repository.TareaDetalleNotaPedidoRepository;
import com.tecnolpet.ot.repository.TipoOperacionRepository;
import com.tecnolpet.ot.repository.UsuarioRepository;
import com.tecnolpet.ot.repository.VistaProcesoSuscripcionRepository;
import com.tecnolpet.ot.repository.VistaVendidosRepository;
import com.tecnolpet.ot.seguridad.UsuarioAuthenticate;
import com.tecnolpet.ot.utils.ClavesUtils;

@Service
@PropertySources(value = { @PropertySource("classpath:/application.properties") })
public class SuscripcionService {

	@Autowired
	private NotaPedidoRepository notaPedidoRepository;

	@Autowired
	private DetalleNotaPedidoRepository detalleNotaPedidoRepository;

	@Autowired
	private CatalogoRepository catalogoRepository;
	@Autowired
	private VistaProcesoSuscripcionRepository vistaProcesoSuscripcionRepository;
	@Autowired
	private VistaVendidosRepository vistaMasVendidosRepository;

	@Autowired
	private TipoOperacionRepository tipoOperacionRepository;

	@Autowired
	private SuscripcionRepository suscripcionRepository;

	@Autowired
	private EnlaceRepository enlaceRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private AccesoRepository accesoRepository;

	@Autowired
	private ProductoRepository productoRepository;

	@Autowired
	private SucursalRepository sucursalRepository;

	@Autowired
	private TareaDetalleNotaPedidoRepository tareaDetalleNotaPedidoRepository;

	@Autowired
	private PerfilEmpresaRepository perfilEmpresaRepository;

	@Autowired
	private Environment env;

	@Autowired
	private EnviarCorreoService enviarCorreoService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private SeguimientoRepository seguimientoRepository;

	public SuscripcionPedidoDto generarSuscripcion(Integer idPedido) {

		SuscripcionPedidoDto suscripcion = new SuscripcionPedidoDto();
		Catalogo registrado = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosPedido.REGISTRADO).get(0);
		NotaPedido notaPedido = notaPedidoRepository.findOne(idPedido);
		List<DetalleNotaPedido> detalles = detalleNotaPedidoRepository
				.findDetalleNotaPedido(notaPedido, registrado);
		List<DetalleTareaDto> listaServicios = new ArrayList<DetalleTareaDto>();

		for (DetalleNotaPedido det : detalles) {
			DetalleTareaDto detalleTareaDto = new DetalleTareaDto();
			det.setTareas(tareaDetalleNotaPedidoRepository
					.findTareasDetalleNotaPedido(det));
			detalleTareaDto.setDetalleNotapedido(det);
			detalleTareaDto.setListaTareas(tareaDetalleNotaPedidoRepository
					.findTareas(det));

			listaServicios.add(detalleTareaDto);
		}

		suscripcion.setNotaPedido(notaPedido);
		suscripcion.setDetalles(detalles);
		suscripcion.setSuscripciones(generaraListaSuscripciones(detalles));
		suscripcion.setDetalleServicio(listaServicios);
		return suscripcion;
	}

	public SuscripcionPedidoDto traerSuscripciones(Integer idPedido) {

		SuscripcionPedidoDto suscripcion = new SuscripcionPedidoDto();
		Catalogo registrado = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosPedido.REGISTRADO).get(0);
		NotaPedido notaPedido = notaPedidoRepository.findOne(idPedido);
		List<DetalleNotaPedido> detalles = detalleNotaPedidoRepository
				.findDetalleNotaPedido(notaPedido, registrado);

		List<DetalleTareaDto> listaTarea = new ArrayList<DetalleTareaDto>();

		for (DetalleNotaPedido det : detalles) {
			det.setTareas(tareaDetalleNotaPedidoRepository
					.findTareasDetalleNotaPedido(det));
			DetalleTareaDto dto = new DetalleTareaDto();
			dto.setDetalleNotapedido(det);

			List<TareaDetalleNotaPedido> detalleTareas = new ArrayList<TareaDetalleNotaPedido>();

			for (TareaDetalleNotaPedido detTarea : tareaDetalleNotaPedidoRepository
					.findTareas(det)) {
				Seguimiento seguimiento = seguimientoRepository
						.findFinalByTarea(detTarea.getId());
				SeguimientoDto dtoSeguimiento = new SeguimientoDto();
				if (seguimiento != null) {
					dtoSeguimiento.setCantidad(seguimiento.getCantidad());
					dtoSeguimiento.setObservacion(seguimiento.getObservacion());

				}
				detTarea.setSeguimiento(dtoSeguimiento);
				detalleTareas.add(detTarea);
			}

			dto.setListaTareas(detalleTareas);

			listaTarea.add(dto);
		}
		suscripcion.setNotaPedido(notaPedido);
		suscripcion.setDetalles(detalles);
		suscripcion.setDetalleServicio(listaTarea);

		return suscripcion;
	}

	@Transactional
	public void creaSuscripciones(SuscripcionPedidoDto suscripcionPedidoDto,
			UsuarioAuthenticate usuario) throws MailAuthenticationException,
			MailSendException, UnsupportedEncodingException, MessagingException {

		NotaPedido notaPedido = suscripcionPedidoDto.getNotaPedido();

		Catalogo aprobada = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosPedido.APROBADO).get(0);

		Usuario usuarioApr = usuarioRepository.findOne(usuario.getId());

		notaPedido.setIdCatalogo(aprobada);
		notaPedido.setUsuarioAprobador(usuarioApr);
		// notaPedido.setUsuarioAprobacion(usuario.getId());
		notaPedido.setFechaHoraAprobacion(new Timestamp(Calendar.getInstance()
				.getTime().getTime()));

		notaPedidoRepository.save(notaPedido);

		List<DetalleTareaDto> listaTarea = suscripcionPedidoDto
				.getDetalleServicio();

		for (DetalleTareaDto detalleTarea : listaTarea) {
			List<TareaDetalleNotaPedido> listaTareasDetalle = detalleTarea
					.getListaTareas();
			for (TareaDetalleNotaPedido tareaDeatalle : listaTareasDetalle) {
				tareaDeatalle.setDetalleNotaPedido(detalleTarea
						.getDetalleNotapedido());
				tareaDetalleNotaPedidoRepository.save(tareaDeatalle);
			}
		}

		// notificar
		enviarCorreoService.armarPedidoGestionHtml(notaPedido);

	}

	@Transactional
	public void aprobarSuscripciones(SuscripcionPedidoDto suscripcionPedidoDto,
			Integer idUsuario) throws Exception {

		NotaPedido notaPedido = suscripcionPedidoDto.getNotaPedido();

		Catalogo aprobada = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosPedido.GENERADA).get(0);

		Usuario usuarioApr = usuarioRepository.findOne(idUsuario);

		notaPedido.setIdCatalogo(aprobada);
		// notaPedido.setUsuarioAprobacion(idUsuario);
		notaPedido.setUsuarioAprobador(usuarioApr);
		notaPedido.setFechaHoraAprobacion(new Timestamp(Calendar.getInstance()
				.getTime().getTime()));

		notaPedidoRepository.save(notaPedido);

		// notificar
		enviarCorreoService.armarPedidoAprobarHtml(notaPedido);

	}

	private List<Suscripcion> generaraListaSuscripciones(
			List<DetalleNotaPedido> detalles) {
		List<Suscripcion> listaSuscripciones = new ArrayList<Suscripcion>();
		Catalogo latente = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosSuscripciones.LATENTE).get(0);
		TipoOperacion tipoOperacion = tipoOperacionRepository
				.findOne(SotApp.TipoOperacion.NUEVASUSCRIPCION);

		Suscripcion suscripcion;

		for (DetalleNotaPedido detalle : detalles) {
			if (detalle.getProducto().isAplicaSuscripcion()) {
				Integer cantidad;
				if (detalle.getCantidad() == null) {
					cantidad = 1;
				} else {

					cantidad = detalle.getCantidad();
				}
				for (int i = 0; i < cantidad; i++) {
					suscripcion = new Suscripcion();

					suscripcion
							.setCliente(detalle.getNotaPedido().getCliente());
					suscripcion.setDemo(Boolean.FALSE);
					suscripcion.setEnlace(null);
					suscripcion.setCatalogo(latente);
					suscripcion.setDetalleNotaPedido(detalle);
					suscripcion.setFechaHoraRegistro(new Timestamp(Calendar
							.getInstance().getTime().getTime()));
					suscripcion.setNotaPedido(detalle.getNotaPedido());
					suscripcion.setTipoOperacion(tipoOperacion);
					suscripcion.setCosto(detalle.getTotal());
					suscripcion.setProducto(detalle.getProducto());
					listaSuscripciones.add(suscripcion);
				}

			}
		}

		return listaSuscripciones;

	}

	@Transactional
	public void crearEnlance(Enlace enlace) {

		enlaceRepository.save(enlace);
	}

	public List<ClienteSuscripcionDto> traeClientesActivos() {
		ClienteSuscripcionDto clienteDto;
		List<ClienteSuscripcionDto> lista = new ArrayList<ClienteSuscripcionDto>();

		Catalogo activo = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosSuscripciones.ACTIVA).get(0);
		List<Cliente> clientes = suscripcionRepository
				.findClientesActivos(activo);

		for (Cliente cliente : clientes) {
			clienteDto = new ClienteSuscripcionDto();
			clienteDto.setCliente(cliente);
			clienteDto.setNumeroSuscripciones(suscripcionRepository
					.findNumeroSuscripciones(activo, cliente));
			lista.add(clienteDto);

		}
		return lista;
	}

	public List<Suscripcion> traeSuscripcionesByCliente(Integer idCliente) {
		Cliente cliente = clienteRepository.findOne(idCliente);
		Catalogo activo = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosSuscripciones.ACTIVA).get(0);

		return suscripcionRepository
				.findSuscripcionesByCliente(activo, cliente);

	}

	public List<Acceso> generarAccesos(Integer idSuscripcion) {
		Suscripcion suscripcion = suscripcionRepository.findOne(idSuscripcion);

		List<Acceso> lista = accesoRepository.findBySuscripcion(suscripcion);

		if (lista.size() <= 0) {
			lista = generarAccesosTemporales(suscripcion);

		}

		return lista;

	}

	public Acceso generarAccesoGratis(Integer idSuscripcion) {
		Suscripcion suscripcion = suscripcionRepository.findOne(idSuscripcion);

		Catalogo gratis = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosTipoAcceso.OBSEQUIO).get(0);
		Catalogo estado = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosAcceso.GENERADO).get(0);

		Acceso acceso;

		acceso = new Acceso();
		acceso.setSuscripcion(suscripcion);
		acceso.setCatalogoTipoAcceso(gratis);
		acceso.setCatalogoEstado(estado);
		acceso.setFechaInicio(suscripcion.getFechaInicio());
		acceso.setFechaVencimiento(suscripcion.getFechaVencimiento());
		acceso.setClave(ClavesUtils.generarClaves(
				SotApp.AlgoritmoClaves.GENERACLAVE, 8));
		acceso.setUsuario(generaUsuario());

		return acceso;

	}

	@SuppressWarnings("unused")
	public List<PedidoDto> traerPedidos(ConsultaOtDto consultaDto,
			UsuarioAuthenticate usuario) {

		List<PedidoDto> listaDto = new ArrayList<PedidoDto>();
		Catalogo estado = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosPedido.REGISTRADO).get(0);

		List<NotaPedido> lista = null;
		PerfilEmpresa perfilEmpresa = perfilEmpresaRepository.findOne(usuario
				.getPerfil_empresa());

		if (consultaDto.getTipo().equals(1)) {
			lista = notaPedidoRepository.findNotasPorCliente(
					perfilEmpresa.getEmpresa(), consultaDto.getFechaDesde(),
					consultaDto.getFechaHasta(), usuario.getCliente());

		}

		if (consultaDto.getTipo().equals(2)) {
			lista = notaPedidoRepository.findNotasPorClienteCierre(
					perfilEmpresa.getEmpresa(), consultaDto.getFechaDesde(),
					consultaDto.getFechaHasta(), usuario.getCliente());

		}
		if (consultaDto.getTipo().equals(3)) {

			lista = notaPedidoRepository.findNotasPorClienteFiltros(
					perfilEmpresa.getEmpresa(), usuario.getCliente(), "%"
							+ consultaDto.getServicio() + "%", "%"
							+ consultaDto.getEquipo() + "%",
					"%" + consultaDto.getSerie() + "%",
					"%" + consultaDto.getOet() + "%");

		}

		if (consultaDto.getTipo().equals(4)) {

			if (consultaDto.getCliente() == null) {
				lista = notaPedidoRepository.findNotasPorClienteCierre(
						perfilEmpresa.getEmpresa(),
						consultaDto.getFechaDesde(),
						consultaDto.getFechaHasta());
			} else {
				lista = notaPedidoRepository.findNotasPorClienteCierre(
						perfilEmpresa.getEmpresa(),
						consultaDto.getFechaDesde(),
						consultaDto.getFechaHasta(), consultaDto.getCliente());

			}

		}

		if (consultaDto.getTipo().equals(5)) {
			if (consultaDto.getCliente() == null) {
				lista = notaPedidoRepository.findNotasPorCliente(
						perfilEmpresa.getEmpresa(),
						consultaDto.getFechaDesde(),
						consultaDto.getFechaHasta());
			} else {
				lista = notaPedidoRepository.findNotasPorCliente(
						perfilEmpresa.getEmpresa(),
						consultaDto.getFechaDesde(),
						consultaDto.getFechaHasta(), consultaDto.getCliente());

			}

		}

		if (consultaDto.getTipo().equals(6)) {
			if (consultaDto.getCliente() == null) {

				lista = notaPedidoRepository.findNotasPorClienteFiltrosOit(
						perfilEmpresa.getEmpresa(),
						"%" + consultaDto.getServicio() + "%", "%"
								+ consultaDto.getEquipo() + "%", "%"
								+ consultaDto.getSerie() + "%", "%"
								+ consultaDto.getOet() + "%",
						"%" + consultaDto.getOit() + "%",
						"%" + consultaDto.getReporte() + "%");
			} else {

				lista = notaPedidoRepository.findNotasPorClienteFiltrosOit(
						perfilEmpresa.getEmpresa(), consultaDto.getCliente(),
						"%" + consultaDto.getServicio() + "%", "%"
								+ consultaDto.getEquipo() + "%", "%"
								+ consultaDto.getSerie() + "%", "%"
								+ consultaDto.getOet() + "%",
						"%" + consultaDto.getOit() + "%",
						"%" + consultaDto.getReporte() + "%");
			}

		}

		if (consultaDto.getTipo().equals(7)) {
			if (consultaDto.getCliente() == null) {
				lista = notaPedidoRepository
						.findNotasPorClienteFiltrosOitSeguimiento(
								perfilEmpresa.getEmpresa(),
								"%" + consultaDto.getServicio() + "%", "%"
										+ consultaDto.getEquipo() + "%", "%"
										+ consultaDto.getSerie() + "%", "%"
										+ consultaDto.getOet() + "%", "%"
										+ consultaDto.getOit() + "%");
			} else {

				lista = notaPedidoRepository
						.findNotasPorClienteFiltrosOitSeguimiento(
								perfilEmpresa.getEmpresa(),
								consultaDto.getCliente(),
								"%" + consultaDto.getServicio() + "%", "%"
										+ consultaDto.getEquipo() + "%", "%"
										+ consultaDto.getSerie() + "%", "%"
										+ consultaDto.getOet() + "%", "%"
										+ consultaDto.getOit() + "%");
			}

		}
		for (NotaPedido np : lista) {
			PedidoDto dto = new PedidoDto();
			Integer num = 0;
			Integer equipos = 0;

			List<DetalleNotaPedido> listaDetalles = detalleNotaPedidoRepository
					.findDetalleNotaPedido(np, estado);
			dto.setDetalles(listaDetalles);
			for (DetalleNotaPedido dp : listaDetalles) {
				equipos = equipos + dp.getCantidad();
				for (TareaDetalleNotaPedido t : dp.getTareasDetalleNotaPedido()) {
					num = num + dp.getCantidad();
				}

				dto.setTotalServicios(num);
			}

			np.setCantidadEquipos(equipos);
			dto.setNotaPedido(np);

			listaDto.add(dto);

		}
		
		
		return listaDto;
	}

	@SuppressWarnings("unused")
	public List<PedidoDto> traerPedidosServicio(ConsultaOtDto consultaDto,
			UsuarioAuthenticate usuario) {

		List<PedidoDto> listaDto = new ArrayList<PedidoDto>();
		Catalogo estado = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosPedido.REGISTRADO).get(0);

		List<NotaPedido> lista = null;
		PerfilEmpresa perfilEmpresa = perfilEmpresaRepository.findOne(usuario
				.getPerfil_empresa());

		if (consultaDto.getTipo().equals(1)) {
			lista = notaPedidoRepository.findNotasPorCliente(
					perfilEmpresa.getEmpresa(), consultaDto.getFechaDesde(),
					consultaDto.getFechaHasta(), usuario.getCliente());

		}

		if (consultaDto.getTipo().equals(2)) {
			lista = notaPedidoRepository.findNotasPorClienteCierre(
					perfilEmpresa.getEmpresa(), consultaDto.getFechaDesde(),
					consultaDto.getFechaHasta(), usuario.getCliente());

		}
		if (consultaDto.getTipo().equals(3)) {

			lista = notaPedidoRepository.findNotasPorClienteFiltros(
					perfilEmpresa.getEmpresa(), usuario.getCliente(), "%"
							+ consultaDto.getServicio() + "%", "%"
							+ consultaDto.getEquipo() + "%",
					"%" + consultaDto.getSerie() + "%",
					"%" + consultaDto.getOet() + "%");

		}

		if (consultaDto.getTipo().equals(4)) {

			if (consultaDto.getCliente() == null) {
				lista = notaPedidoRepository.findNotasPorClienteCierre(
						perfilEmpresa.getEmpresa(),
						consultaDto.getFechaDesde(),
						consultaDto.getFechaHasta());
			} else {
				lista = notaPedidoRepository.findNotasPorClienteCierre(
						perfilEmpresa.getEmpresa(),
						consultaDto.getFechaDesde(),
						consultaDto.getFechaHasta(), consultaDto.getCliente());

			}

		}

		if (consultaDto.getTipo().equals(5)) {
			if (consultaDto.getCliente() == null) {
				lista = notaPedidoRepository.findNotasPorCliente(
						perfilEmpresa.getEmpresa(),
						consultaDto.getFechaDesde(),
						consultaDto.getFechaHasta());
			} else {
				lista = notaPedidoRepository.findNotasPorCliente(
						perfilEmpresa.getEmpresa(),
						consultaDto.getFechaDesde(),
						consultaDto.getFechaHasta(), consultaDto.getCliente());

			}

		}

		if (consultaDto.getTipo().equals(6)) {
			if (consultaDto.getCliente() == null) {

				lista = notaPedidoRepository.findNotasPorClienteFiltrosOit(
						perfilEmpresa.getEmpresa(),
						"%" + consultaDto.getServicio() + "%", "%"
								+ consultaDto.getEquipo() + "%", "%"
								+ consultaDto.getSerie() + "%", "%"
								+ consultaDto.getOet() + "%",
						"%" + consultaDto.getOit() + "%",
						"%" + consultaDto.getReporte() + "%");
			} else {

				System.out.println("%" + consultaDto.getServicio() + "%");
				System.out.println("%" + consultaDto.getEquipo() + "%");
				System.out.println("%" + consultaDto.getSerie() + "%");
				System.out.println("%" + consultaDto.getOet() + "%");
				System.out.println("%" + consultaDto.getOit() + "%");
				System.out.println("%" + consultaDto.getReporte() + "%");
				
				
				lista = notaPedidoRepository.findNotasPorClienteFiltrosOit(
						perfilEmpresa.getEmpresa(), consultaDto.getCliente(),
						"%" + consultaDto.getServicio() + "%",
						"%"	+ consultaDto.getEquipo() + "%", 
						"%"	+ consultaDto.getSerie() + "%", 
						"%"	+ consultaDto.getOet() + "%",
						"%" + consultaDto.getOit() + "%",
						"%" + consultaDto.getReporte() + "%");
			}

		}

		if (consultaDto.getTipo().equals(7)) {
			if (consultaDto.getCliente() == null) {
				lista = notaPedidoRepository
						.findNotasPorClienteFiltrosOitSeguimiento(
								perfilEmpresa.getEmpresa(),
								"%" + consultaDto.getServicio() + "%", "%"
										+ consultaDto.getEquipo() + "%", "%"
										+ consultaDto.getSerie() + "%", "%"
										+ consultaDto.getOet() + "%", "%"
										+ consultaDto.getOit() + "%");
			} else {

				lista = notaPedidoRepository
						.findNotasPorClienteFiltrosOitSeguimiento(
								perfilEmpresa.getEmpresa(),
								consultaDto.getCliente(),
								"%" + consultaDto.getServicio() + "%", "%"
										+ consultaDto.getEquipo() + "%", "%"
										+ consultaDto.getSerie() + "%", "%"
										+ consultaDto.getOet() + "%", "%"
										+ consultaDto.getOit() + "%");
			}

		}
		for (NotaPedido np : lista) {
			PedidoDto dto = new PedidoDto();
			Integer num = 0;
			Integer equipos = 0;

			List<DetalleNotaPedido> listaDetalles = detalleNotaPedidoRepository
					.findDetalleNotaPedido(np, estado);
			dto.setDetalles(listaDetalles);
			for (DetalleNotaPedido dp : listaDetalles) {
				equipos = equipos + dp.getCantidad();
				for (TareaDetalleNotaPedido t : dp.getTareasDetalleNotaPedido()) {
					num = num + dp.getCantidad();
				}
				dto.getDetalleServicios().addAll(
						tareaDetalleNotaPedidoRepository.findTareasReporte(dp,
								"%" + consultaDto.getReporte() + "%"));
				/* dp.setTareaServicios(dp.getTareasDetalleNotaPedido());*/
				 
				dto.setTotalServicios(num);
			}

			np.setCantidadEquipos(equipos);
			dto.setNotaPedido(np);

			listaDto.add(dto);

		}

		
		return listaDto;
	}

	@SuppressWarnings("unused")
	public List<TareaDetalleNotaPedido> traerExperiencia(
			ConsultaOtDto consultaDto, UsuarioAuthenticate usuario) {

		Catalogo estado = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosPedido.REGISTRADO).get(0);

		List<TareaDetalleNotaPedido> lista = null;
		PerfilEmpresa perfilEmpresa = perfilEmpresaRepository.findOne(usuario
				.getPerfil_empresa());

		if (consultaDto.getTipo().equals(1)) {
			lista = notaPedidoRepository.findExperienciaFechas(
					perfilEmpresa.getEmpresa(), consultaDto.getFechaDesde(),
					consultaDto.getFechaHasta(), consultaDto.getTecnico());
		} else {

			if (consultaDto.getTipo().equals(6)) {
				lista = notaPedidoRepository.findExperiencia(
						perfilEmpresa.getEmpresa(),
						"%" + consultaDto.getServicio() + "%", "%"
								+ consultaDto.getReporte() + "%",
						consultaDto.getTecnico());

			}
		}

		return lista;
	}

	@Transactional
	public void guardarSeguimiento(Seguimiento seguimiento) {
		Catalogo activo = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosGenerales.ACTIVO).get(0);

		seguimiento.setFecha(new Date());
		seguimiento.setCatalogo(activo);
		seguimientoRepository.save(seguimiento);

		// notificar por email
		try {
			enviarCorreoService.notificarSeguimiento(seguimiento);
		} catch (MailAuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MailSendException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Transactional
	public void registrarAccesos(List<Acceso> listaAccesos) throws Exception {

		Catalogo generado = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosAcceso.GENERADO).get(0);

		Catalogo procesado = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosAcceso.PROCESADO).get(0);

		if (!validarAccesosGenerados(listaAccesos)) {
			throw new Exception("No existen accesos generados");
		}

		for (Acceso acceso : listaAccesos) {
			if (acceso.getCatalogoEstado().getId().equals(generado.getId())) {
				acceso.setCatalogoEstado(procesado);
				acceso.setActivo(Boolean.FALSE);
				accesoRepository.save(acceso);

			}
		}

	}

	@Transactional
	public void registrarAccesosTemporales(List<Acceso> listaAccesos)
			throws Exception {
		Catalogo activoadoTmp = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosAcceso.ACTIVADO_TEMPORAL).get(0);

		Catalogo procesado = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosAcceso.PROCESADO).get(0);

		if (!validarAccesosProcesados(listaAccesos)) {
			throw new Exception(
					"Los accesos seleccionados deben estar procesados para la activación temporal");
		}

		for (Acceso acceso : listaAccesos) {
			if (acceso.getCatalogoEstado().getId().equals(procesado.getId())) {
				acceso.setCatalogoEstado(activoadoTmp);
				accesoRepository.save(acceso);
				// enviar email
				// notificarAcceso(acceso);
			}
		}
	}

	@Transactional
	public void resetarAccesos(Acceso acceso) throws Exception {
		Catalogo activoadoTmp = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosAcceso.ACTIVADO_TEMPORAL).get(0);

		Catalogo enviado = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosAcceso.ENVIADO).get(0);
		List<Acceso> lista = new ArrayList<Acceso>();
		lista.add(acceso);

		if (!validarAccesosActivados(lista)) {
			throw new Exception(
					"Los accesos seleccionados deben estar activados");
		}

		if (acceso.getCatalogoEstado().getId().equals(activoadoTmp.getId())
				|| acceso.getCatalogoEstado().getId().equals(enviado.getId())) {
			// acceso.setCatalogoEstado(activoadoTmp);
			acceso.setClave(ClavesUtils.generarClaves(
					SotApp.AlgoritmoClaves.GENERACLAVE, 8));
			accesoRepository.save(acceso);

		}
	}

	private boolean validarAccesosGenerados(List<Acceso> listaAccesos) {

		boolean resp = true;
		Integer contadorAccesos = 0;
		Catalogo generado = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosAcceso.GENERADO).get(0);

		for (Acceso acceso : listaAccesos) {
			if (acceso.getCatalogoEstado().getId().equals(generado.getId())) {
				contadorAccesos++;
			}
		}

		if (contadorAccesos == 0) {
			resp = false;
		}

		return resp;
	}

	private boolean validarAccesosProcesados(List<Acceso> listaAccesos) {

		boolean resp = true;
		Integer contadorAccesos = 0;
		Catalogo generado = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosAcceso.PROCESADO).get(0);

		for (Acceso acceso : listaAccesos) {
			if (acceso.getCatalogoEstado().getId().equals(generado.getId())) {
				contadorAccesos++;
			}
		}

		if (contadorAccesos == 0) {
			resp = false;
		}

		return resp;
	}

	private boolean validarAccesosActivados(List<Acceso> listaAccesos) {

		boolean resp = true;
		Integer contadorAccesos = 0;
		Catalogo activado = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosAcceso.ACTIVADO_TEMPORAL).get(0);

		Catalogo enviado = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosAcceso.ENVIADO).get(0);

		for (Acceso acceso : listaAccesos) {
			if (acceso.getCatalogoEstado().getId().equals(activado.getId())
					|| acceso.getCatalogoEstado().getId()
							.equals(enviado.getId())) {
				contadorAccesos++;
			}
		}

		if (contadorAccesos == 0) {
			resp = false;
		}

		return resp;
	}

	private List<Acceso> generarAccesosTemporales(Suscripcion suscripcion) {

		Integer accesos = suscripcion.getDetalleNotaPedido().getNumeroAccesos();
		Catalogo normal = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosTipoAcceso.NORMAL).get(0);
		Catalogo estado = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosAcceso.GENERADO).get(0);
		Acceso acceso;
		List<Acceso> lista = new ArrayList<Acceso>();

		for (int i = 0; i < accesos; i++) {
			acceso = new Acceso();
			acceso.setSuscripcion(suscripcion);
			acceso.setCatalogoTipoAcceso(normal);
			acceso.setCatalogoEstado(estado);
			acceso.setFechaInicio(suscripcion.getFechaInicio());
			acceso.setFechaVencimiento(suscripcion.getFechaVencimiento());
			acceso.setClave(ClavesUtils.generarClaves(
					SotApp.AlgoritmoClaves.GENERACLAVE, 8));
			acceso.setUsuario(generaUsuario());
			lista.add(acceso);

		}

		return lista;

	}

	private String generaUsuario() {

		Long numeroUsuario = accesoRepository.getNumeroUsuario();

		return "EDLE-" + numeroUsuario.toString();

	}

	public Integer getSuscripcionesLatentes() {
		Catalogo catalogo = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosSuscripciones.LATENTE).get(0);
		Integer cantidad = suscripcionRepository
				.cantidadSuscripciones(catalogo);

		return cantidad;
	}

	public Integer getCantNotaPedidoRegistradas(UsuarioAuthenticate usuario) {
		Catalogo catalogo = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosPedido.REGISTRADO).get(0);
		Integer cantidad = suscripcionRepository.cantidadNotaPedido(catalogo,
				usuario.getSucursal());

		return cantidad;
	}

	public Integer getCantFacturasEnviar() {
		Catalogo catalogo = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosPedido.APROBADO).get(0);
		Integer cantidad = suscripcionRepository
				.cantidadFacturasEnviar(catalogo);
		return cantidad;
	}

	public Integer cantNotaPedidoEnviadas() {
		Catalogo catalogo = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosPedido.ENVIADA).get(0);
		Integer cantidad = suscripcionRepository
				.cantNotaPedidoEnviadas(catalogo);
		return cantidad;
	}

	public List<VistaProcesoSuscripcion> traerProcesos() {
		List<VistaProcesoSuscripcion> ProcesosSuscripciones = vistaProcesoSuscripcionRepository
				.findAll();
		return ProcesosSuscripciones;
	}

	public List<Seguimiento> traerSeguimientos(Integer idTareaDetallePedido) {

		TareaDetalleNotaPedido tarea = tareaDetalleNotaPedidoRepository
				.findOne(idTareaDetallePedido);

		List<Seguimiento> seguimientos = seguimientoRepository
				.findByTareaDetalleNotaPedidoOrderByIdDesc(tarea);

		return seguimientos;
	}

	public List<VistaVendidos> traerLosMasVendidos() {
		List<VistaVendidos> VistaMasVendidos = vistaMasVendidosRepository
				.findAll();
		return VistaMasVendidos;
	}

	public List<Suscripcion> ventasAnuales(String anio) {
		String str2 = "-01-01";
		String str3 = "-12-31";
		String inicio = anio.concat(str2);
		String fin = anio.concat(str3);
		List<Suscripcion> suscrip = suscripcionRepository.ventasAnuales(
				ParseFecha(inicio), ParseFecha(fin));
		return suscrip;
	}

	public ActualizacionProductoDto traerPorProductoActualizable(
			Integer idProducto) {

		ActualizacionProductoDto actualizacionDto = new ActualizacionProductoDto();
		ProductoActualizacion productoActualizacion = new ProductoActualizacion();
		Catalogo estado = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosSuscripciones.ACTIVADEFINITIVA).get(0);
		Producto producto = productoRepository.findOne(idProducto);
		Integer version = producto.getNumeroActualizacion() + 1;
		Integer resto = 0;
		List<ProductoActualizacionDetalle> detallesActualizacion = new ArrayList<ProductoActualizacionDetalle>();
		ProductoActualizacionDetalle detalleActualizacion = null;

		productoActualizacion.setProducto(producto);
		productoActualizacion.setVersionAnterior(producto
				.getNumeroActualizacion());
		productoActualizacion.setVersionActual(version);

		List<Suscripcion> suscripciones = suscripcionRepository
				.findByProductoActualizable(producto.getId(), (producto
						.getProducto() == null ? -1 : producto.getProducto()
						.getId()), estado.getId());

		for (Suscripcion s : suscripciones) {

			resto = version - s.getNumeroActualizacion();

			for (Integer i = 1; i < resto + 1; i++) {

				detalleActualizacion = new ProductoActualizacionDetalle();
				detalleActualizacion.setId(0);
				detalleActualizacion.setSuscripcion(s);
				detalleActualizacion.setVersion(s.getNumeroActualizacion() + i);
				detalleActualizacion.setEsActualizacion(i == 1 ? false : true);
				detallesActualizacion.add(detalleActualizacion);

			}

		}

		actualizacionDto.setProductoActualizacion(productoActualizacion);
		actualizacionDto
				.setListaProductoActualizacionDetalle(detallesActualizacion);

		return actualizacionDto;

	}

	public static Date ParseFecha(String fecha) {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaDate = null;
		try {
			fechaDate = formato.parse(fecha);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		return fechaDate;
	}

	public List<Suscripcion> traerSuscripcionesAprobadas(Integer clienteId) {
		Catalogo aprobadas = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosSuscripciones.APROBADA).get(0);

		Cliente cliente = clienteRepository.findOne(clienteId);
		List<Suscripcion> suscripcionesAprobadas = suscripcionRepository
				.findByCatalogoAndCliente(aprobadas, cliente);

		return suscripcionesAprobadas;
	}

	public void saveAccesosPorRangoIp(UsuarioAuthenticate usuario, Acceso acceso) {
		accesoRepository.save(acceso);
	}

	public void actualizarResponsables(
			TareaDetalleNotaPedido tareaDetalleNotaPedido) {

		tareaDetalleNotaPedidoRepository.save(tareaDetalleNotaPedido);

	}

}
