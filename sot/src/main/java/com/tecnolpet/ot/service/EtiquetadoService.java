/**
 * 
 */
package com.tecnolpet.ot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.tecnolpet.ot.dto.FiltrosDto;
import com.tecnolpet.ot.dto.RespuestaReporteDto;
import com.tecnolpet.ot.model.SubCategoria;
import com.tecnolpet.ot.repository.SubcategoriaRepository;
import com.tecnolpet.ot.seguridad.UsuarioAuthenticate;

/**
 * @author administrador
 *
 */
@Configuration
@Service
@PropertySources(value = { @PropertySource("classpath:/application.properties") })
public class EtiquetadoService {
	
	@Autowired
	private Environment env;	
	
	@Autowired
	private SubcategoriaRepository subcategoriaRepository;
	
	public RespuestaReporteDto getUrlConParams(FiltrosDto filtrosDto, UsuarioAuthenticate usuario){
		String urlBirt = env.getProperty("birt.url");
		StringBuffer sb = new StringBuffer(urlBirt);	
		RespuestaReporteDto respuesta = new RespuestaReporteDto();
		
		if(filtrosDto.getComercial() != null){
			sb.append("new_report.rptdesign&CodigoFechas=".concat(String.valueOf(filtrosDto.getRango())).concat("&Comercial=").concat(filtrosDto.getComercial()).concat("&Sucursal=").concat(String.valueOf(usuario.getSucursal()).concat("&Linea=").concat(String.valueOf(filtrosDto.getLinea()))));
		}else{
			sb.append("new_report.rptdesign&CodigoFechas=".concat(String.valueOf(filtrosDto.getRango()).concat("&Sucursal=").concat(String.valueOf(usuario.getSucursal())).concat("&Linea=").concat(String.valueOf(filtrosDto.getLinea()))));
		}
		respuesta.setUrl(sb.toString());
		return respuesta;
	}
	
	public List<SubCategoria> getSubcategoriaList(){
		return subcategoriaRepository.findAll();
	}
	
}
