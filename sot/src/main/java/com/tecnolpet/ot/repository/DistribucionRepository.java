package com.tecnolpet.ot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tecnolpet.ot.model.Catalogo;
import com.tecnolpet.ot.model.Cliente;
import com.tecnolpet.ot.model.Distribucion;
import com.tecnolpet.ot.model.FechasRenovacion;
import com.tecnolpet.ot.model.SubCategoria;
import com.tecnolpet.ot.model.Sucursal;
import com.tecnolpet.ot.model.Telerenovador;

public interface DistribucionRepository extends JpaRepository<Distribucion , Integer>{

	@Query("Select d from Distribucion d Where d.sucursal= :sucursal and d.fechaRenovacion= :fechaRenovacion and d.subCategoria= :subcategoria and d.estado= :estado  order by d.cliente.nombres ASC ")
	public List<Distribucion> findDistribucion(@Param("sucursal") Sucursal sucursal,@Param("fechaRenovacion") FechasRenovacion fechaRenovacion,@Param("subcategoria") SubCategoria subcategoria,@Param("estado") Catalogo estado);

	
	@Query("Select d from Distribucion d Where d.sucursal= :sucursal and d.fechaRenovacion= :fechaRenovacion  and d.estado= :estado  and d.telerenovador= :telerenovador and d.cliente= :cliente order by d.cliente.nombres ASC ")
	public List<Distribucion> findDistribucionTelerenovador(@Param("sucursal") Sucursal sucursal,@Param("fechaRenovacion") FechasRenovacion fechaRenovacion,@Param("estado") Catalogo estado,@Param("telerenovador") Telerenovador telerenovador,@Param("cliente") Cliente cliente);
	
	@Query("Select DISTINCT(d.cliente) from Distribucion d Where d.sucursal= :sucursal and d.fechaRenovacion= :fechaRenovacion  and d.estado= :estado  and d.telerenovador= :telerenovador order by d.cliente.nombres ASC ")
	public List<Cliente> findDistribucionTelerenovadorCliente(@Param("sucursal") Sucursal sucursal,@Param("fechaRenovacion") FechasRenovacion fechaRenovacion,@Param("estado") Catalogo estado,@Param("telerenovador") Telerenovador telerenovador);
	

}
