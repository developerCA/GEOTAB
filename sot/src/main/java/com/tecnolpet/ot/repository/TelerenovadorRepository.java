package com.tecnolpet.ot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tecnolpet.ot.model.Sucursal;
import com.tecnolpet.ot.model.Telerenovador;

public interface TelerenovadorRepository extends JpaRepository<Telerenovador, Integer>{
	Telerenovador findByUsuarioAndSucursal(Integer user,Sucursal sucursal);
	
	List<Telerenovador> findBySucursal(Sucursal sucursal);
}
