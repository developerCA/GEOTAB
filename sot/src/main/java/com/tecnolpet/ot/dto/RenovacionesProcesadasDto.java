/**
 * 
 */
package com.tecnolpet.ot.dto;

import java.io.Serializable;

import com.tecnolpet.ot.model.Renovacion;

/**
 * @author administrador
 *
 */
public class RenovacionesProcesadasDto implements Serializable{

	private static final long serialVersionUID = -8398531393155647815L;

	private Integer total;
	
	private Renovacion renovacion;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Renovacion getRenovacion() {
		return renovacion;
	}

	public void setRenovacion(Renovacion renovacion) {
		this.renovacion = renovacion;
	}
}
