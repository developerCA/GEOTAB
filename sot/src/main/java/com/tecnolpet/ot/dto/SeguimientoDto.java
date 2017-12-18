package com.tecnolpet.ot.dto;

import java.io.Serializable;

public class SeguimientoDto implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3966701746255512919L;

	
	private Integer cantidad;
	
	private String observacion;

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	
}
