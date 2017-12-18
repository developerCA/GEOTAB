package com.tecnolpet.ot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.tecnolpet.ot.constant.SotApp;
import com.tecnolpet.ot.dto.BodegaDto;
import com.tecnolpet.ot.model.Bodega;
import com.tecnolpet.ot.model.Catalogo;
import com.tecnolpet.ot.repository.BodegaRepository;
import com.tecnolpet.ot.repository.CatalogoRepository;

@Service
@PropertySources(value = { @PropertySource("classpath:/application.properties") })
public class BodegaService {

	@Autowired
	private BodegaRepository bodegaRepository;

	@Autowired
	private CatalogoRepository catalogoRepository;

	@Autowired
	private Environment env;

//	public List<Bodega> getBodegaList() {
//		return bodegaRepository.findAll();
//	}

	
	public List<Bodega> getBodegaList() {
		List<Bodega> bodega = bodegaRepository.findByEstado(catalogoRepository.findCatalogoBySigla("ACTIVO").get(0));
		return bodega;
	}
	
	public void guardarBodega(BodegaDto bodegaDto)
			throws Exception {
		Catalogo catalogo = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosGenerales.ACTIVO).get(0);
		String nombre = bodegaDto.getNombre();
		String codigokoinor = bodegaDto.getCodigoKoynor();
		Bodega bodega = new Bodega();
		bodega.setNombre(nombre);
		bodega.setCodigoKohinor(codigokoinor);
		bodega.setEstado(catalogo);	
		bodegaRepository.save(bodega);
	}
	public void actualizar(BodegaDto bodegaDto){
	
		Catalogo catalogo = catalogoRepository.findCatalogoBySigla(SotApp.EstadosGenerales.ACTIVO).get(0);
				Bodega bodega = bodegaRepository.findOne(bodegaDto.getId());
				bodega.setNombre(bodegaDto.getNombre());
				bodega.setCodigoKohinor(bodegaDto.getCodigoKoynor());
				bodega.setEstado(catalogo);
				bodegaRepository.save(bodega);
	}

	public void eliminar(BodegaDto bodegaDto) {

		Catalogo catalogo = catalogoRepository.findCatalogoBySigla(
		SotApp.EstadosGenerales.ELIMINADO).get(0);
		Bodega bodega = bodegaRepository.findOne(bodegaDto.getId());
		bodega.setEstado(catalogo);
		bodegaRepository.save(bodega);

	}

}
