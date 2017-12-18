package com.tecnolpet.ot.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tecnolpet.ot.constant.SotApp;
import com.tecnolpet.ot.dto.FielWebDto;
import com.tecnolpet.ot.model.Acceso;
import com.tecnolpet.ot.model.Catalogo;
import com.tecnolpet.ot.model.DetallesTareasSuscripciones;
import com.tecnolpet.ot.model.Suscripcion;
import com.tecnolpet.ot.model.TareasSuscripcion;
import com.tecnolpet.ot.repository.AccesoRepository;
import com.tecnolpet.ot.repository.CatalogoRepository;
import com.tecnolpet.ot.repository.DetallesTareasSuscripcionRepository;
import com.tecnolpet.ot.repository.SuscripcionRepository;
import com.tecnolpet.ot.repository.TareasSuscripcionRepository;

@EnableScheduling
@Service
public class SchedulerService {

	
	@Autowired
	private SuscripcionRepository suscripcionRepository;

	@Autowired
	private AccesoRepository accesoRepository;

	@Autowired
	private CatalogoRepository catalogoRepository;
	
	@Autowired
	private TareasSuscripcionRepository tareasSuscripcionRepository;
	
	@Autowired
	private DetallesTareasSuscripcionRepository detallesTareasSuscripcionRepository;
	
	@Autowired
	private Environment env;	
		
	private Boolean verificarAccesosActivos(List<Acceso> accesos) {
		Boolean res = Boolean.FALSE;

		for (Acceso acceso : accesos) {
			if (acceso.getCatalogoEstado().getSigla()
					.equals(SotApp.EstadosAcceso.ACTIVADO_TEMPORAL)
					&& acceso.getActivo() == Boolean.FALSE) {
				res = Boolean.TRUE;
			}
		}
		return res;
	}

	private Boolean verificarAccesosNoActivos(List<Acceso> accesos) {
		Boolean res = Boolean.FALSE;

		for (Acceso acceso : accesos) {
			if (acceso.getCatalogoEstado().getSigla()
					.equals(SotApp.EstadosAcceso.DESACTIVADO)) {
				res = Boolean.TRUE;
			}
		}
		return res;
	}
	
	
	//@Scheduled(cron = "0 0/1 * * * *")
	public void actualizarAccesos() throws Exception {

		Catalogo activadoTemporal = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosAcceso.ACTIVADO_TEMPORAL).get(0);

