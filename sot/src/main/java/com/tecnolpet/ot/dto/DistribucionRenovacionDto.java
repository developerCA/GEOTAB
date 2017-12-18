package com.tecnolpet.ot.dto;

import java.io.Serializable;

import com.tecnolpet.ot.model.Distribucion;

public class DistribucionRenovacionDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3562208403915541873L;

	
	private Distribucion distribucion;
	
	private RenovacionDetalleDto renovacionDetalle;
	
	private Boolean seleccion;
	

	public Distribucion getDistribucion() {
		return distribucion;
	}

	public RenovacionDetalleDto getRenovacionDetalle() {
		return renovacionDetalle;
	}

	public void setDistribucion(Distribucion distribucion) {
		this.distribucion = distribucion;
	}

	public void setRenovacionDetalle(RenovacionDetalleDto renovacionDetalle) {
		this.renovacionDetalle = renovacionDetalle;
	}

	public Boolean getSeleccion() {
		return seleccion;
	}

	public void setSeleccion(Boolean seleccion) {
		this.seleccion = seleccion;
	}
	
	
}
