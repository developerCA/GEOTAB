package com.tecnolpet.ot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tecnolpet.ot.constant.SotApp;
import com.tecnolpet.ot.dto.AdminUsuarioDto;
import com.tecnolpet.ot.model.Empresa;
import com.tecnolpet.ot.model.Perfil;
import com.tecnolpet.ot.model.Usuario;
import com.tecnolpet.ot.repository.EmpresaRepository;
import com.tecnolpet.ot.repository.PerfilRepository;
import com.tecnolpet.ot.repository.UsuarioRepository;
import com.tecnolpet.ot.seguridad.UsuarioAuthenticate;
import com.tecnolpet.ot.utils.ClavesUtils;

@Service
public class UserService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PerfilRepository perfilRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	

	@Autowired
	private EnviarCorreoService enviarCorreoService;

	@Autowired
	private PasswordEncoder passwordEncoder;

//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}

	public List<Usuario> traerUsuariosPorEstado(boolean estado) {
		return usuarioRepository.findByEstadoUsuario(estado);
	}

	public List<Usuario> traerUsuariosEmpresa(Integer idEmpresa) {
		return usuarioRepository.findUsuarioEmpresa(idEmpresa);
	}

	/*public List<Usuario> traerUsuariosPorPerfilProducto(
			PerfilEmpresa perfilEmpresa) {
		return usuarioRepository.findUsuarioByPerfilEmpresa(perfilEmpresa);
	}*/

	public List<Usuario> listarUsuariosPorPerfilEmpresa(Integer idPerfil,Integer idEmpresa) {
		Perfil perfil=perfilRepository.findOne( idPerfil);
		Empresa empresa=empresaRepository.findOne(idEmpresa);
		
		return usuarioRepository.listarUsuarioPorPerfilEmpresa(perfil, empresa);
	}

	public String generarPassword() {
		return ClavesUtils.generarClaves(SotApp.AlgoritmoClaves.GENERACLAVE, 8);
	}

	public void guardar(AdminUsuarioDto adminUsuarioDto, String op)
			throws Exception {

		Usuario usuario = adminUsuarioDto.getUsuario();
		usuario.setPerfil(adminUsuarioDto.getPerfil());
		usuario.setEmpresa(adminUsuarioDto.getEmpresa());

		if (!op.equals("update")) {
			for (Usuario u : traerUsuariosPorEstado(true)) {
				if (u.getUsername().equalsIgnoreCase(usuario.getUsername())) {
					throw new Exception(
							"Usuario ya registrado, digite otro por favor..");
				}
			}

			String clave = adminUsuarioDto.getPassword();
			usuario.setPassword(passwordEncoder.encode(adminUsuarioDto
					.getPassword()));
			enviarCorreoService.armarEnvioClaveHtml(clave,
					usuario.getNombreUsuario(), usuario.getUsername(),
					usuario.getEmailUsuario());
		}

		usuario.setEstadoUsuario(usuario.getEstadoUsuario());
		
	
		usuarioRepository.save(usuario);
	}

	public void cambiaClave(UsuarioAuthenticate usuario, String nuevaClave)
			throws Exception {

		Usuario usuarioUpdate = usuarioRepository.findOne(usuario.getId());

		usuarioUpdate.setPassword(passwordEncoder.encode(nuevaClave));
		enviarCorreoService.armarEnvioResetClaveHtml	(nuevaClave,
				usuarioUpdate.getNombreUsuario(), usuarioUpdate.getUsername(),
				usuarioUpdate.getEmailUsuario());

		usuarioRepository.save(usuarioUpdate);
	}

}
