package com.tecnolpet.ot.dto;

import java.io.Serializable;

import com.tecnolpet.ot.model.Aviso;

public class DetalleDistribucionDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Aviso aviso;
	private Integer cantidadSuscripciones;
	private double total;
	private Integer Telerenovador;
	
	public Aviso getAviso() {
		return aviso;
	}
	public void setAviso(Aviso aviso) {
		this.aviso = aviso;
	}
	public Integer getCantidadSuscripciones() {
		return cantidadSuscripciones;
	}
	public void setCantidadSuscripciones(Integer cantidadSuscripciones) {
		this.cantidadSuscripciones = cantidadSuscripciones;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Integer getTelerenovador() {
		return Telerenovador;
	}
	public void setTelerenovador(Integer telerenovador) {
		Telerenovador = telerenovador;
	}
	
}
