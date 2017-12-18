package com.tecnolpet.ot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tecnolpet.ot.model.Catalogo;
import com.tecnolpet.ot.model.ProductoActualizacion;
import com.tecnolpet.ot.model.ProductoActualizacionDetalle;
import com.tecnolpet.ot.model.Suscripcion;

public interface ProductoActualizacionDetalleRepository extends JpaRepository<ProductoActualizacionDetalle, Integer> {
	
    @Query(value="select pad.* from producto_actualizacion_detalle pad, suscripcion s WHERE pad.codigo_producto_actualizacion = :productoActualizacion AND pad.codigo_suscripcion = s.codigo and s.estado = :estadoSuscripcion and (s.codigo_producto = :producto OR s.codigo_cliente = :cliente and pad.estado = :estado) ", nativeQuery=true)
    public List<ProductoActualizacionDetalle> findByProductoActualizacion(@Param("productoActualizacion") Integer productoActualizacion, @Param("estado") Integer estado, @Param("producto") Integer producto, @Param("cliente") Integer cliente, @Param("estadoSuscripcion") Integer estadoSuscripcion);

    public List<ProductoActualizacionDetalle> findByCodigoBarrasAndEstado(String codigoBarras, Catalogo estado);
    
    public List<ProductoActualizacionDetalle> findBySuscripcionAndVersionAndEstado(Suscripcion suscripcion, Integer version, Catalogo estado);
    
    public List<ProductoActualizacionDetalle> findByProductoActualizacionAndEstado(ProductoActualizacion productoActualizacion, Catalogo estado);
    
}
