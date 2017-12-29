package com.tecnolpet.ot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecnolpet.ot.dto.PermisoDto;
import com.tecnolpet.ot.model.Permiso;
import com.tecnolpet.ot.repository.PermisoRepository;

@Service
public class PermisoService {

	@Autowired
	private PermisoRepository permisoRepository;

	

	public List<PermisoDto> listaArbol() {

		List<Permiso> padres = permisoRepository.buscarPermisosPadre();

		List<PermisoDto> lista = new ArrayList<PermisoDto>();
		for (Permiso permiso : padres) {
			PermisoDto dto = new PermisoDto();
			dto.setId(permiso.getId());
			dto.setLabel(permiso.getNombrePermiso());
			dto = listaArbolHijo(dto);
			lista.add(dto);
		}

		return lista;
	}

	public PermisoDto listaArbolHijo(PermisoDto dtoPadre) {

		List<Permiso> hijos = permisoRepository.buscarPermisosHijo(dtoPadre.getId());

		List<PermisoDto> lista = new ArrayList<PermisoDto>();

		for (Permiso permiso : hijos) {

			PermisoDto dto = new PermisoDto();
			dto.setId(permiso.getId());
			dto.setLabel(permiso.getNombrePermiso());

			if (permiso.getPermisos().size() > 0) {
				dto = listaArbolHijo(dto);
			}

			lista.add(dto);
		}

		dtoPadre.setChildren(lista);
		return dtoPadre;
	}

	public List<PermisoDto> listaArbolActivo() {

		List<Permiso> padres = permisoRepository.findByEmpresaPadreActivo();

		List<PermisoDto> lista = new ArrayList<PermisoDto>();
		for (Permiso permiso : padres) {
			PermisoDto dto = new PermisoDto();
			dto.setId(permiso.getId());
			dto.setLabel(permiso.getNombrePermiso());
			dto = listaArbolHijoActivo(dto);
			lista.add(dto);
		}

		return lista;
	}

	public PermisoDto listaArbolHijoActivo(PermisoDto dtoPadre) {

		List<Permiso> hijos = permisoRepository.findByEmpresaHijoActivo(dtoPadre.getId());

		List<PermisoDto> lista = new ArrayList<PermisoDto>();

		for (Permiso permiso : hijos) {

			PermisoDto dto = new PermisoDto();
			dto.setId(permiso.getId());
			dto.setLabel(permiso.getNombrePermiso());

			if (permiso.getPermisos().size() > 0) {
				dto = listaArbolHijoActivo(dto);
			}

			lista.add(dto);
		}

		dtoPadre.setChildren(lista);
		return dtoPadre;
	}

	public void guardar(Permiso permiso) {
		permisoRepository.save(permiso);
	}

	public Permiso traerPermiso(Integer id) {
		return permisoRepository.findOne(id);
	}

}
