/**
 * 
 */
package com.tecnolpet.ot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tecnolpet.ot.constant.SotApp;
import com.tecnolpet.ot.model.Catalogo;
import com.tecnolpet.ot.model.Distribucion;
import com.tecnolpet.ot.model.FechasRenovacion;
import com.tecnolpet.ot.model.SubCategoria;
import com.tecnolpet.ot.model.Sucursal;
import com.tecnolpet.ot.model.Telerenovador;
import com.tecnolpet.ot.repository.AvisoDetalleRepository;
import com.tecnolpet.ot.repository.AvisoRepository;
import com.tecnolpet.ot.repository.CatalogoRepository;
import com.tecnolpet.ot.repository.DistribucionRepository;
import com.tecnolpet.ot.repository.FechasRenovacionRepository;
import com.tecnolpet.ot.repository.SubcategoriaRepository;
import com.tecnolpet.ot.repository.SucursalRepository;
import com.tecnolpet.ot.repository.TelerenovadorRepository;
import com.tecnolpet.ot.seguridad.UsuarioAuthenticate;

@Service
public class DistribucionService {

	@Autowired
	private AvisoRepository avisoRepository;

	@Autowired
	private AvisoDetalleRepository avisoDetalleRepository;

	@Autowired
	private FechasRenovacionRepository fechasRenovacionRepository;

	@Autowired
	private TelerenovadorRepository telerenovadorRepository;
	
	@Autowired
	private SucursalRepository sucursalRepository;
	
	@Autowired
	private SubcategoriaRepository subcategoriaRepository;
	
	@Autowired
	private CatalogoRepository catalogoRepository;
	
	@Autowired
	private DistribucionRepository distribucionRepository;
	
	

	public List<Distribucion> traerDistribucionPorFechaLinea(Integer codigoFechaRenovacion, Integer codigoSubcategoria,UsuarioAuthenticate usuario) {
		List<Distribucion> listaDistribuciones;
		
		Catalogo estado = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosDistribucion.REGISTRADA).get(0);
		
		FechasRenovacion fechaRenovacion = fechasRenovacionRepository.findOne(codigoFechaRenovacion);
		SubCategoria subcategoria=subcategoriaRepository.findOne(codigoSubcategoria);
		Sucursal sucursal=sucursalRepository.findOne(usuario.getSucursal());
		
		listaDistribuciones=distribucionRepository.findDistribucion(sucursal, fechaRenovacion, subcategoria, estado);
		
		
		return listaDistribuciones;
	}

	public List<Telerenovador> getTelerenovadores(UsuarioAuthenticate usuario) {
		Sucursal sucursal = sucursalRepository.findOne(usuario.getSucursal());
		
		List<Telerenovador> lista = telerenovadorRepository.findBySucursal(sucursal);
		
		return lista;
	}
	
	@Transactional
	public void asignarTelerenovador(List<Distribucion> detalleDistribucion){
		
		Catalogo estado = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosDistribucion.ASIGNADA).get(0);
		
		for (Distribucion distribucion:detalleDistribucion){
			distribucion.setEstado(estado);
			distribucionRepository.save(distribucion);
			
		}
	}
}
