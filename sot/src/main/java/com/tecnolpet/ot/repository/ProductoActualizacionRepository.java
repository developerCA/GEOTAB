package com.tecnolpet.ot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tecnolpet.ot.model.Catalogo;
import com.tecnolpet.ot.model.ProductoActualizacion;

public interface ProductoActualizacionRepository extends JpaRepository<ProductoActualizacion, Integer> {

	@Query("SELECT pa FROM ProductoActualizacion pa WHERE pa.estado = :estado")
	public List<ProductoActualizacion> findByEstado(@Param("estado") Catalogo estado);
	
	@Query("SELECT pad.productoActualizacion FROM ProductoActualizacionDetalle pad WHERE pad.codigoBarras = :codigoBarras AND pad.estado = :estado AND pad.productoActualizacion.estado = :estado AND pad.suscripcion.catalogo = :estadoSuscripcion")
	public List<ProductoActualizacion> findByCodigoBarras(@Param("codigoBarras") String codigoBarras, @Param("estado") Catalogo estado, @Param("estadoSuscripcion") Catalogo estadoSuscripcion);
	
	@Query("SELECT DISTINCT (pad.productoActualizacion) FROM ProductoActualizacionDetalle pad WHERE pad.estado = :estado AND pad.productoActualizacion.estado = :estado AND pad.suscripcion.producto.id = :producto OR pad.suscripcion.cliente.id = :cliente AND pad.suscripcion.catalogo = :estadoSuscripcion")
	public List<ProductoActualizacion> findGeneradasByCodigoClienteProducto(@Param("producto") Integer producto, @Param("cliente") Integer cliente, @Param("estado") Catalogo estado, @Param("estadoSuscripcion") Catalogo estadoSuscripcion);
	
}
