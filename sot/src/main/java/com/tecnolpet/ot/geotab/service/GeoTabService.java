package com.tecnolpet.ot.geotab.service;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tecnolpet.ot.constant.SotApp;
import com.tecnolpet.ot.dto.DiferenciaHoraDto;
import com.tecnolpet.ot.geotab.dto.DispositivoGeotabDto;
import com.tecnolpet.ot.geotab.dto.DispositivoTableroDto;
import com.tecnolpet.ot.geotab.dto.GrupoChildrenGeoTabDto;
import com.tecnolpet.ot.geotab.dto.GrupoDispositivoDto;
import com.tecnolpet.ot.geotab.dto.GrupoGeotabDto;
import com.tecnolpet.ot.geotab.dto.LocalizazionesGeotabDto;
import com.tecnolpet.ot.geotab.dto.ProcesaDatosGeotabDto;
import com.tecnolpet.ot.geotab.dto.PuntoZonaGeotabDto;
import com.tecnolpet.ot.geotab.dto.SincronizarZonasDto;
import com.tecnolpet.ot.geotab.dto.TableroGeoTabDto;
import com.tecnolpet.ot.geotab.dto.TableroViewGeoTabDto;
import com.tecnolpet.ot.geotab.dto.TipoZonaGeotabDto;
import com.tecnolpet.ot.geotab.dto.ZonaGeotabDto;
import com.tecnolpet.ot.geotab.dto.ZonaRutaGeotabDto;
import com.tecnolpet.ot.geotab.dto.ZonaTableroDto;
import com.tecnolpet.ot.geotab.dto.ZonaTypeGeotabDto;
import com.tecnolpet.ot.model.Dispositivo;
import com.tecnolpet.ot.model.Empresa;
import com.tecnolpet.ot.model.FechaDispositivo;
import com.tecnolpet.ot.model.GeotabGrupo;
import com.tecnolpet.ot.model.Localizacion;
import com.tecnolpet.ot.model.LocalizacionDispositivo;
import com.tecnolpet.ot.model.LocalizacionZona;
import com.tecnolpet.ot.model.Punto;
import com.tecnolpet.ot.model.Ruta;
import com.tecnolpet.ot.model.TipoHorario;
import com.tecnolpet.ot.model.TipoZona;
import com.tecnolpet.ot.model.VLocalizacionDispositivo;
import com.tecnolpet.ot.model.VTablero;
import com.tecnolpet.ot.model.Zona;
import com.tecnolpet.ot.repository.DispositivoRepository;
import com.tecnolpet.ot.repository.EmpresaRepository;
import com.tecnolpet.ot.repository.FechaDispositivoRepository;
import com.tecnolpet.ot.repository.GeoTabGrupoRepository;
import com.tecnolpet.ot.repository.LocalizacionDispositivoRepository;
import com.tecnolpet.ot.repository.LocalizacionRepository;
import com.tecnolpet.ot.repository.LocalizacionZonaRepository;
import com.tecnolpet.ot.repository.PuntoRepository;
import com.tecnolpet.ot.repository.RutaRepository;
import com.tecnolpet.ot.repository.TipoHorarioRepository;
import com.tecnolpet.ot.repository.TipoZonaRepository;
import com.tecnolpet.ot.repository.VTableroRepository;
import com.tecnolpet.ot.repository.VlocalizacionRepository;
import com.tecnolpet.ot.repository.ZonaRepository;
import com.tecnolpet.ot.seguridad.UsuarioAuthenticate;
import com.tecnolpet.ot.utils.FechasUtils;
import com.tecnolpet.ot.utils.PoligonoUtils;

@Service
public class GeoTabService {

	@Autowired
	private GeoTabGrupoRepository geoTabGrupoRepository;

	@Autowired
	private DispositivoRepository dispositivoRepository;

	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
	private RutaRepository rutaRepository;

	@Autowired
	private ZonaRepository zonaRepository;

