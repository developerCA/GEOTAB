package com.tecnolpet.ot.dto;

import java.io.Serializable;

import com.tecnolpet.ot.model.Cliente;

public class ClienteSuscripcionDto  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4035636698898399348L;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Integer getNumeroSuscripciones() {
		return numeroSuscripciones;
	}

	public void setNumeroSuscripciones(Integer numeroSuscripciones) {
		this.numeroSuscripciones = numeroSuscripciones;
	}

	private Cliente cliente;
	
	private Integer numeroSuscripciones;
	
	
}
