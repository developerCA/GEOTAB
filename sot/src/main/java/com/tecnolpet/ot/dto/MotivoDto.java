package com.tecnolpet.ot.dto;

import java.io.Serializable;

public class MotivoDto implements Serializable{

	private static final long serialVersionUID = 7571812072374714733L;
	
	private Integer id;
	private String motivo;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
	

}
