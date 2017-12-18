package com.tecnolpet.ot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecnolpet.ot.repository.AccesosRenovacionRepository;

@Service
public class AccesosRenovacionService {
	
	@Autowired
	private AccesosRenovacionRepository accesosRenovacionRepository;
	
}
