package com.tecnolpet.ot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tecnolpet.ot.model.Cliente;
import com.tecnolpet.ot.model.Empresa;
import com.tecnolpet.ot.model.PerfilEmpresa;
import com.tecnolpet.ot.model.Permiso;
import com.tecnolpet.ot.model.PermisoPerfilEmpresa;
import com.tecnolpet.ot.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	public List<Usuario> findByPerfilEmpresa(PerfilEmpresa perfilEmpresa);
	
	Usuario findByUsername(String username);
	
	public List<Usuario> findByEstadoUsuario(Boolean estado); 
	
	public List<Usuario> findUsuarioByPerfilEmpresa(PerfilEmpresa perfilEmpresa);
	
	@Query( value = "Select u.* from usuario u INNER JOIN perfil_empresa p ON u.id_perfil_empresa = p.id_perfil_empresa and p.estado = true where p.id_empresa = :idEmpresa", nativeQuery = true)
	public List<Usuario> findUsuarioEmpresa(@Param("idEmpresa") Integer idEmpresa);	
	
	@Query("Select u from Usuario u where u.username = :username and u.perfilEmpresa.empresa.id =:id")
	public Usuario findByUsernameEmpresa(@Param("username") String username,@Param("id") Integer id); 
	
	//@Query("Select u from Usuario u where u.perfilEmpresa.empresa= :empresa and u.cliente= :cliente")
	//public List<Usuario> findUsuariosByCliente(@Param("empresa") Empresa empresa,@Param("cliente") Cliente cliente); 
	
	@Query("Select pf from PermisoPerfilEmpresa pf where pf.perfilEmpresa = :perfilEmpresa and pf.permiso.permiso is null and pf.perfilEmpresa.empresa.id= :id order by pf.permiso.ordenPermiso")
	public List<PermisoPerfilEmpresa> findByPerfil(@Param("perfilEmpresa") PerfilEmpresa perfilEmpresa,@Param("id") Integer id);

	@Query("Select pf from PermisoPerfilEmpresa pf where pf.perfilEmpresa = :perfilEmpresa and pf.permiso.permiso = :permiso and pf.perfilEmpresa.empresa.id=:id order by pf.permiso.ordenPermiso")
	public List<PermisoPerfilEmpresa> findByPerfilHijos(
			@Param("perfilEmpresa") PerfilEmpresa perfilEmpresa, @Param("permiso") Permiso permiso,@Param("id") Integer id);

	@Query( value = "Select u.* from usuario u INNER JOIN perfil_empresa p ON u.id_perfil_empresa = p.id_perfil_empresa where u.id_perfil_empresa = :idPerfilEmpresa and p.estado = true", nativeQuery = true) 
	public List<Usuario> listarUsuarioPorPerfilEmpresa(@Param("idPerfilEmpresa") Integer idPerfilEmpresa);	
	
}
