package com.tecnolpet.ot.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tecnolpet.ot.dto.PermisoDto;
import com.tecnolpet.ot.dto.RespuestaDto;
import com.tecnolpet.ot.model.Empresa;
import com.tecnolpet.ot.model.Permiso;
import com.tecnolpet.ot.service.PermisoService;



@RestController
@RequestMapping("api/permiso")
public class PermisoController {

	private final PermisoService permisoService;

	@Autowired
	public PermisoController(PermisoService permisoService) {
		this.permisoService = permisoService;
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public RespuestaDto guardarPermiso(@RequestBody Permiso permiso) {
		RespuestaDto respuestaDto = new RespuestaDto();
		if (permiso.getIdEmpresa() != null) {
			Empresa p = new Empresa();
			p.setId(permiso.getIdEmpresa());
			permiso.setEmpresa(p);
		}
		if (permiso.getIdPermisoPadre() != null) {
			Permiso per = new Permiso();
			per.setId(permiso.getIdPermisoPadre());
			permiso.setPermiso(per);
		}
		try {
			permisoService.guardar(permiso);
			respuestaDto.setEstado(Boolean.TRUE);
			respuestaDto.setMensaje("OK");
		} catch (Exception ex) {
			respuestaDto.setEstado(Boolean.FALSE);
			respuestaDto.setMensaje(ex.getMessage());
		}

		return respuestaDto;
	}

	@RequestMapping(value = "/traeArbol/{idProducto}", method = RequestMethod.GET)
	public List<PermisoDto> buscarArbol(
			@PathVariable("idProducto") Integer idProducto) {
		List<PermisoDto> lista = permisoService.listaArbol(idProducto);
		return lista;
	}

	@RequestMapping(value = "/traePermiso/{id}", method = RequestMethod.GET)
	public Permiso traerPermiso(@PathVariable("id") Integer idPermiso) {
		return permisoService.traerPermiso(idPermiso);
	}

}