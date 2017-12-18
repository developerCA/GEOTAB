/**
 * 
 */
package com.tecnolpet.ot.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author administrador
 *
 */
public class AvisoFechasDto implements Serializable{
	
	
	private static final long serialVersionUID = 9113573175374206403L;
	
	private Date fechaInicio;
	private Date fechaFin;
	
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
}
