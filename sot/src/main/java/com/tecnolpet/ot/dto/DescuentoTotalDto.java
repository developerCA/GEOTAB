package com.tecnolpet.ot.dto;

import java.io.Serializable;

public class DescuentoTotalDto implements Serializable{

	private static final long serialVersionUID = -1410222286405404367L;

	private double valor;
	
	private int totem;

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getTotem() {
		return totem;
	}

	public void setTotem(int totem) {
		this.totem = totem;
	}
	
	
}
