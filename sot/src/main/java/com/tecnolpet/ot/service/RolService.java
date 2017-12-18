package com.tecnolpet.ot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecnolpet.ot.model.Rol;
import com.tecnolpet.ot.model.UsuarioRol;
import com.tecnolpet.ot.repository.RolRepository;
import com.tecnolpet.ot.repository.UsuarioRepository;
import com.tecnolpet.ot.repository.UsuarioRolRepository;



@Service
public class RolService {

	@Autowired
	private RolRepository rolRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioRolRepository usuarioRolRepository;
	
	
	public List<Rol> traeRoles(){
		
		return rolRepository.findByEstadoRol(Boolean.TRUE);
	}
	
	public void guardar(Rol rol,String op) throws Exception{
		
		if(!op.equals("update")){
		for (Rol r : traeRoles()) {
			
			if (r.getNombreRol()
					.equalsIgnoreCase(rol.getNombreRol())) {
				throw new Exception(
						"Ya existe un rol con ese nombre.");
			}

		}
		}
		
		rol.setEstadoRol(Boolean.TRUE);
		rolRepository.save(rol);
		
	}
	
	public void eliminarRol(Rol rol) throws Exception{
		
		if (existeUsuarioByRol(rol)) {
			throw new Exception(
					"No se puede eliminar porque que existe un usuario activo registrado en este rol.");

		}
		
		rol.setEstadoRol(Boolean.FALSE);
		rolRepository.save(rol);
	}
	
	
	private boolean existeUsuarioByRol(Rol rol) {

		List<UsuarioRol> listaUsuariosRolByRol = usuarioRolRepository.findUsuarioRolByRol(rol);

		boolean existe = false;

		for (UsuarioRol ur : listaUsuariosRolByRol) {
			if ( ur.getUsuario().getEstadoUsuario()==true) {
				existe = true;
				break;
			}

		}

		return existe;
	}
	

	
}