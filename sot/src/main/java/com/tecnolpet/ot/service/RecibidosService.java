/**
 * 
 */
package com.tecnolpet.ot.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecnolpet.ot.dto.CodigoDto;
import com.tecnolpet.ot.model.Aviso;
import com.tecnolpet.ot.model.AvisoDetalle;
import com.tecnolpet.ot.model.Distribuidores;
import com.tecnolpet.ot.model.Suscripcion;
import com.tecnolpet.ot.repository.AvisoDetalleRepository;
import com.tecnolpet.ot.repository.AvisoRepository;
import com.tecnolpet.ot.repository.CatalogoRepository;
import com.tecnolpet.ot.repository.DistribuidorRepository;

/**
 * @author Armando Ariel Suárez Pons
 *
 */

@Service
public class RecibidosService {

	@Autowired
	private CatalogoRepository catalogoRepository;
	
	@Autowired
	private DistribuidorRepository distribuidorRepository;
	
	@Autowired
	private AvisoDetalleRepository avisoDetalleRepository;
	
	@Autowired
	private AvisoRepository avisoRepository;
	
	public List<Distribuidores> getDistribuidores(){
		return distribuidorRepository.findAll();
	}
	
	public List<Suscripcion> procesarCodigoBarras(String codigo){
		List<Suscripcion> res = Collections.emptyList();
		List<Suscripcion> suscripciones = new ArrayList<Suscripcion>();
		
		String id = codigo.split("\\.")[0];
		Aviso aviso = avisoRepository.findById(Integer.valueOf(id)).get(0);

		List<AvisoDetalle> avisoDetalles = aviso.getAvisosDetalle();
		
		if(avisoDetalles.size() > 0){		
			for (AvisoDetalle avisoDetalle : avisoDetalles) {
				suscripciones.add(avisoDetalle.getSuscripcion());			
			}
			res = suscripciones;
		}
		return res; 
	}
	
	public CodigoDto analizarCodigo(String codigo){
		
		int code = Integer.valueOf(codigo.split("\\.")[0]);
		String segmento2 = codigo.split("\\.")[1];
		
		CodigoDto codigoDto = new CodigoDto();
		
		codigoDto.setCodigo(code);
		codigoDto.setSegmento2(segmento2);
		
		return codigoDto;
	}
	
}
