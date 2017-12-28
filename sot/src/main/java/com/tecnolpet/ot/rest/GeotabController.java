package com.tecnolpet.ot.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tecnolpet.ot.dto.RespuestaDto;
import com.tecnolpet.ot.geotab.dto.GrupoGeotabDto;
import com.tecnolpet.ot.geotab.service.GeoTabService;

@RestController
@RequestMapping("api/geotab")
public class GeotabController {

	private final GeoTabService geoTabService;

	@Autowired
	public GeotabController(GeoTabService geoTabService) {
		this.geoTabService = geoTabService;
	}

	@RequestMapping(value = "/sincronizar", method = RequestMethod.POST)
	public RespuestaDto sincronizarGrupos(@RequestBody List<GrupoGeotabDto> grupos) {
		RespuestaDto respuestaDto = new RespuestaDto();

		try {

			respuestaDto.setEstado(Boolean.TRUE);
			geoTabService.sincronziarGrupos(grupos);
		
			
		} catch (Exception ex) {
			respuestaDto.setEstado(Boolean.FALSE);
			respuestaDto.setMensaje(ex.getMessage());
		}

		return respuestaDto;
	}
}
