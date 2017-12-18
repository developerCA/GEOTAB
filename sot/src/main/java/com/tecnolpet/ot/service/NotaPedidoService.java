/**
 * 
 */
package com.tecnolpet.ot.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tecnolpet.ot.constant.SotApp;
import com.tecnolpet.ot.constant.SotApp.SecuenciasSot;
import com.tecnolpet.ot.dto.PedidoDto;
import com.tecnolpet.ot.model.Catalogo;
import com.tecnolpet.ot.model.CostoServicio;
import com.tecnolpet.ot.model.DetalleNotaPedido;
import com.tecnolpet.ot.model.Empresa;
import com.tecnolpet.ot.model.IncidenciaDetalleNotaPedido;
import com.tecnolpet.ot.model.IncidenciaNotaPedido;
import com.tecnolpet.ot.model.NotaPedido;
import com.tecnolpet.ot.model.PerfilEmpresa;
import com.tecnolpet.ot.model.Producto;
import com.tecnolpet.ot.model.Secuencia;
import com.tecnolpet.ot.model.SecuenciaOrden;
import com.tecnolpet.ot.model.Suscripcion;
import com.tecnolpet.ot.model.Tarea;
import com.tecnolpet.ot.model.TareaDetalleNotaPedido;
import com.tecnolpet.ot.repository.CatalogoRepository;
import com.tecnolpet.ot.repository.CostoServicioRepository;
import com.tecnolpet.ot.repository.DetalleNotaPedidoRepository;
import com.tecnolpet.ot.repository.IncidenciaDetalleNotaPedidoRepository;
import com.tecnolpet.ot.repository.IncidenciaNotaPedidoRepository;
import com.tecnolpet.ot.repository.NotaPedidoRepository;
import com.tecnolpet.ot.repository.PerfilEmpresaRepository;
import com.tecnolpet.ot.repository.ProductoRepository;
import com.tecnolpet.ot.repository.SecuenciaOrdenRepository;
import com.tecnolpet.ot.repository.SecuenciaRepository;
import com.tecnolpet.ot.repository.SucursalRepository;
import com.tecnolpet.ot.repository.SuscripcionRepository;
import com.tecnolpet.ot.repository.TareaDetalleNotaPedidoRepository;
import com.tecnolpet.ot.seguridad.UsuarioAuthenticate;

/**
 * @author administrador
 *
 */
@Service
public class NotaPedidoService {

	@Autowired
	private NotaPedidoRepository notaPedidoRepository;

	@Autowired
	private IncidenciaNotaPedidoRepository incidenciaNotaPedidoRepository;

	@Autowired
	private CatalogoRepository catalogoRepository;

	@Autowired
	private DetalleNotaPedidoRepository detalleNotaPedidoRepository;

	@Autowired
	private ProductoRepository productoRepository;

	@Autowired
	private SuscripcionRepository suscripcionRepository;

	@Autowired
	private IncidenciaDetalleNotaPedidoRepository incidenciaDetalleNotaPedidoRepository;

	@Autowired
	private SucursalRepository sucursalRepository;

	@Autowired
	private TareaDetalleNotaPedidoRepository tareaDetalleNotaPedidoRepository;

	@Autowired
	private PerfilEmpresaRepository perfilEmpresaRepository;

	@Autowired
	private EnviarCorreoService enviarCorreoService;

	@Autowired
	private CostoServicioRepository costoServicioRepository;

	@Autowired
	private SecuenciaRepository secuenciaRepository;

	@Autowired
	private SecuenciaOrdenRepository secuenciaOrdenRepository;

	public List<NotaPedido> getNotasPedidoRegistradasRevisadas(
			final UsuarioAuthenticate usuario) {

		PerfilEmpresa perfilEmpresa = perfilEmpresaRepository.findOne(usuario
				.getPerfil_empresa());

		return notaPedidoRepository.findNotasRegistradasAndRevisadas(
				perfilEmpresa.getEmpresa(), catalogoRepository
						.findCatalogoBySigla(SotApp.EstadosPedido.REGISTRADO)
						.get(0).getId(), catalogoRepository
						.findCatalogoBySigla(SotApp.EstadosPedido.REVISADO)
						.get(0).getId(), catalogoRepository
						.findCatalogoBySigla(SotApp.EstadosPedido.REVERSADO)
						.get(0).getId());
	}

