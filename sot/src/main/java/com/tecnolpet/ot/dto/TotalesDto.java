package com.tecnolpet.ot.dto;

import java.io.Serializable;

public class TotalesDto implements Serializable{
	
	private static final long serialVersionUID = 6914044372885404582L;

	private double subtotal;
	
	private double descuento;
	
	private double impuesto_0;
	
	private double impuesto;
	
	private double total;

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

	public double getImpuesto_0() {
		return impuesto_0;
	}

	public void setImpuesto_0(double impuesto_0) {
		this.impuesto_0 = impuesto_0;
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
