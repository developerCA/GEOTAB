package com.tecnolpet.ot.dto;

import java.io.Serializable;
import java.util.List;

import com.tecnolpet.ot.model.AccesosRenovacion;
import com.tecnolpet.ot.model.RenovacionDetalle;
import com.tecnolpet.ot.model.Suscripcion;

public class DetalleRenovacionDto implements Serializable{

	private static final long serialVersionUID = 6698826289118382096L;
	
	private RenovacionDetalle detalleRenovacion;
	
	private Suscripcion suscripcion;
	
	private List<AccesosRenovacion> accesoRenovacionList;
	
	private Boolean check;

	public RenovacionDetalle getDetalleRenovacion() {
		return detalleRenovacion;
	}

	public void setDetalleRenovacion(RenovacionDetalle detalleRenovacion) {
		this.detalleRenovacion = detalleRenovacion;
	}

	public List<AccesosRenovacion> getAccesoRenovacionList() {
		return accesoRenovacionList;
	}

	public void setAccesoRenovacionList(List<AccesosRenovacion> accesoRenovacionList) {
		this.accesoRenovacionList = accesoRenovacionList;
	}

	public Suscripcion getSuscripcion() {
		return suscripcion;
	}

	public void setSuscripcion(Suscripcion suscripcion) {
		this.suscripcion = suscripcion;
	}

	public Boolean getCheck() {
		return check;
	}

	public void setCheck(Boolean check) {
		this.check = check;
	}

}
