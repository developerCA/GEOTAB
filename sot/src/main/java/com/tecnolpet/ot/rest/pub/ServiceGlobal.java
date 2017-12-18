package com.tecnolpet.ot.rest.pub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.dto.OrdenOTDto;
import com.tecnolpet.ot.dto.OrdenServiciosDto;
import com.tecnolpet.ot.dto.RespuestaDto;
import com.tecnolpet.ot.dto.ServicioReporteDto;
import com.tecnolpet.ot.jview.ViewOT;
import com.tecnolpet.ot.service.TareaService;

@RestController
@RequestMapping("global")
public class ServiceGlobal {

	private final TareaService tareaService;

	@Autowired
	public ServiceGlobal(TareaService tareaService) {
		this.tareaService = tareaService;
	}
	@JsonView(ViewOT.PublicView.class)
	@RequestMapping(value = "/wsOT", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public RespuestaDto traerOt(@RequestBody ServicioReporteDto servicio) {
		RespuestaDto respuesta = new RespuestaDto();

		try {

			OrdenOTDto orden= tareaService.traerServicio(servicio.getCodigoQR());
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("Ok");
			respuesta.setObjeto(orden);
		} catch (Exception ex) {
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}

		return respuesta;
	}
	@JsonView(ViewOT.PublicView.class)
	@RequestMapping(value = "/wsOTServicios", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public RespuestaDto traerOtServicios(@RequestBody ServicioReporteDto servicio) {
		RespuestaDto respuesta = new RespuestaDto();

		try {

			OrdenServiciosDto orden= tareaService.traerServiciosOT(servicio.getCodigoQR());
			respuesta.setEstado(Boolean.TRUE);
			respuesta.setMensaje("Ok");
			respuesta.setObjeto(orden);
		} catch (Exception ex) {
			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());
		}

		return respuesta;
	}
	
	
}
