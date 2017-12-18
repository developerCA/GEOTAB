/**
 * 
 */
package com.tecnolpet.ot.dto;

import java.io.Serializable;

/**
 * @author administrador
 *
 */
public class FiltrosDto implements Serializable{
	
	private static final long serialVersionUID = -622176951748117657L;

	private Integer rango;
	
	private String comercial;
	
	private Integer linea;

	public Integer getRango() {
		return rango;
	}

	public void setRango(Integer rango) {
		this.rango = rango;
	}

	public String getComercial() {
		return comercial;
	}

	public void setComercial(String comercial) {
		this.comercial = comercial;
	}

	public Integer getLinea() {
		return linea;
	}

	public void setLinea(Integer linea) {
		this.linea = linea;
	}
	
}
