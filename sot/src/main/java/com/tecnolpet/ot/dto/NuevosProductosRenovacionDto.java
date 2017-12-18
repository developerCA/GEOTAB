package com.tecnolpet.ot.dto;

import java.io.Serializable;

import com.tecnolpet.ot.model.Producto;

public class NuevosProductosRenovacionDto implements Serializable{

	private static final long serialVersionUID = 7842012893338065428L;
	
	private Integer aviso;
	
	private Producto producto;
	
	private Integer cantidad;
	
	private double descuento;
	
	private Integer cantidadAccesos;

	public Integer getAviso() {
		return aviso;
	}

	public void setAviso(Integer aviso) {
		this.aviso = aviso;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public Integer getCantidadAccesos() {
		return cantidadAccesos;
	}

	public void setCantidadAccesos(Integer cantidadAccesos) {
		this.cantidadAccesos = cantidadAccesos;
	}

}
