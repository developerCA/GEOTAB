package com.tecnolpet.ot.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.tecnolpet.ot.model.AccesosRenovacion;
import com.tecnolpet.ot.model.RenovacionDetalle;

public class RenovacionDetalleDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7963481252360591609L;

	private RenovacionDetalle renovacionDetalle;
	
	private List<AccesosRenovacion> accesosRenovacion=new ArrayList<AccesosRenovacion>();

	public RenovacionDetalle getRenovacionDetalle() {
		return renovacionDetalle;
	}

	public List<AccesosRenovacion> getAccesosRenovacion() {
		return accesosRenovacion;
	}

	public void setRenovacionDetalle(RenovacionDetalle renovacionDetalle) {
		this.renovacionDetalle = renovacionDetalle;
	}

	public void setAccesosRenovacion(List<AccesosRenovacion> accesosRenovacion) {
		this.accesosRenovacion = accesosRenovacion;
	}
	
	
}
