package com.tecnolpet.ot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecnolpet.ot.model.Profesion;
import com.tecnolpet.ot.repository.ProfesionRepository;

@Service
public class ProfesionService {

	@Autowired
	private ProfesionRepository profesionRepository;
	
	
	public List<Profesion>traerProfesiones(){	
		return profesionRepository.findAll();
	}
	
	public void guardar(Profesion profesion,String op) throws Exception{
		
		if(!op.equals("update")){

			for (Profesion s : traerProfesiones()) {
				
				if (s.getNombre()
						.equalsIgnoreCase(profesion.getNombre())) {
					throw new Exception(
							"Ya existe una profesión con ese nombre.");
				}

			}
			}
		
		profesionRepository.save(profesion);
	}
}
