package com.tecnolpet.ot.rest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.tecnolpet.ot.geotab.dto.ReporteVueltaDto;
import com.tecnolpet.ot.geotab.dto.SincronizarZonasDto;
import com.tecnolpet.ot.geotab.service.GeoTabService;
import com.tecnolpet.ot.jview.ViewOT;
import com.tecnolpet.ot.model.Dispositivo;
import com.tecnolpet.ot.model.Empresa;
import com.tecnolpet.ot.model.Ruta;
import com.tecnolpet.ot.model.VTablero;
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
	public RespuestaDto sincronizarGrupos(
			@RequestBody List<GrupoGeotabDto> grupos) {
		RespuestaDto respuestaDto = new RespuestaDto();

		try {
			respuestaDto.setEstado(Boolean.TRUE);
			geoTabService.sincronizarGrupos(grupos);
		} catch (Exception ex) {
			respuestaDto.setEstado(Boolean.FALSE);
			respuestaDto.setMensaje(ex.getMessage());
		}

		return respuestaDto;
	}

	@RequestMapping(value = "/reporte", method = RequestMethod.POST)
	public RespuestaDto reporte(@RequestBody ReporteVueltaDto reporteVueltaDto) {
		RespuestaDto respuestaDto = new RespuestaDto();

		try {
			respuestaDto.setEstado(Boolean.TRUE);
			geoTabService.generarReporte(reporteVueltaDto);
			respuestaDto.setObjeto(reporteVueltaDto);
		} catch (Exception ex) {
			respuestaDto.setEstado(Boolean.FALSE);
			respuestaDto.setMensaje(ex.getMessage());
		}

		return respuestaDto;
	}

	@RequestMapping(value = "/reporte/{vuelta}/{codigoDispositivo}", method = RequestMethod.GET, produces = "application/pdf")
	public ResponseEntity<byte[]> reporteGET(
			@PathVariable("vuelta") Integer idVuelta,
			@PathVariable("codigoDispositivo") Integer idCodigoDispositivo,
			HttpServletResponse response
	) {
		RespuestaDto respuestaDto = new RespuestaDto();
		ReporteVueltaDto reporteVueltaDto = new ReporteVueltaDto();
		reporteVueltaDto.setVuelta(idVuelta);
		reporteVueltaDto.setCodigoDispositivo(idCodigoDispositivo);

		try {
			respuestaDto.setEstado(Boolean.TRUE);
			geoTabService.generarReporte(reporteVueltaDto);
			respuestaDto.setObjeto(reporteVueltaDto);
		} catch (Exception ex) {
			respuestaDto.setEstado(Boolean.FALSE);
			respuestaDto.setMensaje(ex.getMessage());
		}

	    Path path = Paths.get(reporteVueltaDto.getNombre());
	    byte[] pdfContents = null;
	    try {
	        pdfContents = Files.readAllBytes(path);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    HttpHeaders headers = new HttpHeaders();
	    ////headers.setContentType(MediaType.parseMediaType("application/pdf"));
	    //String filename = "data.pdf";
	    //headers.add("content-disposition", "inline; filename=" + filename);
	    //headers.add("content-disposition", "attachment; filename=" + filename);
	    //headers.setContentDispositionFormData(filename, filename);
	    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
	    ResponseEntity<byte[]> response1 = new ResponseEntity<byte[]>(
	            pdfContents, headers, HttpStatus.OK);
	    return response1;
	}

	@RequestMapping(value = "/sincronizarDispositivos", method = RequestMethod.POST)
	public RespuestaDto sincronizarDispositivos(
			@RequestBody List<DispositivoGeotabDto> dispositivos) {
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
	public RespuestaDto sincronizarZonas(
			@RequestBody SincronizarZonasDto sincronizarZonasDto) {
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
	public RespuestaDto procesarLocalizaciones(
			@RequestBody ProcesaDatosGeotabDto procesaDatosGeotabDto) {
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
	public RespuestaDto procesarTablero(
			@AuthenticationPrincipal UsuarioAuthenticate usuario) {
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

	@RequestMapping(value = "/procesarTableroHistorico", method = RequestMethod.POST)
	public RespuestaDto procesarTableroHistorico(
			@AuthenticationPrincipal UsuarioAuthenticate usuario, @RequestBody Date fecha) {
		RespuestaDto respuestaDto = new RespuestaDto();
		//System.out.print("Fecha: ");
		//System.out.println(fecha);
		try {
			respuestaDto.setEstado(Boolean.TRUE);
			respuestaDto.setObjeto(geoTabService.devolverTableroHistorico(
					usuario, fecha));

		} catch (Exception ex) {
			respuestaDto.setEstado(Boolean.FALSE);
			respuestaDto.setMensaje(ex.getMessage());
		}

		return respuestaDto;
	}

	@RequestMapping(value = "/horaProgramada", method = RequestMethod.POST)
	public RespuestaDto regitrarHoraProgramada(
			@RequestBody VTablero vlocalizacionDispositivo) {
		RespuestaDto respuestaDto = new RespuestaDto();

		try {
			geoTabService.registrarHoraProgramada(vlocalizacionDispositivo);
			respuestaDto.setEstado(Boolean.TRUE);

		} catch (Exception ex) {
			respuestaDto.setEstado(Boolean.FALSE);
			respuestaDto.setMensaje(ex.getMessage());
		}

		return respuestaDto;
	}

	@JsonView(ViewOT.PublicView.class)
	@RequestMapping(value = "/listar/dispositivos", method = RequestMethod.GET)
	public List<Dispositivo> traerInstrumentos(
			@AuthenticationPrincipal UsuarioAuthenticate usuario) {
		return geoTabService.devolverDispositivos(usuario.getRuta());
	}

	@JsonView(ViewOT.PublicView.class)
	@RequestMapping(value = "/listar/empresas", method = RequestMethod.GET)
	public List<Empresa> traerEmpresas(
			@AuthenticationPrincipal UsuarioAuthenticate usuario) {
		return geoTabService.traerEmpresas();
	}

	@JsonView(ViewOT.PublicView.class)
	@RequestMapping(value = "/rutas", method = RequestMethod.GET)
	public List<Ruta> traerRutas(@RequestParam Integer codigoEmpresa) {
		return geoTabService.devolverRutas(codigoEmpresa);
	}

	@JsonView(ViewOT.PublicView.class)
	@RequestMapping(value = "/zonas", method = RequestMethod.GET)
	public List<Zona> traerZonas(@RequestParam Integer codigoRuta) {
		return geoTabService.traerZonas(codigoRuta);
	}
}
