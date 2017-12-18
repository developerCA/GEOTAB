/**
 * 
 */
package com.tecnolpet.ot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tecnolpet.ot.model.Catalogo;
import com.tecnolpet.ot.model.Cliente;
import com.tecnolpet.ot.model.Producto;

/**
 * @author administrador
 *
 */
public interface ProductoRepository extends JpaRepository<Producto, Integer>{
	List<Producto> findProductoByCatalogoAndCliente(Catalogo catalogo,Cliente cliente);
	
	@Query(value= "SELECT p.* FROM Producto p WHERE p.aplica_accesos = true AND p.codigo NOT IN (SELECT q.codigo FROM Producto q WHERE q.aplica_accesos = true AND q.codigo_producto_renovacion = q.codigo) and p.codigo_categoria= :categoria and p.codigo <> :producto",nativeQuery=true)
	List<Producto> findByAplicaAccesosTrue(@Param("categoria") Integer categoria,@Param("producto") Integer producto);
	
	@Query(value="SELECT p.* FROM Producto p WHERE p.renovable = true AND p.codigo NOT IN (SELECT q.codigo FROM Producto q WHERE q.renovable = true AND q.codigo_producto_renovacion = q.codigo) AND p.actualizable = true", nativeQuery=true)
	public List<Producto> findByActualizables();
	
}
