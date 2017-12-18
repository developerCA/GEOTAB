package com.tecnolpet.ot.dto;

import java.io.Serializable;

import com.tecnolpet.ot.model.Producto;

public class NuevasRenovacionesDto implements Serializable{

	private static final long serialVersionUID = -5584974822622218300L;
	
	private Integer aviso;
	private Integer cantidad;
	private boolean ocultar;
	private Producto producto;
	private Integer cantidadAccesos;
	private Integer descuento;
	
	public Integer getAviso() {
		return aviso;
	}
	public void setAviso(Integer aviso) {
		this.aviso = aviso;
	}	
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public boolean isOcultar() {
		return ocultar;
	}
	public void setOcultar(boolean ocultar) {
		this.ocultar = ocultar;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public Integer getCantidadAccesos() {
		return cantidadAccesos;
	}
	public void setCantidadAccesos(Integer cantidadAccesos) {
		this.cantidadAccesos = cantidadAccesos;
	}
	public Integer getDescuento() {
		return descuento;
	}
	public void setDescuento(Integer descuento) {
		this.descuento = descuento;
	}
	
}
