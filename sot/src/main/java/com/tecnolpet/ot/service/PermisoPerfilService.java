package com.tecnolpet.ot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecnolpet.ot.dto.PermisoDto;
import com.tecnolpet.ot.model.PermisoPerfil;
import com.tecnolpet.ot.repository.PermisoPerfilRepository;



@Service
public class PermisoPerfilService {

	@Autowired
	private PermisoPerfilRepository permisoPerfilRepository;

	public List<PermisoPerfil> listaPadresProductoPermiso(
			Integer idPerfil) {
		return permisoPerfilRepository
				.buscarPadresPermisoPerfil(idPerfil);
	}

	public List<PermisoPerfil> buscarHijosPermisoPerfil(
			Integer idPerfil, Integer idPadre) {
		return permisoPerfilRepository.buscarHijosPermisoPerfil(
				idPerfil, idPadre);
	}

	public List<PermisoPerfil> buscarPadre(Integer idPerfil,
			Integer idPadre) {
		return permisoPerfilRepository.buscarPadre(idPerfil,
				idPadre);
	}

	public List<PermisoDto> arbolPermisoPerfilEmpresa(Integer idPerfil) {
		List<PermisoPerfil> lista = listaPadresProductoPermiso(idPerfil);
		List<PermisoDto> permiso = new ArrayList<PermisoDto>();
		for (PermisoPerfil p : lista) {
			PermisoDto dto = new PermisoDto();
			dto.setLabel(p.getPermiso().getNombrePermiso());
			dto.setId(p.getId());
			dto = llenarHijosPermisoPerfilEmpresa(dto, p.getPermiso().getId(),
					idPerfil);
			permiso.add(dto);
		}
		return permiso;
	}

	public PermisoDto llenarHijosPermisoPerfilEmpresa(PermisoDto dto,
			Integer idPadrePermiso, Integer idPerfil) {

		List<PermisoPerfil> lista = buscarHijosPermisoPerfil(
				idPerfil, idPadrePermiso);

		List<PermisoDto> permiso = new ArrayList<PermisoDto>();

		for (PermisoPerfil ppp : lista) {
			PermisoDto hijo = new PermisoDto();
			hijo.setId(ppp.getId());
			hijo.setLabel(ppp.getPermiso().getNombrePermiso());

			if (ppp.getPermiso().getPermisos().size() > 0) {
				hijo = llenarHijosPermisoPerfilEmpresa(dto, ppp.getPermiso()
						.getId(), idPerfil);
			}

			permiso.add(hijo);
		}

		dto.setChildren(permiso);
		return dto;
	}

	public void guardarAsignacion(PermisoPerfil permisoPerfil) {
		List<PermisoPerfil> lista = permisoPerfilRepository.buscarPermisoPerfil(
				permisoPerfil.getPerfil().getId(),
				permisoPerfil.getPermiso().getId());

		if (lista == null || lista.isEmpty()) {
			if (permisoPerfil.getPermiso().getPermiso() != null) {
				// busco si tiene permiso al padre

				List<PermisoPerfil> padres = buscarPadre(
						permisoPerfil.getPerfil().getId(),
						permisoPerfil.getPermiso().getPermiso().getId());
				

				if (padres == null || padres.isEmpty()) {

					PermisoPerfil padre = new PermisoPerfil();
					padre.setPerfil(permisoPerfil
							.getPerfil());
					padre.setPermiso(permisoPerfil.getPermiso()
							.getPermiso());

					guardarAsignacion(padre);
				}
			}
			permisoPerfilRepository.save(permisoPerfil);
		}

	}

	public void eliminarAsignacion(Integer idPermisoPefil) {
		permisoPerfilRepository.delete(idPermisoPefil);
	}

	public List<PermisoPerfil> buscarPermisoPerfil(
			Integer idPerfil, Integer idPermiso) {
		return permisoPerfilRepository.buscarPermisoPerfil(
				idPerfil, idPermiso);
	}

}
