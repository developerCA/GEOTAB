package com.tecnolpet.ot.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.tecnolpet.ot.model.SubCategoria;

public class LineaProductoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8239294788771335635L;
	private SubCategoria subcategoria;
	private List<ClienteAvisoDto> clientes=new ArrayList<ClienteAvisoDto>();
	
	
	public SubCategoria getSubcategoria() {
		return subcategoria;
	}
	public void setSubcategoria(SubCategoria subcategoria) {
		this.subcategoria = subcategoria;
	}
	public List<ClienteAvisoDto> getClientes() {
		return clientes;
	}
	public void setClientes(List<ClienteAvisoDto> clientes) {
		this.clientes = clientes;
	}
	
	
	
}
