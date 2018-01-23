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
import com.tecnolpet.ot.model.Empresa;
import com.tecnolpet.ot.model.Ruta;
import com.tecnolpet.ot.model.Zona;
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

	@RequestMapping(value = "/actualizarZona", method = RequestMethod.POST)
	public RespuestaDto actualizarZona(@RequestBody Zona zona

	) {
		RespuestaDto respuestaDto = new RespuestaDto();

		try {
			geoTabService.actualizarZona(zona);
			respuestaDto.setEstado(Boolean.TRUE);
			respuestaDto.setObjeto(zona);
		} catch (Exception ex) {
			respuestaDto.setEstado(Boolean.FALSE);
			respuestaDto.setMensaje(ex.getMessage());
		}

		return respuestaDto;
	}

	@RequestMapping(value = "/editarZona", method = RequestMethod.POST)
	public RespuestaDto editarZona(@RequestBody Integer idZona

	) {
		RespuestaDto respuestaDto = new RespuestaDto();

		try {
			respuestaDto.setEstado(Boolean.TRUE);
			respuestaDto.setObjeto(geoTabService.editarZona(idZona));
		} catch (Exception ex) {
			respuestaDto.setEstado(Boolean.FALSE);
			respuestaDto.setMensaje(ex.getMessage());
		}

		return respuestaDto;
	}

	@RequestMapping(value = "/procesarDatos", method = RequestMethod.POST)
	public RespuestaDto procesarLocalizaciones(@RequestBody ProcesaDatosGeotabDto procesaDatosGeotabDto) {
		RespuestaDto respuestaDto = new RespuestaDto();

		try {
			respuestaDto.setEstado(Boolean.TRUE);
			geoTabService.procesarLocalizaciones(procesaDatosGeotabDto);
			// respuestaDto.setObjeto(geoTabService.devolverFechaProceso());
		} catch (Exception ex) {
			respuestaDto.setEstado(Boolean.FALSE);
			respuestaDto.setMensaje(ex.getMessage());
		}

		return respuestaDto;
	}

	@RequestMapping(value = "/procesarTablero", method = RequestMethod.POST)
	public RespuestaDto procesarTablero(@AuthenticationPrincipal UsuarioAuthenticate usuario) {
		RespuestaDto respuestaDto = new RespuestaDto();

		try {
			respuestaDto.setEstado(Boolean.TRUE);
			respuestaDto.setObjeto(geoTabService.devolverTablero(usuario));

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
	@RequestMapping(value = "/listar/empresas", method = RequestMethod.GET)
	public List<Empresa> traerEmpresas(@AuthenticationPrincipal UsuarioAuthenticate usuario) {
		return geoTabService.traerEmpresas();
	}

	@JsonView(ViewOT.PublicView.class)
	@RequestMapping(value = "/rutas/{empresa}", method = RequestMethod.GET)
	public List<Ruta> traerRutas(@PathVariable("empresa") Integer codigoEmpresa) {
		return geoTabService.devolverRutas(codigoEmpresa);
	}

	@JsonView(ViewOT.PublicView.class)
	@RequestMapping(value = "/zonas/{ruta}", method = RequestMethod.GET)
	public List<Zona> traerZonas(@PathVariable("ruta") Integer codigoRuta) {
		return geoTabService.traerZonas(codigoRuta);
	}
}
