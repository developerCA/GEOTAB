package com.tecnolpet.ot.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecnolpet.ot.model.Empresa;
import com.tecnolpet.ot.model.PerfilEmpresa;
import com.tecnolpet.ot.model.Usuario;
import com.tecnolpet.ot.repository.EmpresaRepository;
import com.tecnolpet.ot.repository.PerfilEmpresaRepository;
import com.tecnolpet.ot.repository.UsuarioRepository;



@Service
public class EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PerfilEmpresaRepository perfilEmpresaRepository;
	
	public List<Empresa> traerEmpresa(){
		return empresaRepository.findByEstado(Boolean.TRUE);
	}
	
	public Empresa findByOne(Integer id){
		return empresaRepository.findOne(id);
	}
	
	public void guardar(Empresa empresa,String op) throws Exception{
		
		if(!op.equals("update")){

			for (Empresa p : traerEmpresa()) {
				
				if (p.getNombre()
						.equalsIgnoreCase(empresa.getNombre())) {
					throw new Exception(
							"Ya existe una empresa con ese nombre.");
				}

			}
			}
		
		empresa.setEstado(Boolean.TRUE);
		empresaRepository.save(empresa);
	}
	
	public void eliminar(Empresa empresa) throws Exception{
		
		if (existeUsuarioByEmpresa(empresa)) {
			throw new Exception(
					"No se puede eliminar porque que existe un usuario activo enrolado a esta empresa.");

		}
		
		empresa.setEstado(Boolean.FALSE);
		empresaRepository.save(empresa);
	}
	
	  
	private boolean existeUsuarioByEmpresa(Empresa empresa) {

		List<PerfilEmpresa> listaPerfilesByProducto = perfilEmpresaRepository.findByEmpresaAndEstado(empresa, true);


		List<Usuario> usuarioByperfilproducto = null;
		boolean existe = false;

		for (PerfilEmpresa pp : listaPerfilesByProducto) {
			
			usuarioByperfilproducto = usuarioRepository.findByPerfilEmpresa(pp);
					
				
			if (usuarioByperfilproducto.size()>0) {			
				existe = true;
				break;
			}

		}

		return existe;
	}
	
	
	
	public void guardarPerfilEmpresa(PerfilEmpresa perfilEmpresa) throws Exception{
		perfilEmpresa.setEstado(true);
		perfilEmpresaRepository.save(perfilEmpresa);
	} 		 
	
	public void desactivarPerfilEmpresa(PerfilEmpresa perfilEmpresa) throws Exception{
		perfilEmpresa.setEstado(false);
		perfilEmpresaRepository.save(perfilEmpresa);
	}	
	
}

