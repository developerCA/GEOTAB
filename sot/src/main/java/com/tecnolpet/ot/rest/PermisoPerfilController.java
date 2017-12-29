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
import com.tecnolpet.ot.model.PermisoPerfil;
import com.tecnolpet.ot.service.EmpresaService;
import com.tecnolpet.ot.service.PermisoPerfilService;
import com.tecnolpet.ot.service.PermisoService;



//se crea el webservice que trae los datos
@RestController
@RequestMapping("api/permisoPerfil")
public class PermisoPerfilController {

	
	@Autowired
	private PermisoService permisoService;
	@Autowired
	private EmpresaService empresaService;
	@Autowired
	private PermisoPerfilService permisoPerfilEmpresaService;

	@RequestMapping(method = RequestMethod.GET)
	public List<Empresa> traerPerfilProducto() {
		return empresaService.traerEmpresa();
	}

	/*@RequestMapping(value = "/traePerfiles/{producto}", method = RequestMethod.GET)
	public List<PerfilEmpresa> traerPerfiles(
			@PathVariable("producto") Integer id) {
		Empresa p = empresaService. findByOne(id);
		return perfilEmpresaService.findPerfilProductoByProducto(p, true);
	}  */

	@RequestMapping(value = "/traeArbol", method = RequestMethod.GET)
	public List<PermisoDto> buscarArbol() {
		return permisoService.listaArbol();
	}

	@RequestMapping(value = "/traeArbolPermisoPerfilProducto/{idPermisoProducto}", method = RequestMethod.GET)
	public List<PermisoDto> arbolPermisoPerfilProducto(
			@PathVariable("idPermisoProducto") Integer idPermisoPerfil) {
		return permisoPerfilEmpresaService
				.arbolPermisoPerfilEmpresa(idPermisoPerfil);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public RespuestaDto guardarAsignacion(
			@RequestBody PermisoPerfil permisoPerfilEmpresa) {

		RespuestaDto respuesta = new RespuestaDto();
		Permiso permiso = permisoService.traerPermiso(permisoPerfilEmpresa
				.getIdPermiso());
		/*PerfilEmpresa perfilProducto = perfilEmpresaService
				.buscarPorId(permisoPerfilEmpresa.getIdPermisoPerfil());

		permisoPerfilEmpresa.setPermiso(permiso);
		permisoPerfilEmpresa.setPerfilEmpresa(perfilProducto);*/

		try {

			permisoPerfilEmpresaService
					.guardarAsignacion(permisoPerfilEmpresa);
			respuesta.setEstado(Boolean.TRUE);

		} catch (Exception ex) {

			respuesta.setEstado(Boolean.FALSE);
			respuesta.setMensaje(ex.getMessage());

		}

		return respuesta;

	}

	@RequestMapping(method = RequestMethod.PUT, value = "/eliminarAsignacion")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void delete(@RequestBody PermisoPerfil permisoPerfilEmpresa) {
		permisoPerfilEmpresaService.eliminarAsignacion(permisoPerfilEmpresa.getId());
				
	}

}
