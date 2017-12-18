package com.tecnolpet.ot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecnolpet.ot.model.Empresa;
import com.tecnolpet.ot.model.Perfil;
import com.tecnolpet.ot.model.PerfilEmpresa;
import com.tecnolpet.ot.repository.PerfilEmpresaRepository;



@Service
public class PerfilEmpresaService {

	@Autowired
	private PerfilEmpresaRepository perfilEmpresaRepository;

	public List<PerfilEmpresa> findPerfilEmpresaByPerfil(Perfil perfil) {
		return perfilEmpresaRepository.findByPerfil(perfil);
	}

	public List<PerfilEmpresa> findPerfilProductoByProducto(Empresa empresa, Boolean estado) {
		return perfilEmpresaRepository.findByEmpresaAndEstado(empresa, estado);
	} 
	
	public PerfilEmpresa findPerfilProductoByProducto(Perfil perfil, Empresa empresa,Boolean estado) {
		return perfilEmpresaRepository.findByPerfilAndEmpresaAndEstado(perfil, empresa,estado);
	}	 
 
	public void guardar(PerfilEmpresa p) {
		perfilEmpresaRepository.save(p);
	}

	public PerfilEmpresa buscarPorId(Integer id) {
		return perfilEmpresaRepository.findOne(id);
	}
}
