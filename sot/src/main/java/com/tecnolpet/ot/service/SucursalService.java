package com.tecnolpet.ot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecnolpet.ot.model.Sucursal;
import com.tecnolpet.ot.repository.SucursalRepository;

@Service
public class SucursalService {
	
	@Autowired
	private SucursalRepository sucursalRepository;
	
	
	public List<Sucursal> traerSucursales(){
		return sucursalRepository.findSucursalByEstado(Boolean.TRUE);
	}
	
	
	public void guardar(Sucursal sucursal, String op) throws Exception{
		
		if(!op.equals("update")){

			for (Sucursal s : traerSucursales()) {
				
				if (s.getNombre()
						.equalsIgnoreCase(sucursal.getNombre())) {
					throw new Exception(
							"Ya existe una sucursal con ese nombre.");
				}

			}
			}
		
		sucursal.setEstado(Boolean.TRUE);
		sucursalRepository.save(sucursal);
	}
	
	public void eliminar(Sucursal sucursal){

		sucursal.setEstado(Boolean.FALSE);
		sucursalRepository.save(sucursal);
	}
	
	public Sucursal traerSucursalUsuario(Integer id){
		
		Sucursal sucursal=sucursalRepository.findOne(id);
		
		return sucursal;
	}
}
