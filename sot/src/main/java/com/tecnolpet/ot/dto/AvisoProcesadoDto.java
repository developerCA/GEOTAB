package com.tecnolpet.ot.dto;

import java.io.Serializable;

public class AvisoProcesadoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1188641959570742865L;
	private Integer aviso;
	private boolean enviado;
	private String observacion;
	
	public Integer getAviso() {
		return aviso;
	}
	public void setAviso(Integer aviso) {
		this.aviso = aviso;
	}
	public boolean getEnviado() {
		return enviado;
	}
	public void setEnviado(boolean enviado) {
		this.enviado = enviado;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
		
}