	public List<NotaPedido> getNotasPedidoRegistradas(
			UsuarioAuthenticate usuario) {

		PerfilEmpresa perfilEmpresa = perfilEmpresaRepository.findOne(usuario
				.getPerfil_empresa());
		return notaPedidoRepository.findNotasRegistradas(catalogoRepository
				.findCatalogoBySigla(SotApp.EstadosPedido.REGISTRADO).get(0)
				.getId(), perfilEmpresa.getEmpresa());
	}

	public List<NotaPedido> getNotasPedidoAprobadasSuscripcion(
			UsuarioAuthenticate usuario) {

		PerfilEmpresa perfilEmpresa = perfilEmpresaRepository.findOne(usuario
				.getPerfil_empresa());
		return notaPedidoRepository.findNotasAprobadasSuscripcion(
				catalogoRepository
						.findCatalogoBySigla(SotApp.EstadosPedido.APROBADO)
						.get(0).getId(), perfilEmpresa.getEmpresa(),
				usuario.getCliente());

	}

	public List<Object> traerDataInicial(UsuarioAuthenticate usuario) {

		PerfilEmpresa perfilEmpresa = perfilEmpresaRepository.findOne(usuario
				.getPerfil_empresa());
		List<Object> lista = null;

		if (perfilEmpresa.getPerfil().getId() == SotApp.CLIENTE) {
			lista = notaPedidoRepository.findDashBoard(perfilEmpresa
					.getEmpresa().getId(), usuario.getCliente().getId());

		} else {
			lista = notaPedidoRepository.findDashBoard(perfilEmpresa
					.getEmpresa().getId());

		}

		return lista;
	}

	public List<Object> traerDataTick(UsuarioAuthenticate usuario) {

		PerfilEmpresa perfilEmpresa = perfilEmpresaRepository.findOne(usuario
				.getPerfil_empresa());
		List<Object> lista = null;

		Calendar fecha = Calendar.getInstance();
		int anio = fecha.get(Calendar.YEAR);

		lista = notaPedidoRepository.findTicks(perfilEmpresa.getEmpresa()
				.getId(), anio);

		return lista;
	}

	public List<Object> traerValueTick(UsuarioAuthenticate usuario) {

		PerfilEmpresa perfilEmpresa = perfilEmpresaRepository.findOne(usuario
				.getPerfil_empresa());
		List<Object> lista = null;

		Calendar fecha = Calendar.getInstance();
		int anio = fecha.get(Calendar.YEAR);

		if (perfilEmpresa.getPerfil().getId() == SotApp.CLIENTE) {
			lista = notaPedidoRepository.findDataTicks(perfilEmpresa
					.getEmpresa().getId(), anio, usuario.getCliente().getId());
		} else {

			lista = notaPedidoRepository.findDataTicks(perfilEmpresa
					.getEmpresa().getId(), anio);

		}

		return lista;
	}

	public List<Object> traerValueEquipos(UsuarioAuthenticate usuario) {

		PerfilEmpresa perfilEmpresa = perfilEmpresaRepository.findOne(usuario
				.getPerfil_empresa());
		List<Object> lista = null;

		Calendar fecha = Calendar.getInstance();
		int anio = fecha.get(Calendar.YEAR);

		if (perfilEmpresa.getPerfil().getId() == SotApp.CLIENTE) {

			lista = notaPedidoRepository.findDataTicksEquipos(perfilEmpresa
					.getEmpresa().getId(), anio, usuario.getCliente().getId());
		} else {

			lista = notaPedidoRepository.findDataTicksEquipos(perfilEmpresa
					.getEmpresa().getId(), anio);
		}

		return lista;
	}

