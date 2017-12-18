/**
 * 
 */
package com.tecnolpet.ot.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.tecnolpet.ot.model.Distribucion;

public class DistribucionDto implements Serializable{
	
	private static final long serialVersionUID = -8082961813858292287L;
	
	private List<Distribucion> listaDistribucion=new ArrayList<Distribucion>();

	public List<Distribucion> getListaDistribucion() {
		return listaDistribucion;
	}

	public void setListaDistribucion(List<Distribucion> listaDistribucion) {
		this.listaDistribucion = listaDistribucion;
	}
	
	
		
}
