package com.tecnolpet.ot.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecnolpet.ot.model.Perfil;
import com.tecnolpet.ot.repository.PerfilRepository;
import com.tecnolpet.ot.repository.UsuarioRepository;



@Service
public class PerfilService {

	@Autowired
	private PerfilRepository perfilRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	

	public List<Perfil> traePerfil() {
		return perfilRepository.findByEstadoPerfil(Boolean.TRUE);
	}

	public void guardar(Perfil perfil,String op) throws Exception {

		if(!op.equals("update")){

		for (Perfil p : traePerfil()) {
			
			if (p.getNombrePerfil()
					.equalsIgnoreCase(perfil.getNombrePerfil())) {
				throw new Exception(
						"Ya existe un perfil con ese nombre.");
			}

		}
		}

		perfil.setEstadoPerfil(Boolean.TRUE);
		perfilRepository.save(perfil);
	}
	

	public void eliminar(Perfil perfil) throws Exception {

		if (existeUsuarioByPerfil(perfil)) {
			throw new Exception(
					"No se puede eliminar porque que existe un usuario activo enrolado a este perfil.");

		}

		perfil.setEstadoPerfil(Boolean.FALSE);
		perfilRepository.save(perfil);

	}

	private boolean existeUsuarioByPerfil(Perfil perfil) {

		boolean existe = false;
		/*
		List<PerfilEmpresa> listaPerfilesByProducto = perfilproductoRepository
				.findByPerfil(perfil);

		List<Usuario> usuarioByperfilproducto = null;
	

		for (PerfilEmpresa pp : listaPerfilesByProducto) {
			
			usuarioByperfilproducto = usuarioRepository
					.findByPerfilEmpresa(pp);
				
			if (usuarioByperfilproducto.size()>0) {			
				existe = true;
				break;
			}

		}*/

		return existe;
	}

}

