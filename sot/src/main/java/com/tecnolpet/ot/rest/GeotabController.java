package com.tecnolpet.ot.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.dto.RespuestaDto;
import com.tecnolpet.ot.geotab.dto.DispositivoGeotabDto;
import com.tecnolpet.ot.geotab.dto.GrupoGeotabDto;
import com.tecnolpet.ot.geotab.dto.ProcesaDatosGeotabDto;
import com.tecnolpet.ot.geotab.dto.SincronizarZonasDto;
import com.tecnolpet.ot.geotab.service.GeoTabService;
import com.tecnolpet.ot.jview.ViewOT;
import com.tecnolpet.ot.model.Dispositivo;
import com.tecnolpet.ot.model.Ruta;
import com.tecnolpet.ot.seguridad.UsuarioAuthenticate;

@RestController
@RequestMapping("api/geotab")
public class GeotabController {

	private final GeoTabService geoTabService;

	@Autowired
	public GeotabController(GeoTabService geoTabService) {
		this.geoTabService = geoTabService;
	}

	@RequestMapping(value = "/sincronizarGrupos", method = RequestMethod.POST)
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

	@RequestMapping(value = "/sincronizarDispositivos", method = RequestMethod.POST)
	public RespuestaDto sincronizarDispositivos(@RequestBody List<DispositivoGeotabDto> dispositivos) {
		RespuestaDto respuestaDto = new RespuestaDto();

		try {
			respuestaDto.setEstado(Boolean.TRUE);

			geoTabService.sincronziarDispositivos(dispositivos);
		} catch (Exception ex) {
			respuestaDto.setEstado(Boolean.FALSE);
			respuestaDto.setMensaje(ex.getMessage());
		}

		return respuestaDto;
	}

	@RequestMapping(value = "/sincronizarZonas", method = RequestMethod.POST)
	public RespuestaDto sincronizarZonas(@RequestBody SincronizarZonasDto sincronizarZonasDto) {
		RespuestaDto respuestaDto = new RespuestaDto();

		try {
			respuestaDto.setEstado(Boolean.TRUE);

			geoTabService.sincronizarZonas(sincronizarZonasDto);
		} catch (Exception ex) {
			respuestaDto.setEstado(Boolean.FALSE);
			respuestaDto.setMensaje(ex.getMessage());
		}

		return respuestaDto;
	}

	@RequestMapping(value = "/fechasDispositivos", method = RequestMethod.POST)
	public RespuestaDto fechasDispositivos(

	) {
		RespuestaDto respuestaDto = new RespuestaDto();

		try {
			respuestaDto.setEstado(Boolean.TRUE);
			respuestaDto.setObjeto(geoTabService.devolverFechaProceso());
		} catch (Exception ex) {
			respuestaDto.setEstado(Boolean.FALSE);
			respuestaDto.setMensaje(ex.getMessage());
		}

		return respuestaDto;
	}

	@RequestMapping(value = "/procesarDatos", method = RequestMethod.POST)
	public RespuestaDto procesarLocalizaciones(@RequestBody ProcesaDatosGeotabDto procesaDatosGeotabDto  ) {
		RespuestaDto respuestaDto = new RespuestaDto();

		try {
			respuestaDto.setEstado(Boolean.TRUE);
			geoTabService.procesarLocalizaciones(procesaDatosGeotabDto);
			//respuestaDto.setObjeto(geoTabService.devolverFechaProceso());
		} catch (Exception ex) {
			respuestaDto.setEstado(Boolean.FALSE);
			respuestaDto.setMensaje(ex.getMessage());
		}

		return respuestaDto;
	}

	@JsonView(ViewOT.PublicView.class)
	@RequestMapping(value = "/listar/dispositivos", method = RequestMethod.GET)
	public List<Dispositivo> traerInstrumentos(@AuthenticationPrincipal UsuarioAuthenticate usuario) {
		return geoTabService.devolverDispositivos(usuario.getRuta());
	}

	@JsonView(ViewOT.PublicView.class)
	@RequestMapping(value = "/rutas", method = RequestMethod.GET)
	public List<Ruta> traerRutas(@RequestParam Integer codigoEmpresa) {
		return geoTabService.devolverRutas(codigoEmpresa);
	}
}
