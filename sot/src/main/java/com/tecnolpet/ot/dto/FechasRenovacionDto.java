/**
 * 
 */
package com.tecnolpet.ot.dto;

import java.io.Serializable;

/**
 * @author administrador
 *
 */
public class FechasRenovacionDto implements Serializable{

	private static final long serialVersionUID = 4346339928262510638L;

	private Integer id;

	private String fechaFin;


	private String fechaInicio;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getFechaFin() {
		return fechaFin;
	}


	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}


	public String getFechaInicio() {
		return fechaInicio;
	}


	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
}
