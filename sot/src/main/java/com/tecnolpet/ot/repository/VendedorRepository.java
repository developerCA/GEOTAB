package com.tecnolpet.ot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;





import com.tecnolpet.ot.model.Catalogo;
import com.tecnolpet.ot.model.Vendedor;

public interface VendedorRepository extends JpaRepository<Vendedor,Integer> {
	
	List<Vendedor> findByEstado(Catalogo catalogo);

}
