package com.tecnolpet.ot.dto;

import java.io.Serializable;

import com.tecnolpet.ot.model.Aviso;

public class AvisoRecibidoDto implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Aviso aviso;
	
	private String nombreRecibe;
	private String fechaEntrega;
	
	public Aviso getAviso() {
		return aviso;
	}
	public void setAviso(Aviso aviso) {
		this.aviso = aviso;
	}
	public String getNombreRecibe() {
		return nombreRecibe;
	}
	public void setNombreRecibe(String nombreRecibe) {
		this.nombreRecibe = nombreRecibe;
	}
	public String getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(String fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	
}