	public List<Object> traerValueServicios(UsuarioAuthenticate usuario) {

		PerfilEmpresa perfilEmpresa = perfilEmpresaRepository.findOne(usuario
				.getPerfil_empresa());
		List<Object> lista = null;

		Calendar fecha = Calendar.getInstance();
		int anio = fecha.get(Calendar.YEAR);

		if (perfilEmpresa.getPerfil().getId() == SotApp.CLIENTE) {

			lista = notaPedidoRepository.findDataTicksServicios(perfilEmpresa
					.getEmpresa().getId(), anio, usuario.getCliente().getId());
		} else {
			lista = notaPedidoRepository.findDataTicksServicios(perfilEmpresa
					.getEmpresa().getId(), anio);

		}

		return lista;
	}

	public List<NotaPedido> traerOrdenes(UsuarioAuthenticate usuario) {

		PerfilEmpresa perfilEmpresa = perfilEmpresaRepository.findOne(usuario
				.getPerfil_empresa());
		List<NotaPedido> lista = null;

		if (perfilEmpresa.getPerfil().getId() == SotApp.CLIENTE) {

			lista = notaPedidoRepository.findOrdenes(
					perfilEmpresa.getEmpresa(), usuario.getCliente());

		} else {
			lista = notaPedidoRepository
					.findOrdenes(perfilEmpresa.getEmpresa());

		}

		return lista;
	}

	public void calcularCostos(PedidoDto pedidoDto) {
		NotaPedido np = null;
		double subtotal = 0;
		double total = 0;
		Catalogo estado = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosGenerales.ACTIVO).get(0);

		np = pedidoDto.getNotaPedido();

		for (DetalleNotaPedido dp : pedidoDto.getDetalles()) {
			subtotal = 0;
			Producto producto = productoRepository.findOne(dp.getProducto()
					.getId());
			dp.setCantidadReal(dp.getCantidad());

			for (Tarea tarea : dp.getTareas()) {
				CostoServicio costoServicio = costoServicioRepository
						.findCostosByTipoCategoriaAndTarea(estado,
								producto.getCategoria(), tarea);

				if (costoServicio != null) {
					subtotal += costoServicio.getCosto().doubleValue()
							* dp.getCantidad();
				}

			}

			dp.setSubtotal(subtotal);
			total += dp.getSubtotal();
		}