	@Autowired
	private PuntoRepository puntoRepository;

	@Autowired
	private TipoZonaRepository tipoZonaRepository;

	@Autowired
	private FechaDispositivoRepository fechaDispositivoRepository;

	@Autowired
	private LocalizacionRepository localizacionRepository;

	@Autowired
	private LocalizacionZonaRepository localizacionZonaRepository;

	@Autowired
	private TipoHorarioRepository tipoHorarioRepository;

	@Autowired
	private VlocalizacionRepository vlocalizacionRepository;

	@Autowired
	private VTableroRepository vTableroRepository;

	@Autowired
	private LocalizacionDispositivoRepository localizacionDispositivoRepository;

	public TableroGeoTabDto devolverTablero(UsuarioAuthenticate usuario) {
		Ruta ruta = usuario.getRuta();
		Date fecha = new Date();
		TableroGeoTabDto tablero = new TableroGeoTabDto();

		procesarHoarasProgramadas(ruta.getCodigo(), fecha);

		List<VTablero> listadoDatos = vTableroRepository
				.findByCodigoRutaAndFechaOrderByOrdenAsc(ruta.getCodigo(),
						fecha);

		List<ZonaTableroDto> listaZonas = vTableroRepository
				.findByCodigoRutaAndFechaZonas(fecha, ruta.getCodigo());
		List<DispositivoTableroDto> listaDispositivos = vTableroRepository
				.findByCodigoRutaAndFechaDispositivos(fecha, ruta.getCodigo());

		List<DispositivoTableroDto> sList = listaDispositivos
				.stream()
				.sorted(Comparator
						.comparing(DispositivoTableroDto::getCodigoDispositivo)
						.thenComparing(
								Comparator
										.comparing(DispositivoTableroDto::getNumeroVuelta)))
				.collect(Collectors.toList());

		List<TableroViewGeoTabDto> listaConfig = new ArrayList<>();

		for (DispositivoTableroDto dispositivo : sList) {
			listaConfig.add(armarZonaDispositivo(listadoDatos, listaZonas,
					dispositivo));
		}

		tablero.setTablero(listaConfig);
		tablero.setZonas(listaZonas);
		tablero.setDispositivos(sList);

		return tablero;

	}

	private void procesarHoarasProgramadas(Integer rutaCodigo, Date fecha) {

		List<VTablero> listadoDatos = vTableroRepository
				.findByCodigoRutaAndFechaOrderByOrdenAsc(rutaCodigo, fecha);

		for (VTablero tablero : listadoDatos) {
			LocalizacionDispositivo localizacionDispositivo = localizacionDispositivoRepository
					.findOne(tablero.getCodigo());

			if (null != localizacionDispositivo.getZona().getInicioZona()
					&& localizacionDispositivo.getZona().getInicioZona()) {

				if (null != localizacionDispositivo.getHoraProgramada()) {

					DiferenciaHoraDto diferencia = FechasUtils.diferenciaHoras(
							localizacionDispositivo.getHoraSalida(),
							localizacionDispositivo.getHoraProgramada());

					localizacionDispositivo.setDiferenciaTiempo(diferencia
							.getHoraDiferencia());
					localizacionDispositivo.setCumpleTiempo(diferencia
							.getOperacion());

					localizacionDispositivoRepository
							.save(localizacionDispositivo);

				}

			}

			if (null != tablero.getCodigoEnlace()) {

				LocalizacionDispositivo localizacionDispositivoEnlace = localizacionDispositivoRepository
						.findOne(tablero.getCodigoEnlace());

				if (null != localizacionDispositivoEnlace.getHoraProgramada()) {
					if (null == tablero.getHoraProgramada()) {
						Zona zonaFocalizada = zonaRepository.findOne(tablero
								.getCodigoZona());

						Time horaProgramada = FechasUtils.aumentarMinutos(
								localizacionDispositivoEnlace
										.getHoraProgramada(), zonaFocalizada
										.getTiempo());

						DiferenciaHoraDto diferencia = FechasUtils
								.diferenciaHoras(
										localizacionDispositivo.getHoraSalida(),
										horaProgramada);
						localizacionDispositivo.setDiferenciaTiempo(diferencia
								.getHoraDiferencia());
						localizacionDispositivo
								.setHoraProgramada(horaProgramada);
						localizacionDispositivo.setCumpleTiempo(diferencia
								.getOperacion());
						localizacionDispositivoRepository
								.save(localizacionDispositivo);

					}
				}
			}

		}

	}

