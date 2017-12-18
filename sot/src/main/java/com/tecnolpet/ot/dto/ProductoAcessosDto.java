package com.tecnolpet.ot.dto;

import java.io.Serializable;

import com.tecnolpet.ot.model.Producto;

public class ProductoAcessosDto implements Serializable{

	private static final long serialVersionUID = -7103887633148587713L;
	
	private Producto producto;
	
	private Integer acceso;
	
	private double subtotal;

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Integer getAcceso() {
		return acceso;
	}

	public void setAcceso(Integer acceso) {
		this.acceso = acceso;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

}