		np.setTotal(total);

	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void guardar(PedidoDto pedidoDto, final UsuarioAuthenticate usuario)
			throws Exception {

		Catalogo catalogoRegistro = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosPedido.REGISTRADO).get(0);
		Catalogo catalogoActivo = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosGenerales.ACTIVO).get(0);
		PerfilEmpresa perfilEmpresa = perfilEmpresaRepository.findOne(usuario
				.getPerfil_empresa());

		List<DetalleNotaPedido> lista = pedidoDto.getDetalles();

		NotaPedido np = pedidoDto.getNotaPedido();

		np.setIdCatalogo(catalogoRegistro);
		np.setEstado(catalogoActivo);
		np.setFechaHoraRegistro(new Timestamp(Calendar.getInstance().getTime()
				.getTime()));
		np.setFechaGestion(new Date());

		np.setPrefactura(Boolean.FALSE);
		np.setPagado(Boolean.FALSE);
		np.setUsuarioRegistro(usuario.getId());
		np.setEmpresa(perfilEmpresa.getEmpresa());

		notaPedidoRepository.save(np);

		for (DetalleNotaPedido dp : lista) {

			Producto producto = productoRepository.findOne(dp.getProducto()
					.getId());
			dp.setNotaPedido(np);
			dp.setCatalogo(catalogoRegistro);

			detalleNotaPedidoRepository.save(dp);

			for (Tarea t : dp.getTareas()) {
				TareaDetalleNotaPedido tareaDetalle = new TareaDetalleNotaPedido();
				CostoServicio costoServicio = costoServicioRepository
						.findCostosByTipoCategoriaAndTarea(catalogoActivo,
								producto.getCategoria(), t);

				tareaDetalle.setDetalleNotaPedido(dp);
				tareaDetalle.setTarea(t);
				tareaDetalle.setCodigoReporte(crearOrdenReporte(perfilEmpresa
						.getEmpresa()));

				tareaDetalle.setAlcance(t.getAlcance());
				if (costoServicio == null) {
					tareaDetalle.setCosto(0d);
					tareaDetalle.setTotal(0d);
				} else {
					tareaDetalle.setCosto(costoServicio.getCosto()
							.doubleValue());
					tareaDetalle.setTotal(tareaDetalle.getCosto()
							* dp.getCantidad());

				}

				tareaDetalleNotaPedidoRepository.save(tareaDetalle);

			}

		}

		// notificar correo
		enviarCorreoService.armarPedidoRegistroHtml(np);
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void actualizar(final PedidoDto pedidoDto,
			final UsuarioAuthenticate usuario) throws Exception {

		Catalogo catalogoModificado = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosPedido.REGISTRADO).get(0);
		Catalogo catalogoActivo = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosGenerales.ACTIVO).get(0);
		PerfilEmpresa perfilEmpresa = perfilEmpresaRepository.findOne(usuario
				.getPerfil_empresa());

		pedidoDto.getNotaPedido().setIdCatalogo(catalogoModificado);
		pedidoDto.getNotaPedido().setObservacion(null);
		NotaPedido pedido = notaPedidoRepository
				.save(pedidoDto.getNotaPedido());

		List<DetalleNotaPedido> lista = pedidoDto.getDetalles();

		for (DetalleNotaPedido detalleNotaPedido : lista) {
			Producto producto = productoRepository.findOne(detalleNotaPedido
					.getProducto().getId());

			detalleNotaPedido.setNotaPedido(pedido);
			detalleNotaPedido.setCatalogo(catalogoModificado);

			if (detalleNotaPedido.getId() == null) {
				detalleNotaPedidoRepository.save(detalleNotaPedido);
				for (Tarea t : detalleNotaPedido.getTareas()) {
					CostoServicio costoServicio = costoServicioRepository
							.findCostosByTipoCategoriaAndTarea(catalogoActivo,
									producto.getCategoria(), t);

					TareaDetalleNotaPedido tareaDetalle = new TareaDetalleNotaPedido();
					tareaDetalle.setDetalleNotaPedido(detalleNotaPedido);

					tareaDetalle.setTarea(t);
					if (null == tareaDetalle.getCodigoReporte()) {
						tareaDetalle
								.setCodigoReporte(crearOrdenReporte(perfilEmpresa
										.getEmpresa()));

					}

					if (costoServicio == null) {
						tareaDetalle.setCosto(0d);
						tareaDetalle.setTotal(0d);
					} else {
						tareaDetalle.setCosto(costoServicio.getCosto()
								.doubleValue());
						tareaDetalle.setTotal(tareaDetalle.getCosto()
								* detalleNotaPedido.getCantidad());

					}

					tareaDetalleNotaPedidoRepository.save(tareaDetalle);
				}
			}
		}

	}

	@Transactional
	public void eliminar(NotaPedido notaPedido, final Integer usuario) {
		IncidenciaNotaPedido incidenciaNotaPedido = new IncidenciaNotaPedido();
		Catalogo catalogoEliminado = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosGenerales.ELIMINADO).get(0);
		Catalogo catalogoCancelado = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosPedido.CANCELADO).get(0);

		notaPedido.setEstado(catalogoEliminado);
		notaPedido.setIdCatalogo(catalogoCancelado);
		notaPedido.setFechaHoraCancelacion(new Timestamp(Calendar.getInstance()
				.getTime().getTime()));
		notaPedido.setUsuarioCancelacion(usuario);
		notaPedidoRepository.save(notaPedido);

		incidenciaNotaPedido.setIdNotaPedido(notaPedido);
		incidenciaNotaPedido.setFechaActualizacion(new Timestamp(Calendar
				.getInstance().getTime().getTime()));
		incidenciaNotaPedido.setUsuarioModificado(usuario);
		incidenciaNotaPedido.setTipo(catalogoEliminado.getId());
		incidenciaNotaPedidoRepository.save(incidenciaNotaPedido);

	}

	@Transactional
	public void eliminarSuscripcion(NotaPedido notaPedido, final Integer usuario) {
		IncidenciaNotaPedido incidenciaNotaPedido = new IncidenciaNotaPedido();
		Catalogo catalogoEliminado = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosGenerales.ELIMINADO).get(0);
		Catalogo catalogoCancelado = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosPedido.CANCELADO).get(0);
		Catalogo catalogoCanceladoSuscripcion = catalogoRepository
				.findCatalogoBySigla(SotApp.EstadosSuscripciones.CANCELADA)
				.get(0);

		notaPedido.setEstado(catalogoEliminado);
		notaPedido.setIdCatalogo(catalogoCancelado);
		notaPedido.setFechaHoraCancelacion(new Timestamp(Calendar.getInstance()
				.getTime().getTime()));
		notaPedido.setUsuarioCancelacion(usuario);
		notaPedidoRepository.save(notaPedido);

		List<Suscripcion> suscripciones = suscripcionRepository
				.findByNotaPedido(notaPedido);

		for (Suscripcion suscripcion : suscripciones) {

			suscripcion.setCatalogo(catalogoCanceladoSuscripcion);
			suscripcion.setFechaHoraCancelacion(new Timestamp(Calendar
					.getInstance().getTime().getTime()));
			suscripcion.setUsuarioCancelacion(usuario);

			suscripcionRepository.save(suscripcion);

		}

		incidenciaNotaPedido.setIdNotaPedido(notaPedido);
		incidenciaNotaPedido.setFechaActualizacion(new Timestamp(Calendar
				.getInstance().getTime().getTime()));
		incidenciaNotaPedido.setUsuarioModificado(usuario);
		incidenciaNotaPedido.setTipo(catalogoEliminado.getId());
		incidenciaNotaPedidoRepository.save(incidenciaNotaPedido);

	}

	@Transactional
	public void eliminarDetalle(final DetalleNotaPedido detalleNotaPedido,
			final Integer usuario, PedidoDto pedidoDto) {
		IncidenciaDetalleNotaPedido incidenciaDetalleNotaPedido = new IncidenciaDetalleNotaPedido();
		Catalogo catalogoEliminado = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosPedido.CANCELADO).get(0);
		Catalogo catalogoRegistrado = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosPedido.REGISTRADO).get(0);

		detalleNotaPedido.setCatalogo(catalogoEliminado);
		DetalleNotaPedido detalle = detalleNotaPedidoRepository
				.save(detalleNotaPedido);

		pedidoDto.setNotaPedido(detalleNotaPedido.getNotaPedido());
		pedidoDto.setDetalles(detalleNotaPedidoRepository
				.findDetalleNotaPedido(pedidoDto.getNotaPedido(),
						catalogoRegistrado));

		// calcularCostos(pedidoDto);

		notaPedidoRepository.save(pedidoDto.getNotaPedido());

		incidenciaDetalleNotaPedido.setDetalleNotaPedido(detalle);
		incidenciaDetalleNotaPedido.setFechaActualizacion(new Timestamp(
				Calendar.getInstance().getTime().getTime()));
		incidenciaDetalleNotaPedido.setUsuarioModificado(usuario);
		incidenciaDetalleNotaPedido.setTipo(catalogoEliminado);
		incidenciaDetalleNotaPedidoRepository.save(incidenciaDetalleNotaPedido);
	}

	@Transactional
	public void aprobar(final NotaPedido notaPedido, final Integer usuario) {
		IncidenciaNotaPedido incidenciaNotaPedido = new IncidenciaNotaPedido();
		Catalogo catalogoAprobado = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosPedido.APROBADO).get(0);

		notaPedido.setIdCatalogo(catalogoAprobado);
		NotaPedido pedido = notaPedidoRepository.save(notaPedido);

		incidenciaNotaPedido.setIdNotaPedido(pedido);
		incidenciaNotaPedido.setFechaActualizacion(new Timestamp(Calendar
				.getInstance().getTime().getTime()));
		incidenciaNotaPedido.setUsuarioModificado(usuario);
		incidenciaNotaPedido.setTipo(catalogoAprobado.getId());
		incidenciaNotaPedidoRepository.save(incidenciaNotaPedido);
	}

	@Transactional
	public void cancelar(final NotaPedido notaPedido, final Integer usuario) {
		IncidenciaNotaPedido incidenciaNotaPedido = new IncidenciaNotaPedido();
		Catalogo catalogoCancelado = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosPedido.CANCELADO).get(0);

		notaPedido.setIdCatalogo(catalogoCancelado);
		NotaPedido pedido = notaPedidoRepository.save(notaPedido);

		incidenciaNotaPedido.setIdNotaPedido(pedido);
		incidenciaNotaPedido.setFechaActualizacion(new Timestamp(Calendar
				.getInstance().getTime().getTime()));
		incidenciaNotaPedido.setUsuarioModificado(usuario);
		incidenciaNotaPedido.setTipo(catalogoCancelado.getId());
		incidenciaNotaPedidoRepository.save(incidenciaNotaPedido);
	}

	@Transactional
	public void registrarRevision(NotaPedido notaPedido) {
		Catalogo revisada = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosPedido.REVISADO).get(0);
		notaPedido.setIdCatalogo(revisada);

		notaPedidoRepository.save(notaPedido);

	}

	@Transactional
	public void registrarReversion(NotaPedido notaPedido) {
		NotaPedido np = notaPedidoRepository.findOne(notaPedido.getId());
		Catalogo reversada = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosPedido.REVERSADO).get(0);
		np.setIdCatalogo(reversada);
		np.setObservacion("Reversado");
		notaPedidoRepository.save(np);

	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	public Integer crearOrdenInterna(UsuarioAuthenticate usuario)
			throws Exception {
		List<Secuencia> secuencias = secuenciaRepository
				.findByNombre(SecuenciasSot.ORDENES);
		PerfilEmpresa perfilEmpresa = perfilEmpresaRepository.findOne(usuario
				.getPerfil_empresa());

		Integer secuenciaActual = null;
		Integer nuevaSecuencia = null;

		if (secuencias == null) {
			throw new Exception("No existe el catalogo de la secuencia");
		}

		List<SecuenciaOrden> sucuenciaOrden = secuenciaOrdenRepository
				.findByEmpresaAndSecuencia(perfilEmpresa.getEmpresa(),
						secuencias.get(0));
		SecuenciaOrden secuenciaOrden = sucuenciaOrden.get(0);

		secuenciaActual = secuenciaOrden.getNumero();
		nuevaSecuencia = secuenciaActual + 1;
		secuenciaOrden.setNumero(nuevaSecuencia);
		secuenciaOrdenRepository.save(secuenciaOrden);

		return secuenciaActual;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	public Integer crearOrdenReporte(Empresa empresa) throws Exception {
		List<Secuencia> secuencias = secuenciaRepository
				.findByNombre(SecuenciasSot.SERVICIOS);

		Integer secuenciaActual = null;
		Integer nuevaSecuencia = null;

		if (secuencias == null) {
			throw new Exception("No existe el catalogo de la secuencia");
		}

		List<SecuenciaOrden> sucuenciaOrden = secuenciaOrdenRepository
				.findByEmpresaAndSecuencia(empresa, secuencias.get(0));
		SecuenciaOrden secuenciaOrden = sucuenciaOrden.get(0);

		secuenciaActual = secuenciaOrden.getNumero();
		nuevaSecuencia = secuenciaActual + 1;
		secuenciaOrden.setNumero(nuevaSecuencia);
		secuenciaOrdenRepository.save(secuenciaOrden);

		return secuenciaActual;
	}

	public NotaPedido buscarNotaPedido(final Integer id) {
		NotaPedido res = new NotaPedido();

		Catalogo registradas = catalogoRepository.findCatalogoBySigla("REGIST")
				.get(0);

		List<NotaPedido> notas = notaPedidoRepository
				.findNotaPedidoByEstadoAndId(registradas, id);

		if (notas.size() > 0) {
			res = notaPedidoRepository.findNotaPedidoByEstadoAndId(registradas,
					id).get(0);
		}
		return res;

	}

}
