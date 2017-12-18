package com.tecnolpet.ot.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;

public class RespuestaDto implements Serializable {

	/**
	  * 
	  */
	private static final long serialVersionUID = 1L;
	@JsonView(ViewOT.PublicView.class)
	private String mensaje;
	@JsonView(ViewOT.PublicView.class)
	private boolean estado;
	@JsonView(ViewOT.PublicView.class)
	private Object objeto;

	public RespuestaDto() {
	}

	public RespuestaDto(String mensaje, Boolean estado, Object objeto) {
		this.mensaje = mensaje;
		this.estado = estado;
		this.objeto = objeto;

	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Object getObjeto() {
		return objeto;
	}

	public void setObjeto(Object objeto) {
		this.objeto = objeto;
	}
}