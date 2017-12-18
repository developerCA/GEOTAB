package com.tecnolpet.ot.dto;

import java.io.Serializable;
import java.util.List;

public class RenovacionPendienteDto implements Serializable{

	private static final long serialVersionUID = -2833983735314597131L;
	
	private List<ProductoAcessosDto> productoAccesos;
	
	private double subtotal;
	
	private double descuento;
	
	private double impuesto0;
	
	private double impuesto;
	
	private double total;

	public List<ProductoAcessosDto> getProductoAccesos() {
		return productoAccesos;
	}

	public void setProductoAccesos(List<ProductoAcessosDto> productoAccesos) {
		this.productoAccesos = productoAccesos;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public double getImpuesto0() {
		return impuesto0;
	}

	public void setImpuesto0(double impuesto0) {
		this.impuesto0 = impuesto0;
	}

	public double getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(double impuesto) {
		this.impuesto = impuesto;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
