package com.tecnolpet.ot.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tecnolpet.ot.dto.MenuDto;
import com.tecnolpet.ot.model.Perfil;
import com.tecnolpet.ot.model.Permiso;
import com.tecnolpet.ot.model.PermisoPerfil;
import com.tecnolpet.ot.repository.UsuarioRepository;


@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Transactional
	public List<MenuDto> getPermisos(Perfil perfil
			) {

		List<MenuDto> listaPermisos = new ArrayList<MenuDto>();
		List<PermisoPerfil> lista = usuarioRepository.findByPerfil(
				perfil);
		MenuDto menuDto;
		for (PermisoPerfil pp : lista) {
			menuDto = new MenuDto();
			menuDto.setIconoPermiso(pp.getPermiso().getIconoPermiso());
			menuDto.setId(pp.getPermiso().getId());
			menuDto.setNombrePermiso(pp.getPermiso().getNombrePermiso());
			menuDto.setOrdenPermiso(pp.getPermiso().getOrdenPermiso());
			menuDto.setUrlPermiso(pp.getPermiso().getUrlPermiso());
			listaPermisos.add(menuDto);
			getPermisosHijos(perfil, pp.getPermiso(), 
					menuDto);

		}

		return listaPermisos;
	}

	private void getPermisosHijos(Perfil perfil,
			Permiso permiso,  MenuDto menuDto) {

		List<PermisoPerfil> lista = usuarioRepository
				.findByPerfilHijos(perfil, permiso );
		MenuDto menuDtoHijo;

		for (PermisoPerfil pp : lista) {
			menuDtoHijo = new MenuDto();
			menuDtoHijo.setIconoPermiso(pp.getPermiso().getIconoPermiso());
			menuDtoHijo.setId(pp.getPermiso().getId());
			menuDtoHijo.setNombrePermiso(pp.getPermiso().getNombrePermiso());
			menuDtoHijo.setOrdenPermiso(pp.getPermiso().getOrdenPermiso());
			menuDtoHijo.setUrlPermiso(pp.getPermiso().getUrlPermiso());
			menuDto.getPermisos().add(menuDtoHijo);
			getPermisosHijos(perfil, pp.getPermiso(), menuDtoHijo);
		}

	}

}
