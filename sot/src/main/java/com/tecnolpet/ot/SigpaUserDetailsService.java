package com.tecnolpet.ot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.tecnolpet.ot.model.Usuario;
import com.tecnolpet.ot.repository.UsuarioRepository;
import com.tecnolpet.ot.seguridad.UsuarioAuthenticate;
import com.tecnolpet.ot.service.UsuarioService;

@Component
public class SigpaUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository userRepository;
	@Autowired
	UsuarioService usuarioService;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		Usuario user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("UserName " + username
					+ " not found");
		}
		UsuarioAuthenticate usuarioAuthenticate = new UsuarioAuthenticate();
		usuarioAuthenticate.setId(user.getId());
		usuarioAuthenticate.setNombresCompletos(user.getNombreUsuario());
		usuarioAuthenticate.setUsername(user.getUsername());
		usuarioAuthenticate.setEmail(user.getEmailUsuario());
		usuarioAuthenticate.setPassword(user.getPassword());
		usuarioAuthenticate.setPerfil_empresa(user.getPerfilEmpresa().getId());
		usuarioAuthenticate.setRuta(user.getRuta());
		usuarioAuthenticate.setPermisos(usuarioService.getPermisos(
				user.getPerfilEmpresa(), user.getPerfilEmpresa().getEmpresa()
						.getId()));
		return new SecurityUser(usuarioAuthenticate);

	}

}