	public void registrarHoraProgramada(VTablero vLocalizacionDipositivo) {

		LocalizacionDispositivo localizacionDispositivo = localizacionDispositivoRepository
				.findOne(vLocalizacionDipositivo.getCodigo());
		localizacionDispositivo.setHoraProgramada(vLocalizacionDipositivo
				.getHoraProgramada());
		localizacionDispositivoRepository.save(localizacionDispositivo);

		procesarHoarasProgramadas(vLocalizacionDipositivo.getCodigoRuta(),
				vLocalizacionDipositivo.getFecha());

	}

	private TableroViewGeoTabDto armarZonaDispositivo(List<VTablero> datos,
			List<ZonaTableroDto> zonas, DispositivoTableroDto dispositivo) {

		TableroViewGeoTabDto tabla = new TableroViewGeoTabDto();

		tabla.setDispositivoTableroDto(dispositivo);
		List<VTablero> lista = new ArrayList<>();

		for (ZonaTableroDto zona : zonas) {
			lista.add(buscarZonaDispositivo(datos, zona, dispositivo));
		}

		tabla.setListaHoras(lista);

		return tabla;

	}

	private VTablero buscarZonaDispositivo(List<VTablero> datos,
			ZonaTableroDto zona, DispositivoTableroDto dispositivo) {

		boolean val = false;
		VTablero tab = null;

		for (VTablero tablero : datos) {
			if (tablero.getCodigoDispositivo().equals(
					dispositivo.getCodigoDispositivo())
					&& tablero.getCodigoZona().equals(zona.getCodigoZona())
					&& tablero.getNumeroVuelta().equals(
							dispositivo.getNumeroVuelta())

			) {
				val = true;
				tab = tablero;

				break;
			}
		}

		if (!val) {
			tab = new VTablero();
		}

		return tab;
	}

	public FechaDispositivo devolverFechaProceso() throws Exception {
		FechaDispositivo fecha = null;
		FechaDispositivo fechaDispositivo = fechaDispositivoRepository
				.findByNemonico("FECDI");

		if (null == fechaDispositivo) {
			fecha = crearFechaRegistroInicial();
		} else {

			if ("GEN".equals(fechaDispositivo.getEstado())) {
				throw new Exception("Otro proceso esta sincronizando...");
			} else {
				if ("PRO".equals(fechaDispositivo.getEstado())) {
					fecha = establecerFecha(fechaDispositivo);
				} else {
					throw new Exception("Accion no controlada...");
				}
			}

		}

		System.err.println(fecha);
		return fecha;
	}

	private FechaDispositivo establecerFecha(FechaDispositivo fechaProcesada) {

		String fechaInicial = fechaProcesada.getFechaFinal();
		TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'");
		df.setTimeZone(tz);

		String fechaFinalUTC = df.format(new Date());

		fechaProcesada.setEstado("GEN");
		fechaProcesada.setFechaInicio(fechaInicial);
		fechaProcesada.setFechaFinal(fechaFinalUTC);

		fechaDispositivoRepository.save(fechaProcesada);

		return fechaProcesada;

	}

