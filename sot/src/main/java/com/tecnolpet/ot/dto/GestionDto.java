/**
 * 
 */
package com.tecnolpet.ot.dto;

import java.io.Serializable;

import com.tecnolpet.ot.model.FechasRenovacion;

/**
 * @author Armando
 *
 */
public class GestionDto implements Serializable{

	private static final long serialVersionUID = 5466273963002805115L;
	
	private Integer numeroClientes;
	private Integer numeroSuscripciones;
	private Integer numeroTelerenovador;
	private FechasRenovacion periodo;
	
	public Integer getNumeroClientes() {
		return numeroClientes;
	}
	public void setNumeroClientes(Integer numeroClientes) {
		this.numeroClientes = numeroClientes;
	}
	public Integer getNumeroSuscripciones() {
		return numeroSuscripciones;
	}
	public void setNumeroSuscripciones(Integer numeroSuscripciones) {
		this.numeroSuscripciones = numeroSuscripciones;
	}
	public Integer getNumeroTelerenovador() {
		return numeroTelerenovador;
	}
	public void setNumeroTelerenovador(Integer numeroTelerenovador) {
		this.numeroTelerenovador = numeroTelerenovador;
	}
	public FechasRenovacion getPeriodo() {
		return periodo;
	}
	public void setPeriodo(FechasRenovacion periodo) {
		this.periodo = periodo;
	}
		
}
