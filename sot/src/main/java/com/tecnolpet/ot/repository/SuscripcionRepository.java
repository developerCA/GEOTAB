package com.tecnolpet.ot.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tecnolpet.ot.model.Catalogo;
import com.tecnolpet.ot.model.Cliente;
import com.tecnolpet.ot.model.Enlace;
import com.tecnolpet.ot.model.NotaPedido;
import com.tecnolpet.ot.model.SubCategoria;
import com.tecnolpet.ot.model.Sucursal;
import com.tecnolpet.ot.model.Suscripcion;

/**
 * 
 * @author Armando Ariel Suárez Pons
 *
 */

public interface SuscripcionRepository extends JpaRepository<Suscripcion, Integer>{
	
	@Query("SELECT DISTINCT(s.cliente) FROM Suscripcion s WHERE s.catalogo= :activo AND s.fechaVencimiento between :fechaInicio AND :fechaFin AND s.notaPedido.sucursal.id = :sucursalId")
	List<Cliente> findClientesByfechaVencimientoBetween(@Param("activo") Catalogo activo, @Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin, @Param("sucursalId") Integer sucursal);
	
	@Query("SELECT DISTINCT(s.cliente) FROM Suscripcion s WHERE s.catalogo= :activo AND s.fechaVencimiento between :fechaInicio AND :fechaFin AND s.sucursal = :sucursal and s.producto.categoria.subCategoria= :subcategoria")
	List<Cliente> findClientesByfechaVencimientoBetweenLinea(@Param("activo") Catalogo activo, @Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin, @Param("sucursal") Sucursal sucursal,@Param("subcategoria") SubCategoria subcategoria);
	
	@Query("SELECT DISTINCT(s.enlace) FROM Suscripcion s WHERE s.catalogo= :activo AND s.fechaVencimiento between :fechaInicio AND :fechaFin AND s.sucursal = :sucursal and s.producto.categoria.subCategoria= :subcategoria and s.cliente= :cliente")
	List<Enlace> findEnlacesByfechaVencimientoBetweenLinea(@Param("activo") Catalogo activo, @Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin, @Param("sucursal") Sucursal sucursal,@Param("subcategoria") SubCategoria subcategoria,@Param("cliente") Cliente cliente);
	
	@Query("SELECT s FROM Suscripcion s WHERE s.catalogo= :activo AND s.fechaVencimiento between :fechaInicio AND :fechaFin AND s.sucursal = :sucursal and s.producto.categoria.subCategoria= :subcategoria and s.cliente= :cliente and s.enlace= :enlace")
	List<Suscripcion> findSuscripcionesByfechaVencimientoBetweenLinea(@Param("activo") Catalogo activo, @Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin, @Param("sucursal") Sucursal sucursal,@Param("subcategoria") SubCategoria subcategoria,@Param("cliente") Cliente cliente,@Param("enlace") Enlace enlace);
	
	@Query("SELECT s FROM Suscripcion s WHERE s.catalogo= :activo AND s.fechaVencimiento between :fechaInicio AND :fechaFin AND s.cliente = :cliente")
	List<Suscripcion> findByfechaVencimientoBetweenAndCliente(@Param("activo") Catalogo activo, @Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin, @Param("cliente") Cliente cliente);
	
	@Query("SELECT s FROM Suscripcion s WHERE s.notaPedido.sucursal.id = :sucursalId AND s.catalogo= :activo AND s.fechaVencimiento between :fechaInicio AND :fechaFin")
	List<Suscripcion> findByfechaVencimientoBetween(@Param("sucursalId") Integer sucursal, @Param("activo") Catalogo activo, @Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);
	
	public List<Suscripcion> findByNotaPedido(NotaPedido notapedido);
	
	@Query("SELECT distinct(s.cliente) FROM Suscripcion s WHERE s.catalogo= :activo ")
	 public List<Cliente> findClientesActivos(@Param("activo") Catalogo activo);
	