	private FechaDispositivo crearFechaRegistroInicial() {

		FechaDispositivo fechaDispositivo = new FechaDispositivo();
		fechaDispositivo.setEstado("GEN");
		fechaDispositivo.setNemonico("FECDI");

		TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'");
		df.setTimeZone(tz);

		String fechaFinalUTC = df.format(new Date());

		Calendar fechaInicio = Calendar.getInstance();
		fechaInicio.set(Calendar.HOUR_OF_DAY, 0);
		fechaInicio.set(Calendar.MINUTE, 0);
		fechaInicio.set(Calendar.SECOND, 0);
		fechaInicio.set(Calendar.MILLISECOND, 0);

		String fechaInicioUTC = df.format(fechaInicio.getTime());

		fechaDispositivo.setFechaInicio(fechaInicioUTC);
		fechaDispositivo.setFechaFinal(fechaFinalUTC);

		fechaDispositivoRepository.save(fechaDispositivo);

		return fechaDispositivo;
	}

	public void procesarLocalizaciones(
			ProcesaDatosGeotabDto procesaDatosGeotabDto)
			throws InterruptedException {
		cambiarFechaEstadoGenerado(procesaDatosGeotabDto.getFechaDispositivo());

		Integer proceso = localizacionRepository.traerNumeroProceso();

		for (LocalizazionesGeotabDto localizacion : procesaDatosGeotabDto
				.getListaDatos()) {
			List<Dispositivo> listaDispositivo = dispositivoRepository
					.findByCodigoDispositivo(localizacion.getDevice().getId());
			if (null != listaDispositivo) {
				try {
					if (!listaDispositivo.isEmpty()) {
						Dispositivo dispositivo = listaDispositivo.get(0);
						Localizacion localizacionModel = new Localizacion();
						localizacionModel.setDispositivo(dispositivo);
						localizacionModel.setEstado("GEN");
						localizacionModel.setFechaHora(localizacion
								.getDateTime());
						localizacionModel.setLatitude(localizacion
								.getLatitude());
						localizacionModel.setLongitud(localizacion
								.getLongitude());
						localizacionModel.setProceso(proceso);
						localizacionRepository.save(localizacionModel);
						procesarPasoZona(localizacionModel);
					}

				} catch (Exception e) {
					System.err.println(e.getMessage());
				}

			}

		}

		procesarPasoDispositivos(proceso);
		liberarZonasGeneradas(proceso);
		System.out.println("Actualiza fechas de proceso");
		procesaDatosGeotabDto.getFechaDispositivo().setEstado("PRO");
		fechaDispositivoRepository.save(procesaDatosGeotabDto
				.getFechaDispositivo());
	}

	private void procesarPasoDispositivos(Integer proceso) {
		List<VLocalizacionDispositivo> listaVDispositivos = vlocalizacionRepository
				.findByProceso(proceso);

		System.out.println("Entra procesar dispositivos");

		for (VLocalizacionDispositivo vLocalizacion : listaVDispositivos) {

			try {

				Dispositivo dispositivo = dispositivoRepository
						.findOne(vLocalizacion.getCodigoDispositivo());
				Zona zona = zonaRepository.findOne(vLocalizacion
						.getCodigoZona());
				List<LocalizacionDispositivo> listaDispositivosProceso = null;
				System.out.println(zona.getNombre());
				listaDispositivosProceso = localizacionDispositivoRepository
						.findByDispositivoAndZonaAndFechaAndNumeroVuelta(
								dispositivo, zona, vLocalizacion.getFecha(),
								dispositivo.getNumeroVuelta());

				LocalizacionDispositivo localizacionDispositivo = null;

				if (null == listaDispositivosProceso
						|| listaDispositivosProceso.isEmpty()) {

					localizacionDispositivo = new LocalizacionDispositivo();

					if (zona.getInicioZona()) {
						dispositivo.setNumeroVuelta(dispositivo
								.getNumeroVuelta() + 1);
						dispositivoRepository.save(dispositivo);
					}

				} else {

					if (zona.getZonaRetorno()) {
						System.out
								.println("Actualiza proceso de marca de zona");
						System.out.println(listaDispositivosProceso.size());
						if (listaDispositivosProceso.size() > 0) {
							localizacionDispositivo = listaDispositivosProceso
									.get(0);
							localizacionDispositivo.setHoraProgramada(null);
							localizacionDispositivo.setDiferenciaTiempo(null);
						}

					}

				}

				if (null != localizacionDispositivo) {
					localizacionDispositivo
							.setDispositivo(dispositivoRepository
									.findOne(vLocalizacion
											.getCodigoDispositivo()));
					localizacionDispositivo.setFecha(vLocalizacion.getFecha());
					localizacionDispositivo.setHoraEntrada(vLocalizacion
							.getHoraEntrada());
					localizacionDispositivo.setHoraSalida(vLocalizacion
							.getHoraSalida());
					localizacionDispositivo.setProceso(proceso);
					
					localizacionDispositivo.setZona(zonaRepository
							.findOne(vLocalizacion.getCodigoZona()));
					localizacionDispositivo.setNumeroVuelta(dispositivo
							.getNumeroVuelta());
					localizacionDispositivo.setTiempo(zona.getTiempo());

					LocalizacionDispositivo posicionAnterior;
					posicionAnterior = buscarZonaAnterior(dispositivo,
							localizacionDispositivo.getNumeroVuelta(), zona);

					if (null != posicionAnterior) {
						localizacionDispositivo
								.setCodigoEnlace(posicionAnterior.getCodigo());
					}

					localizacionDispositivoRepository
							.save(localizacionDispositivo);

				}

			} catch (Exception e) {
				System.out.println("Error al procesar dispositivos");
				System.err.println(e.getStackTrace());
			}

		}

	}

