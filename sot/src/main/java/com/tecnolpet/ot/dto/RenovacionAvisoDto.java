package com.tecnolpet.ot.dto;

import java.io.Serializable;
import java.util.List;

import com.tecnolpet.ot.model.Renovacion;

public class RenovacionAvisoDto implements Serializable{

	private static final long serialVersionUID = -3960690105971528689L;
	
	private Renovacion renovacion;
	
	
	private List<DetalleRenovacionDto> detalleRenovacionDtoList;
	
	public Renovacion getRenovacion() {
		return renovacion;
	}
	
	public void setRenovacion(Renovacion renovacion) {
		this.renovacion = renovacion;
	}
	public List<DetalleRenovacionDto> getDetalleRenovacionDtoList() {
		return detalleRenovacionDtoList;
	}
	public void setDetalleRenovacionDtoList(
			List<DetalleRenovacionDto> detalleRenovacionDtoList) {
		this.detalleRenovacionDtoList = detalleRenovacionDtoList;
	}

}
