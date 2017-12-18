package com.tecnolpet.ot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tecnolpet.ot.model.Catalogo;
import com.tecnolpet.ot.model.Empresa;
import com.tecnolpet.ot.model.Instrumento;

public interface InstrumentoRepository extends JpaRepository<Instrumento, Integer>{



	List<Instrumento> findByEmpresaAndCatalogo(Empresa emrpesa,Catalogo catalogo);
}
