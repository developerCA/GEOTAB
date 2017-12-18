package com.tecnolpet.ot.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tecnolpet.ot.dto.CalibracionDto;
import com.tecnolpet.ot.dto.RespuestaDto;
import com.tecnolpet.ot.model.Calibracion;
import com.tecnolpet.ot.model.Instrumento;
import com.tecnolpet.ot.seguridad.UsuarioAuthenticate;
import com.tecnolpet.ot.service.CalibracionService;

@RestController
@RequestMapping("api/calibracion")
public class CalibracionController {

	private final CalibracionService calibracionService;

	@Autowired
	public CalibracionController(CalibracionService calibracionService) {
		this.calibracionService = calibracionService;
	}

	@RequestMapping(value = "/instrumentos", method = RequestMethod.GET)
	public List<Instrumento> traerInstrumentos(
			@AuthenticationPrincipal UsuarioAuthenticate usuario) {
		return calibracionService.traerInstrumentos(usuario);
	}

	@RequestMapping(value = "/crear", method = RequestMethod.PUT)
	public RespuestaDto guardarInstrumento(
			@RequestBody Instrumento instrumento,
			@AuthenticationPrincipal UsuarioAuthenticate usuario) {
		RespuestaDto respuestaDto = new RespuestaDto();

		try {

			calibracionService.guardar(instrumento, usuario);
			respuestaDto.setEstado(Boolean.TRUE);
			respuestaDto
					.setMensaje("El instrumento se ha actualizado correctamente..");
			respuestaDto.setObjeto(instrumento);
		} catch (Exception ex) {
			respuestaDto.setEstado(Boolean.FALSE);
			respuestaDto.setMensaje(ex.getMessage());
		}

		return respuestaDto;
	}

	@RequestMapping(value = "/actualizar", method = RequestMethod.PUT)
	public RespuestaDto actualizarInstrumento(
			@RequestBody Instrumento instrumento,
			@AuthenticationPrincipal UsuarioAuthenticate usuario) {
		RespuestaDto respuestaDto = new RespuestaDto();

		try {

			calibracionService.guardar(instrumento, usuario);
			respuestaDto.setEstado(Boolean.TRUE);
			respuestaDto
					.setMensaje("El instrumento se ha actualizado correctamente..");
			respuestaDto.setObjeto(instrumento);
		} catch (Exception ex) {
			respuestaDto.setEstado(Boolean.FALSE);
			respuestaDto.setMensaje(ex.getMessage());
		}

		return respuestaDto;
	}

	@RequestMapping(value = "eliminar", method = RequestMethod.PUT)
	public RespuestaDto EliminarInstrumento(
			@RequestBody Instrumento instrumento,
			@AuthenticationPrincipal UsuarioAuthenticate usuario) {
		RespuestaDto respuestaDto = new RespuestaDto();

		try {

			calibracionService.eliminar(instrumento);
			respuestaDto.setEstado(Boolean.TRUE);
			respuestaDto
					.setMensaje("El instrumento se ha actualizado correctamente..");
			respuestaDto.setObjeto(instrumento);
		} catch (Exception ex) {
			respuestaDto.setEstado(Boolean.FALSE);
			respuestaDto.setMensaje(ex.getMessage());
		}

		return respuestaDto;
	}

	@RequestMapping(value = "/calibracion", method = RequestMethod.PUT)
	public RespuestaDto guardarCalibracion(
			@RequestBody Calibracion calibracion,
			@AuthenticationPrincipal UsuarioAuthenticate usuario) {
		RespuestaDto respuestaDto = new RespuestaDto();

		try {

			calibracionService.guardarCalibracion(calibracion,usuario);
			respuestaDto.setEstado(Boolean.TRUE);
			respuestaDto
					.setMensaje("El instrumneto se ha actualizado correctamente..");
			respuestaDto.setObjeto(calibracion);
		} catch (Exception ex) {
			respuestaDto.setEstado(Boolean.FALSE);
			respuestaDto.setMensaje(ex.getMessage());
		}

		return respuestaDto;
	}
	
	@RequestMapping(value = "/calibracionActualizar", method = RequestMethod.PUT)
	public RespuestaDto actualizarCalibracion(
			@RequestBody Calibracion calibracion,
			@AuthenticationPrincipal UsuarioAuthenticate usuario) {
		RespuestaDto respuestaDto = new RespuestaDto();

		try {

			calibracionService.actualizarCalibracion(calibracion,usuario);
			respuestaDto.setEstado(Boolean.TRUE);
			respuestaDto
					.setMensaje("El instrumento se ha actualizado correctamente..");
			respuestaDto.setObjeto(calibracion);
		} catch (Exception ex) {
			respuestaDto.setEstado(Boolean.FALSE);
			respuestaDto.setMensaje(ex.getMessage());
		}

		return respuestaDto;
	}
	
	

	

	@RequestMapping(value = "/calibracionesInstrumento", method = RequestMethod.GET)
	public List<CalibracionDto> traerCalibracionesInstrumento(
			@AuthenticationPrincipal UsuarioAuthenticate usuario) {

		List<CalibracionDto> list = calibracionService.traerCalibraciones(usuario);

		return list;
	}
	
	@RequestMapping(value = "/calibracionesPorInstrumento", method = RequestMethod.GET)
	public List<CalibracionDto> traerCalibracionesPorInstrumento(@RequestParam Integer calibracion,
			@AuthenticationPrincipal UsuarioAuthenticate usuario) {

		List<CalibracionDto> list = calibracionService.traerCalibracionesInstrumento(calibracion, usuario);

		return list;
	}

}
