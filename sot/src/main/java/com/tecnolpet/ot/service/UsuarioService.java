package com.tecnolpet.ot.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tecnolpet.ot.dto.MenuDto;
import com.tecnolpet.ot.model.PerfilEmpresa;
import com.tecnolpet.ot.model.Permiso;
import com.tecnolpet.ot.model.PermisoPerfilEmpresa;
import com.tecnolpet.ot.repository.UsuarioRepository;


@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Transactional
	public List<MenuDto> getPermisos(PerfilEmpresa perfilEmpresa,
			Integer idEmpresa) {

		List<MenuDto> listaPermisos = new ArrayList<MenuDto>();
		List<PermisoPerfilEmpresa> lista = usuarioRepository.findByPerfil(
				perfilEmpresa, idEmpresa);
		MenuDto menuDto;
		for (PermisoPerfilEmpresa pp : lista) {
			menuDto = new MenuDto();
			menuDto.setIconoPermiso(pp.getPermiso().getIconoPermiso());
			menuDto.setId(pp.getPermiso().getId());
			menuDto.setNombrePermiso(pp.getPermiso().getNombrePermiso());
			menuDto.setOrdenPermiso(pp.getPermiso().getOrdenPermiso());
			menuDto.setUrlPermiso(pp.getPermiso().getUrlPermiso());
			listaPermisos.add(menuDto);
			getPermisosHijos(perfilEmpresa, pp.getPermiso(), idEmpresa,
					menuDto);

		}

		return listaPermisos;
	}

	private void getPermisosHijos(PerfilEmpresa perfilEmpresa,
			Permiso permiso, Integer idEmpresa, MenuDto menuDto) {

		List<PermisoPerfilEmpresa> lista = usuarioRepository
				.findByPerfilHijos(perfilEmpresa, permiso, idEmpresa);
		MenuDto menuDtoHijo;

		for (PermisoPerfilEmpresa pp : lista) {
			menuDtoHijo = new MenuDto();
			menuDtoHijo.setIconoPermiso(pp.getPermiso().getIconoPermiso());
			menuDtoHijo.setId(pp.getPermiso().getId());
			menuDtoHijo.setNombrePermiso(pp.getPermiso().getNombrePermiso());
			menuDtoHijo.setOrdenPermiso(pp.getPermiso().getOrdenPermiso());
			menuDtoHijo.setUrlPermiso(pp.getPermiso().getUrlPermiso());
			menuDto.getPermisos().add(menuDtoHijo);
			getPermisosHijos(perfilEmpresa, pp.getPermiso(), idEmpresa,menuDtoHijo);
		}

	}

}
