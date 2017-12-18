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

	public List<Permiso> findByProducto(Integer idEmpresa) {
		return permisoRepository.findByEmpresaId(idEmpresa);
	}

	
	public List<PermisoDto> listaArbol(Integer idEmpresa) {

		List<Permiso> padres = permisoRepository
				.findByEmpresaPadre(idEmpresa);

		List<PermisoDto> lista = new ArrayList<PermisoDto>();
		for (Permiso permiso : padres) {
			PermisoDto dto = new PermisoDto();
			dto.setId(permiso.getId());
			dto.setLabel(permiso.getNombrePermiso());
			dto = listaArbolHijo(idEmpresa, dto);
			lista.add(dto);
		}

		return lista;
	}

	public PermisoDto listaArbolHijo(Integer idEmpresa, PermisoDto dtoPadre) {

		List<Permiso> hijos = permisoRepository.findByEmpresaHijo(idEmpresa,
				dtoPadre.getId());

		List<PermisoDto> lista = new ArrayList<PermisoDto>();

		for (Permiso permiso : hijos) {

			PermisoDto dto = new PermisoDto();
			dto.setId(permiso.getId());
			dto.setLabel(permiso.getNombrePermiso());

			if (permiso.getPermisos().size() > 0) {
				dto = listaArbolHijo(idEmpresa, dto);
			}

			lista.add(dto);
		}

		dtoPadre.setChildren(lista);
		return dtoPadre;
	}

	public List<PermisoDto> listaArbolActivo(Integer idEmpresa) {

		
		List<Permiso> padres = permisoRepository
				.findByEmpresaPadreActivo(idEmpresa);
		

		List<PermisoDto> lista = new ArrayList<PermisoDto>();
		for (Permiso permiso : padres) {
			PermisoDto dto = new PermisoDto();
			dto.setId(permiso.getId());
			dto.setLabel(permiso.getNombrePermiso());
			dto = listaArbolHijoActivo(idEmpresa, dto);
			lista.add(dto);
		}

		return lista;
	}

	public PermisoDto listaArbolHijoActivo(Integer idEmpresa,
			PermisoDto dtoPadre) {

		List<Permiso> hijos = permisoRepository.findByEmpresaHijoActivo(
				idEmpresa, dtoPadre.getId());

		List<PermisoDto> lista = new ArrayList<PermisoDto>();

		for (Permiso permiso : hijos) {

			PermisoDto dto = new PermisoDto();
			dto.setId(permiso.getId());
			dto.setLabel(permiso.getNombrePermiso());

			if (permiso.getPermisos().size() > 0) {
				dto = listaArbolHijoActivo(idEmpresa, dto);
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

