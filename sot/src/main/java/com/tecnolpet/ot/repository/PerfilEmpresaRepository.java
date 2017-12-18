package com.tecnolpet.ot.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tecnolpet.ot.model.Empresa;
import com.tecnolpet.ot.model.Perfil;
import com.tecnolpet.ot.model.PerfilEmpresa;



public interface PerfilEmpresaRepository extends JpaRepository<PerfilEmpresa, Integer>{
	
	//public List<PerfilProducto> findByPerfil();	
	public List<PerfilEmpresa> findByPerfil(Perfil perfil);
	
	public List<PerfilEmpresa> findByEmpresaAndEstado(Empresa empresa, Boolean estado);
	
	public PerfilEmpresa findByPerfilAndEmpresaAndEstado(Perfil perfil, Empresa empresa,Boolean estado);

}

