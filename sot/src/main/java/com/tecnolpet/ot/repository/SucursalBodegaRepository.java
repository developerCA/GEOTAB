package com.tecnolpet.ot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tecnolpet.ot.model.Bodega;
import com.tecnolpet.ot.model.Sucursal;
import com.tecnolpet.ot.model.SucursalBodega;

public interface SucursalBodegaRepository extends JpaRepository<SucursalBodega, Integer>{
	List<SucursalBodega> findByBodega(Bodega bodega);
	List<SucursalBodega> findByCodigoSucursal(Sucursal sucursal);
	List<SucursalBodega> findByCodigoSucursalAndBodega(Sucursal sucursal, Bodega bodega);
}
