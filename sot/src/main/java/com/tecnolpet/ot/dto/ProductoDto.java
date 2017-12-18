package com.tecnolpet.ot.dto;

import java.io.Serializable;

import com.tecnolpet.ot.model.Producto;

public class ProductoDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5674365563166260870L;
	
	private Producto producto;
	private Producto productoRenovacion;
	private String codigokohinor;
	
	public String getCodigokohinor() {
		return codigokohinor;
	}
	public void setCodigokohinor(String codigokohinor) {
		this.codigokohinor = codigokohinor;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public Producto getProductoRenovacion() {
		return productoRenovacion;
	}
	public void setProductoRenovacion(Producto productoRenovacion) {
		this.productoRenovacion = productoRenovacion;
	}
	
	

}