	@Query("SELECT count(s.id) FROM Suscripcion s WHERE s.catalogo= :activo and cliente= :cliente ")
	public Integer findNumeroSuscripciones(@Param("activo") Catalogo activo,@Param("cliente") Cliente cliente);
	
	@Query("SELECT s FROM Suscripcion s WHERE s.catalogo= :activo and cliente= :cliente order by s.fechaInicio,s.id ")
	public List<Suscripcion> findSuscripcionesByCliente(@Param("activo") Catalogo activo,@Param("cliente") Cliente cliente);
	
	@Query("SELECT DISTINCT(a.suscripcion) FROM Acceso a WHERE a.catalogoEstado = :estado AND a.activo = false")
	public List<Suscripcion> findByAcceso(@Param("estado") Catalogo catalogo);
	
	public List<Suscripcion> findByCatalogoAndFechaVencimiento(Catalogo estado, Date fechaVencimiento);
	@Query("SELECT COUNT(*) FROM Suscripcion  where estado = :estado")
	public Integer cantidadSuscripciones(@Param("estado") Catalogo estado);

	@Query("SELECT COUNT(*) FROM NotaPedido  where estado = :estado and codigo_sucursal =:sucursal")
	public Integer cantidadNotaPedido(@Param("estado") Catalogo estado,
			@Param("sucursal") Integer sucursal);

	@Query("SELECT COUNT(*) FROM NotaPedido  where estado = :estado")
	public Integer cantidadFacturasEnviar(@Param("estado") Catalogo estado);

	@Query("SELECT COUNT(*) FROM NotaPedido  where estado = :estado")
	public Integer cantNotaPedidoEnviadas(@Param("estado") Catalogo estado);

	@Query("select s from Suscripcion s where s.fechaInicio BETWEEN :fechaInicio AND :fechaFin")
	public List<Suscripcion> ventasAnuales(@Param("fechaInicio") Date fechaI, @Param("fechaFin") Date fechaF);
	
	public Suscripcion findByCatalogoAndSuscripcionInicial(Catalogo estado, Integer suscripcion);
	
	@Query("SELECT s FROM Suscripcion s WHERE s.fechaVencimiento = :fecha AND s.catalogo != :estado")
	public List<Suscripcion> findByFechaVencimientoAndCatalogo(@Param("fecha") Date fechaVencimiento, @Param("estado") Catalogo estado);
	
	@Query("SELECT s FROM Suscripcion s WHERE s.fechaVencimiento = :fecha AND s.tipoOperacion.id = :tipoOperacion AND s.catalogo != :estado AND s.suscripcionInicial = :suscripcionInicial")
	public Suscripcion findByFechaVencimientoAndCatalogoAndSuscripcionInicial(@Param("fecha") Date fechaVencimiento, @Param("tipoOperacion")Integer tipoOperacion , @Param("estado") Catalogo estado, @Param("suscripcionInicial") Integer sInicial);
	
	@Query("SELECT s FROM Suscripcion s WHERE s.catalogo = :estado AND s.notaPedido.sucursal = :sucursal AND s.notaPedido.pagado = TRUE")
	public List<Suscripcion> findByCatalogoAndSucursal(@Param("estado") Catalogo estado, @Param("sucursal") Sucursal sucursal);
	
	@Query(value="SELECT s.* FROM Suscripcion s WHERE s.codigo_producto IN (:producto, :renovacion) and s.estado = :activaDefinitiva", nativeQuery=true)
	public List<Suscripcion> findByProductoActualizable(@Param("producto") Integer idProducto, @Param("renovacion") Integer idRenovacion, @Param("activaDefinitiva") Integer idEstado);
	
	public List<Suscripcion> findByCatalogo(Catalogo estado);
	
	public List<Suscripcion> findByCatalogoAndCliente(Catalogo estado, Cliente cliente);
	
	public List<Suscripcion> findBySuscripcionInicialAndSucursalOrderByFechaVencimientoDesc(Integer suscripcionInicial, Sucursal sucursal);
	
}
