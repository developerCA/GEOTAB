package com.tecnolpet.ot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecnolpet.ot.dto.PermisoDto;
import com.tecnolpet.ot.model.PermisoPerfilEmpresa;
import com.tecnolpet.ot.repository.PermisoPerfilEmpresaRepository;



@Service
public class PermisoPerfilEmpresaService {

	@Autowired
	private PermisoPerfilEmpresaRepository permisoPerfilEmpresaRepository;

	public List<PermisoPerfilEmpresa> listaPadresProductoPermiso(
			Integer idEmpresaPermiso) {
		return permisoPerfilEmpresaRepository
				.buscarPadresPermisoPerfil(idEmpresaPermiso);
	}

	public List<PermisoPerfilEmpresa> buscarHijosPermisoPerfil(
			Integer idPerfilEmpresa, Integer idPadre) {
		return permisoPerfilEmpresaRepository.buscarHijosPermisoPerfil(
				idPerfilEmpresa, idPadre);
	}

	public List<PermisoPerfilEmpresa> buscarPadre(Integer idPerfilEmpresa,
			Integer idPadre) {
		return permisoPerfilEmpresaRepository.buscarPadre(idPerfilEmpresa,
				idPadre);
	}

	public List<PermisoDto> arbolPermisoPerfilEmpresa(Integer idEmpresaPermiso) {
		List<PermisoPerfilEmpresa> lista = listaPadresProductoPermiso(idEmpresaPermiso);
		List<PermisoDto> permiso = new ArrayList<PermisoDto>();
		for (PermisoPerfilEmpresa p : lista) {
			PermisoDto dto = new PermisoDto();
			dto.setLabel(p.getPermiso().getNombrePermiso());
			dto.setId(p.getId());
			dto = llenarHijosPermisoPerfilEmpresa(dto, p.getPermiso().getId(),
					idEmpresaPermiso);
			permiso.add(dto);
		}
		return permiso;
	}

	public PermisoDto llenarHijosPermisoPerfilEmpresa(PermisoDto dto,
			Integer idPadrePermiso, Integer idEmpresaPermiso) {

		List<PermisoPerfilEmpresa> lista = buscarHijosPermisoPerfil(
				idEmpresaPermiso, idPadrePermiso);

		List<PermisoDto> permiso = new ArrayList<PermisoDto>();

		for (PermisoPerfilEmpresa ppp : lista) {
			PermisoDto hijo = new PermisoDto();
			hijo.setId(ppp.getId());
			hijo.setLabel(ppp.getPermiso().getNombrePermiso());

			if (ppp.getPermiso().getPermisos().size() > 0) {
				hijo = llenarHijosPermisoPerfilEmpresa(dto, ppp.getPermiso()
						.getId(), idEmpresaPermiso);
			}

			permiso.add(hijo);
		}

		dto.setChildren(permiso);
		return dto;
	}

	public void guardarAsignacion(PermisoPerfilEmpresa permisoPerfilEmpresa) {
		List<PermisoPerfilEmpresa> lista = buscarPermisoPerfilEmpresa(
				permisoPerfilEmpresa.getPerfilEmpresa().getId(),
				permisoPerfilEmpresa.getPermiso().getId());

		if (lista == null || lista.isEmpty()) {
			if (permisoPerfilEmpresa.getPermiso().getPermiso() != null) {
				// busco si tiene permiso al padre

				List<PermisoPerfilEmpresa> padres = buscarPadre(
						permisoPerfilEmpresa.getPerfilEmpresa().getId(),
						permisoPerfilEmpresa.getPermiso().getPermiso().getId());
				

				if (padres == null || padres.isEmpty()) {

					PermisoPerfilEmpresa padre = new PermisoPerfilEmpresa();
					padre.setPerfilEmpresa(permisoPerfilEmpresa
							.getPerfilEmpresa());
					padre.setPermiso(permisoPerfilEmpresa.getPermiso()
							.getPermiso());

					guardarAsignacion(padre);
				}
			}
			permisoPerfilEmpresaRepository.save(permisoPerfilEmpresa);
		}

	}

	public void eliminarAsignacion(Integer idPermisoPefilEmpersa) {
		permisoPerfilEmpresaRepository.delete(idPermisoPefilEmpersa);
	}

	public List<PermisoPerfilEmpresa> buscarPermisoPerfilEmpresa(
			Integer idPerfilEmpresa, Integer idPerfil) {
		return permisoPerfilEmpresaRepository.buscarPermisoPerfilEmpresa(
				idPerfilEmpresa, idPerfil);
	}

}
