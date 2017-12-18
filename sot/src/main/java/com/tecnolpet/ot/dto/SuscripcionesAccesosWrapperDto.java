package com.tecnolpet.ot.dto;

import java.io.Serializable;
import java.util.List;

import com.tecnolpet.ot.model.AccesosRenovacion;
import com.tecnolpet.ot.model.Suscripcion;

public class SuscripcionesAccesosWrapperDto implements Serializable{

	private static final long serialVersionUID = 3645717293260240313L;
	
	private Suscripcion suscripcion;
	
	private List<AccesosRenovacion> accesosRenovacion;

	public Suscripcion getSuscripcion() {
		return suscripcion;
	}

	public void setSuscripcion(Suscripcion suscripcion) {
		this.suscripcion = suscripcion;
	}

	public List<AccesosRenovacion> getAccesosRenovacion() {
		return accesosRenovacion;
	}

	public void setAccesosRenovacion(List<AccesosRenovacion> accesosRenovacion) {
		this.accesosRenovacion = accesosRenovacion;
	}

}