	private LocalizacionDispositivo buscarZonaAnterior(Dispositivo dispositivo,
			Integer numeroVuelta, Zona zona) {
		LocalizacionDispositivo localizacionDispositivo = null;

		if (null != zona.getZonaEnlace()) {
			System.out.println(zona.getNombre());
			Zona zonaAnterior = zonaRepository.findOne(zona.getZonaEnlace()
					.getCodigo());
			System.out.println(zonaAnterior.getNombre());
			System.out.println(dispositivo.getCodigoDispositivo());
			System.out.println(numeroVuelta);
			List<LocalizacionDispositivo> localizaciones = localizacionDispositivoRepository
					.findByDispositivoAndZonaAndNumeroVuelta(dispositivo,
							zonaAnterior, numeroVuelta);

			if (null != localizaciones && localizaciones.isEmpty() == false) {
				System.out.println(localizaciones.size());
				localizacionDispositivo = localizaciones.get(0);
			}
		}

		return localizacionDispositivo;

	}

	public void actualizarZona(Zona zona) {

		zonaRepository.save(zona);
	}

	public Zona editarZona(Integer id) {
		Zona zona = zonaRepository.findOne(id);

		return zona;
	}

	private void liberarZonasGeneradas(Integer proceso) {
		System.err.println("Actualiza proceso de zonas");
		try {
			System.err.println(proceso);
			Integer estadoProceso = localizacionZonaRepository
					.procesarLocalizacionZona(proceso);
			System.out.println("Proceso finalizado");
			System.out.println(estadoProceso);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

	private void procesarPasoZona(Localizacion localizacion) {
		List<Zona> listaZonas = zonaRepository
				.findByRutaOrderByOrdenAsc(localizacion.getDispositivo()
						.getRuta());

		for (Zona zona : listaZonas) {
			List<Punto> listaPuntos = puntoRepository.findByZona(zona);

			boolean buscaParadaZona = PoligonoUtils.buscarZonaPoligono(
					listaPuntos, localizacion);

			if (buscaParadaZona) {
				registraDispositivoZona(localizacion, zona);
				break;
			}

		}

	}

	private void registraDispositivoZona(Localizacion localizacion, Zona zona) {

		try {
			LocalizacionZona localizacionZona = new LocalizacionZona();
			localizacionZona.setDispositivo(localizacion.getDispositivo());
			localizacionZona.setEstado("GEN");
			localizacionZona.setZona(zona);
			localizacionZona
					.setFecha(FechasUtils
							.convertirStringTimeZoneToDate(localizacion
									.getFechaHora()));
			localizacionZona.setFechaUtc(localizacion.getFechaHora());
			Time hora = FechasUtils.convertirStringTimeZoneToTime(localizacion
					.getFechaHora());

			Date dateHora = new Date(hora.getTime());

			Calendar calendarHora = Calendar.getInstance();
			calendarHora.setTime(dateHora);
			calendarHora.add(Calendar.HOUR, -5);

			Time horaProcesada = new Time(calendarHora.getTime().getTime());

			localizacionZona.setHora(horaProcesada);
			localizacionZona.setProceso(localizacion.getProceso());
			localizacionZonaRepository.save(localizacionZona);
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

	private TipoHorario devolverTipoHora(Time hora) {

		List<TipoHorario> tipoHorarios = tipoHorarioRepository.findAll();
		TipoHorario tipoHorario = null;

		for (TipoHorario th : tipoHorarios) {
			if (hora.getTime() >= th.getHoraInicio().getTime()
					&& hora.getTime() <= th.getHoraFin().getTime()) {
				tipoHorario = th;
				break;
			}

		}

		return tipoHorario;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private void cambiarFechaEstadoGenerado(FechaDispositivo fechaDispositivo) {
		fechaDispositivo.setEstado("GEN");
		fechaDispositivoRepository.save(fechaDispositivo);
	}

	public void sincronziarGrupos(List<GrupoGeotabDto> grupos) {
		StringBuilder hijos;
		GeotabGrupo geotabGrupo;

		geoTabGrupoRepository.deleteAll();

		for (GrupoGeotabDto grupo : grupos) {
			geotabGrupo = new GeotabGrupo();
			hijos = new StringBuilder();
			for (GrupoChildrenGeoTabDto grupoHijo : grupo.getChildren()) {
				hijos.append(grupoHijo.getId()).append(",");
			}
			geotabGrupo.setIdentificador(grupo.getId());
			geotabGrupo.setNombre(grupo.getName());
			geotabGrupo.setHijos(hijos.toString());
			geoTabGrupoRepository.save(geotabGrupo);
		}

		establecerNiveles();
		sincronizarCooperativas();
	}

	public void sincronziarDispositivos(List<DispositivoGeotabDto> dispositivos) {

		Dispositivo geotabDispositivo;

		for (DispositivoGeotabDto dispositivo : dispositivos) {

			String indentificadorRuta = null;
			for (GrupoDispositivoDto padre : dispositivo.getGroups()) {
				indentificadorRuta = padre.getId();

				break;
			}

			if (null != indentificadorRuta) {

				System.out.println(indentificadorRuta);
				Ruta ruta = rutaRepository
						.findByIdentificador(indentificadorRuta);

				if (null != ruta) {
					System.out.println(ruta.getNombre());
					geotabDispositivo = dispositivoRepository.findDispositivo(
							ruta, dispositivo.getId());

					if (null != geotabDispositivo) {
						geotabDispositivo.setPlaca(dispositivo
								.getLicensePlate());
						geotabDispositivo.setHabilitacion(dispositivo
								.getVehicleIdentificationNumber());
						geotabDispositivo.setSerie(dispositivo
								.getSerialNumber());
						geotabDispositivo.setNombre(dispositivo.getName());
					} else {
						geotabDispositivo = new Dispositivo();
						geotabDispositivo.setPlaca(dispositivo
								.getLicensePlate());
						geotabDispositivo.setHabilitacion(dispositivo
								.getVehicleIdentificationNumber());
						geotabDispositivo.setCodigoDispositivo(dispositivo
								.getId());
						geotabDispositivo.setSerie(dispositivo
								.getSerialNumber());
						geotabDispositivo.setNombre(dispositivo.getName());
						geotabDispositivo.setRuta(ruta);
						geotabDispositivo.setNumeroVuelta(0);
					}

					dispositivoRepository.save(geotabDispositivo);
				} else {
					System.err.println("Es necesario sinccronizar las rutas + "
							+ indentificadorRuta);
				}

			}

		}
	}

	public void sincronizarZonas(SincronizarZonasDto sincronizarZonasDto) {

		if (null != sincronizarZonasDto.getTipoZonaGeotabDto()) {
			for (TipoZonaGeotabDto tipoZonaGeotabDto : sincronizarZonasDto
					.getTipoZonaGeotabDto()) {

				TipoZona tipoZona = tipoZonaRepository
						.findByIdentificador(tipoZonaGeotabDto.getId());
				if (null == tipoZona) {
					tipoZona = new TipoZona();
					tipoZona.setIdentificador(tipoZonaGeotabDto.getId());
					tipoZona.setNombre(tipoZonaGeotabDto.getName());

				} else {
					tipoZona.setNombre(tipoZonaGeotabDto.getName());

				}

				tipoZonaRepository.save(tipoZona);

			}
		}

		if (null != sincronizarZonasDto.getZonaGeotabDtos()) {
			sincronizarZonas(sincronizarZonasDto.getZonaGeotabDtos());

		}
	}

	private void sincronizarZonas(List<ZonaGeotabDto> zonas) {
		for (ZonaGeotabDto zonaGeotabDto : zonas) {

			String identificadorRuta = null;
			for (ZonaRutaGeotabDto zonaRutaGeotabDto : zonaGeotabDto
					.getGroups()) {
				identificadorRuta = zonaRutaGeotabDto.getId();
			}
			if (null != identificadorRuta) {
				Ruta ruta = rutaRepository
						.findByIdentificador(identificadorRuta);

				if (null != ruta) {

					validarZona(ruta, zonaGeotabDto);
				} else {
					System.err.println(" Ruta no encontrada "
							+ identificadorRuta + " zona: "
							+ zonaGeotabDto.getName());
				}
			}

		}

	}

	private void validarZona(Ruta ruta, ZonaGeotabDto zonaGeotabDto) {

		String identificarTipoZona = null;

		for (ZonaTypeGeotabDto tipoZonaGeotabDto : zonaGeotabDto.getZoneTypes()) {
			identificarTipoZona = tipoZonaGeotabDto.getId();
		}

		if (null != identificarTipoZona) {
			TipoZona tipoZona = tipoZonaRepository
					.findByIdentificador(identificarTipoZona);

			if (null != tipoZona) {
				registrarZona(ruta, zonaGeotabDto, tipoZona);
			}
		}

	}

	private void registrarZona(Ruta ruta, ZonaGeotabDto zonaGeotabDto,
			TipoZona tipoZona) {
		Zona zona = zonaRepository.findByIdentificador(zonaGeotabDto.getId());

		if (null != zona) {
			zona.setNombre(zonaGeotabDto.getName());
			zona.setRuta(ruta);
			zona.setOrden(zonaGeotabDto.getComment());
			zona.setTipoZona(tipoZona);
			if (null == zona.getZonaRetorno()) {
				zona.setZonaRetorno(Boolean.FALSE);
			}

		} else {
			zona = new Zona();
			zona.setNombre(zonaGeotabDto.getName());
			zona.setRuta(ruta);
			zona.setIdentificador(zonaGeotabDto.getId());
			zona.setOrden(zonaGeotabDto.getComment());
			zona.setTipoZona(tipoZona);
			zona.setTiempo(0);
			zona.setInicioZona(Boolean.FALSE);
			zona.setValida(Boolean.TRUE);
			zona.setZonaRetorno(Boolean.FALSE);

		}

		zonaRepository.save(zona);
		registrarPuntosZona(zona, zonaGeotabDto);
	}

	private void registrarPuntosZona(Zona zona, ZonaGeotabDto zonaGeotabDto) {

		List<Punto> listaPuntos = puntoRepository.findByZona(zona);

		for (Punto punto : listaPuntos) {
			puntoRepository.delete(punto);
		}

		for (PuntoZonaGeotabDto puntoDto : zonaGeotabDto.getPoints()) {
			Punto punto = new Punto();
			punto.setPosx(puntoDto.getX());
			punto.setPosy(puntoDto.getY());
			punto.setZona(zona);
			puntoRepository.save(punto);
		}
	}

	public List<Dispositivo> devolverDispositivos(Ruta ruta) {

		return dispositivoRepository.findByRuta(ruta);
	}

	public List<Ruta> devolverRutas(Integer codigoEmpresa) {
		Empresa empresa = empresaRepository.findOne(codigoEmpresa);

		return rutaRepository.findByEmpresa(empresa);
	}

	public List<Empresa> traerEmpresas() {
		return empresaRepository.findAll();
	}

	public List<Zona> traerZonas(Integer id) {
		Ruta ruta = rutaRepository.findOne(id);
		List<Zona> zonas = zonaRepository.findByRutaOrderByOrdenAsc(ruta);

		return zonas;
	}

	private void establecerNiveles() {
		GeotabGrupo geotabGrupo = geoTabGrupoRepository
				.buscarPorIdentificador(SotApp.GeoTab.NIVEL_INICIAL_GRUPO);
		Integer nivel = 1;
		if (null != geotabGrupo) {
			geotabGrupo.setNivel(nivel);
			geoTabGrupoRepository.save(geotabGrupo);
			establecerNivelesHijos(geotabGrupo, nivel);
		}
	}

	private void establecerNivelesHijos(GeotabGrupo geotabGrupo, Integer nivel) {
		Integer siguienteNivel = nivel + 1;
		String[] hijos = geotabGrupo.getHijos().split(",");

		for (String hijo : hijos) {
			List<GeotabGrupo> gruposHijos = geoTabGrupoRepository
					.buscarGruposHijos(hijo);
			for (GeotabGrupo geotabGrupoHijo : gruposHijos) {
				geotabGrupoHijo.setNivel(siguienteNivel);
				geoTabGrupoRepository.save(geotabGrupoHijo);
				establecerNivelesHijos(geotabGrupoHijo, siguienteNivel);
			}
		}

	}

	private void sincronizarCooperativas() {

		List<GeotabGrupo> listaCooperativas = geoTabGrupoRepository
				.findByNivel(SotApp.GeoTab.NIVEL_COOPERATIVA);

		for (GeotabGrupo cooperativa : listaCooperativas) {
			Empresa empresa = empresaRepository
					.findByCodigoCooperativa(cooperativa.getIdentificador());

			if (null == empresa) {
				empresa = new Empresa();
				empresa.setCodigoCooperativa(cooperativa.getIdentificador());
				empresa.setEstado(Boolean.TRUE);
				empresa.setNombre(cooperativa.getNombre());

			} else {
				empresa.setNombre(cooperativa.getNombre());
			}

			empresaRepository.save(empresa);
			sincronizarRutas(cooperativa, empresa);
		}
	}

	private void sincronizarRutas(GeotabGrupo cooperativa, Empresa empresa) {

		String[] hijos = cooperativa.getHijos().split(",");

		for (String identificador : hijos) {
			GeotabGrupo rutaGrupo = geoTabGrupoRepository
					.buscarPorIdentificador(identificador);
			if (null != rutaGrupo) {
				Ruta ruta = rutaRepository.findByEmpresaAndIdentificador(
						empresa, rutaGrupo.getIdentificador());
				if (ruta == null) {
					ruta = new Ruta();
					ruta.setEmpresa(empresa);
					ruta.setNombre(rutaGrupo.getNombre());
					ruta.setIdentificador(rutaGrupo.getIdentificador());
				} else {
					ruta.setNombre(rutaGrupo.getNombre());
				}

				rutaRepository.save(ruta);

			}

		}

	}
}
