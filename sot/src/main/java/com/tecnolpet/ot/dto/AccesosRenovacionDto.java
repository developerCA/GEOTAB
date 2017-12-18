package com.tecnolpet.ot.dto;

import java.io.Serializable;
import java.util.List;

import com.tecnolpet.ot.model.AccesosRenovacion;

public class AccesosRenovacionDto implements Serializable{
	

	private static final long serialVersionUID = -5882387062934963360L;
	
	private List<AccesosRenovacion> accesos;
	private Integer cantidad;
	
	public List<AccesosRenovacion> getAccesos() {
		return accesos;
	}
	public void setAccesos(List<AccesosRenovacion> accesos) {
		this.accesos = accesos;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
}