		List<Suscripcion> suscripciones = suscripcionRepository
				.findByAcceso(activadoTemporal);
		for (Suscripcion suscripcion : suscripciones) {

			FielWebDto fielDto = null;

			List<Acceso> accesosSelectos = new ArrayList<Acceso>();

			List<Acceso> accesos = accesoRepository
					.findBySuscripcion(suscripcion);

			if (verificarAccesosActivos(accesos)) {
				fielDto = new FielWebDto();

				for (Acceso acceso : accesos) {
					if (acceso.getCatalogoEstado().getSigla()
							.equals(SotApp.EstadosAcceso.ACTIVADO_TEMPORAL)
							&& acceso.getActivo() == Boolean.FALSE) {
						if(acceso.getCantidadIps() != null && acceso.getCantidadIps() > 0){
							acceso.setRangoIp(1);
						}
						accesosSelectos.add(acceso);
					}

				}

				fielDto.setAccesos(accesosSelectos);
				fielDto.setCliente(suscripcion.getCliente());
				fielDto.setSuscripcion(suscripcion);
				
				

			} else {
				System.out.println("Nada que procesar...");
			}
		}
	}

	public void desactivarAccesos(Suscripcion suscripcion) throws Exception {
		FielWebDto fielDto = null;

		List<Acceso> accesos = accesoRepository.findBySuscripcion(suscripcion);

		if (verificarAccesosNoActivos(accesos)) {
			fielDto = new FielWebDto();

			fielDto.setAccesos(accesos);
			fielDto.setCliente(suscripcion.getCliente());
			fielDto.setSuscripcion(suscripcion);
			
		} else {
			System.out.println("Nada que procesar...");
		}
	}

	@Scheduled(cron = "0 0 6 * * ?")
	@Transactional
	public void verificarFechasVencimiento() throws Exception {
		Catalogo activa = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosSuscripciones.ACTIVA).get(0);
		Catalogo desactivada = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosSuscripciones.DESACTIVADA).get(0);
		Catalogo desactivado = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosAcceso.DESACTIVADO).get(0);
		Catalogo renovada = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosSuscripciones.RENOVADA).get(0);

		Catalogo activaDefinitiva = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosSuscripciones.ACTIVADEFINITIVA).get(0);

		List<Suscripcion> suscripciones = suscripcionRepository
				.findByCatalogoAndFechaVencimiento(activaDefinitiva, new Date());

		if (suscripciones.size() > 0) {
			for (Suscripcion suscripcion : suscripciones) {
				suscripcion.setCatalogo(desactivada);
				suscripcion.setFechaHoraDesactivacion(new Timestamp(Calendar
						.getInstance().getTime().getTime()));

				Suscripcion suscripcionRenovada = suscripcionRepository
						.findByCatalogoAndSuscripcionInicial(renovada,
								suscripcion.getId());

				if (suscripcionRenovada != null) {
					suscripcionRenovada.setCatalogo(activa);
					suscripcionRenovada.setFechaHoraActivacion(new Timestamp(
							Calendar.getInstance().getTime().getTime()));
				}

				if (suscripcion.getDetalleNotaPedido().getProducto()
						.isAplicaAccesos()) {
					List<Acceso> accesos = accesoRepository
							.findBySuscripcion(suscripcion);
					for (Acceso acceso : accesos)
						acceso.setCatalogoEstado(desactivado);
					accesoRepository.save(accesos);
				}
				desactivarAccesos(suscripcion);
			}
			suscripcionRepository.save(suscripciones);

			System.out.println("Procesado");
		} else {
			System.out.println("No hay suscripciones vencidas...");
		}
	}

	@Scheduled(cron = "0 0 7 * * ?")
	@Transactional
	public void reporteSuscripciones() {
		Catalogo cancelada = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosSuscripciones.CANCELADA).get(0);
		Catalogo renovada = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosSuscripciones.RENOVADA).get(0);
		Catalogo desactivada = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosSuscripciones.DESACTIVADA).get(0);
		
		TareasSuscripcion tareasSuscripcion = null;
		DetallesTareasSuscripciones detallesTareasSuscripciones = null;

		int renovadas = 0;
		int desactivadas = 0;

		Calendar fechaFutura = Calendar.getInstance();

		fechaFutura.add(Calendar.DAY_OF_MONTH, -1);
		
		List<Suscripcion> suscripciones = suscripcionRepository
				.findByFechaVencimientoAndCatalogo(fechaFutura.getTime(), cancelada);
		
		if(suscripciones != null && suscripciones.size() > 0){
			for (Suscripcion suscripcion : suscripciones) {
				int suscripcionInicial = suscripcion.getSuscripcionInicial();

				Suscripcion renovacion = suscripcionRepository
						.findByFechaVencimientoAndCatalogoAndSuscripcionInicial(
								fechaFutura.getTime(), 2, cancelada,
								suscripcionInicial);
				
				if (renovacion != null) {
					 renovadas += 1;
				} else {
					 desactivadas += 1;
				}
			}
			tareasSuscripcion = new TareasSuscripcion();
			tareasSuscripcion.setFechaProcesamiento(new Timestamp(Calendar.getInstance().getTime().getTime()));		
			tareasSuscripcion.setNroSuscripcionesRenovadas(renovadas);
			tareasSuscripcion.setNroSuscripcionesDesactivadas(desactivadas);
			tareasSuscripcion.setNroSuscripcionesProcesadas(renovadas + desactivadas);
			tareasSuscripcionRepository.save(tareasSuscripcion);
			
			for (Suscripcion suscripcion : suscripciones) {
				Suscripcion renovacion = suscripcionRepository
						.findByFechaVencimientoAndCatalogoAndSuscripcionInicial(
								fechaFutura.getTime(), 2, cancelada,
								suscripcion.getSuscripcionInicial());
				
				detallesTareasSuscripciones = new DetallesTareasSuscripciones();
				
				if (renovacion != null) {
					detallesTareasSuscripciones.setCatalogo(renovada);
				} else {
					detallesTareasSuscripciones.setCatalogo(desactivada);
				}
				detallesTareasSuscripciones.setSuscripcion(suscripcion);
				detallesTareasSuscripciones.setTareasSuscripcion(tareasSuscripcion);
				detallesTareasSuscripcionRepository.save(detallesTareasSuscripciones);
			}			
			System.out.println("Se actualizó...");
		}else{
			System.out.println("No se actualizó...");
		}
	}
}