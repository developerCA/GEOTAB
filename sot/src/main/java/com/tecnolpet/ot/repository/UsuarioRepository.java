package com.tecnolpet.ot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tecnolpet.ot.model.Perfil;
import com.tecnolpet.ot.model.Permiso;
import com.tecnolpet.ot.model.PermisoPerfil;
import com.tecnolpet.ot.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	//public List<Usuario> findByPerfilEmpresa(PerfilEmpresa perfilEmpresa);
	
	Usuario findByUsername(String username);
	
	public List<Usuario> findByEstadoUsuario(Boolean estado); 
	
	//public List<Usuario> findUsuarioByPerfilEmpresa(PerfilEmpresa perfilEmpresa);
	
	@Query( value = "Select u.* from usuario u INNER JOIN perfil_empresa p ON u.id_perfil_empresa = p.id_perfil_empresa and p.estado = true where p.id_empresa = :idEmpresa", nativeQuery = true)
	public List<Usuario> findUsuarioEmpresa(@Param("idEmpresa") Integer idEmpresa);	
	

	@Query("Select pf from PermisoPerfil pf where pf.perfil = :perfil and pf.permiso.permiso is null order by pf.permiso.ordenPermiso")
	public List<PermisoPerfil> findByPerfil(@Param("perfil") Perfil perfil);

	@Query("Select pf from PermisoPerfil pf where pf.perfil = :perfil and pf.permiso.permiso = :permiso  order by pf.permiso.ordenPermiso")
	public List<PermisoPerfil> findByPerfilHijos(
			@Param("perfil") Perfil perfil, @Param("permiso") Permiso permiso);

	@Query( value = "Select u.* from usuario u INNER JOIN perfil_empresa p ON u.id_perfil_empresa = p.id_perfil_empresa where u.id_perfil_empresa = :idPerfilEmpresa and p.estado = true", nativeQuery = true) 
	public List<Usuario> listarUsuarioPorPerfilEmpresa(@Param("idPerfilEmpresa") Integer idPerfilEmpresa);	
	
}
