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
import com.tecnolpet.ot.model.Empresa;
import com.tecnolpet.ot.model.Enlace;
import com.tecnolpet.ot.model.Regional;
import com.tecnolpet.ot.model.Sucursal;


/**
 * @author administrador
 *
 */
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	@Query("SELECT c FROM Cliente c WHERE c.identificacion like :identificacion AND (c.nombres like :nombres OR c.apellidos like :apellidos) And c.empresa =:empresa")
	List<Cliente> findClienteAtributos(@Param("identificacion") String identificacion, @Param("nombres") String nombres, @Param("apellidos") String apellidos,@Param("empresa") Empresa empresa);
	
	List<Cliente> findByCatalogoAndEmpresa(Catalogo catalogo,Empresa empresa);
	
	@Query("SELECT e FROM Enlace e WHERE e.cliente= :cliente order by e.apellidos")
	public List<Enlace> findEnlacesByCliente(@Param("cliente") Cliente cliente);
	
	List<Cliente>  findBySucursalAndIdentificacionAndRegional(Sucursal sucursal,String identificacion,Regional regional);
	
	@Query(value="SELECT c.* FROM Cliente c WHERE c.nombres LIKE :term% OR c.identificacion LIKE :term%", nativeQuery=true)
	public List<Cliente> findByTerm(@Param("term") String term);
}
