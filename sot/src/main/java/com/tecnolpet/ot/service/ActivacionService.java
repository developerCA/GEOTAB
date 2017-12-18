package com.tecnolpet.ot.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecnolpet.ot.constant.SotApp;
import com.tecnolpet.ot.model.Catalogo;
import com.tecnolpet.ot.model.Cliente;
import com.tecnolpet.ot.model.Sucursal;
import com.tecnolpet.ot.model.Suscripcion;
import com.tecnolpet.ot.repository.CatalogoRepository;
import com.tecnolpet.ot.repository.ClienteRepository;
import com.tecnolpet.ot.repository.SucursalRepository;
import com.tecnolpet.ot.repository.SuscripcionRepository;
import com.tecnolpet.ot.seguridad.UsuarioAuthenticate;

@Service
public class ActivacionService {
	
	@Autowired
	private SuscripcionRepository suscripcionRepository;
	
	@Autowired
	private CatalogoRepository catalogoRepository;
	
	@Autowired
	private SucursalRepository SucursalRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<Suscripcion> getSuscripcionesActivadasTemporalmente(UsuarioAuthenticate usuario){
		Catalogo activadoTemporal = catalogoRepository.findCatalogoBySigla(SotApp.EstadosSuscripciones.ACTIVA).get(0);
		
		Sucursal sucursal = SucursalRepository.findOne(usuario.getSucursal());
		List<Suscripcion> suscripciones = suscripcionRepository.findByCatalogoAndSucursal(activadoTemporal, sucursal); 
		
		return suscripciones;
	}
	
	public List<Cliente> getClienteList(){
		Catalogo aprobadas = catalogoRepository.findCatalogoBySigla(SotApp.EstadosSuscripciones.APROBADA).get(0);
		
		List<Suscripcion> suscripciones = suscripcionRepository.findByCatalogo(aprobadas);
		List<Cliente> clientes = new ArrayList<Cliente>(); 
		
		for (Suscripcion suscripcion : suscripciones) {
			clientes.add(suscripcion.getCliente());
		}
		return clientes;
	}
	
	public void guardarSuscripcionActivada(Integer suscripcionId, UsuarioAuthenticate usuario){
		Catalogo activaDefinitiva = catalogoRepository.findCatalogoBySigla(SotApp.EstadosSuscripciones.ACTIVA).get(0);
		
		Suscripcion suscripcion = suscripcionRepository.findOne(suscripcionId);
		suscripcion.setFechaHoraActivacion(new Timestamp(Calendar.getInstance().getTime()
				.getTime()));
		suscripcion.setUsuarioActivacion(usuario.getId());
		suscripcion.setCatalogo(activaDefinitiva);
		
		suscripcionRepository.save(suscripcion);
	}
}
