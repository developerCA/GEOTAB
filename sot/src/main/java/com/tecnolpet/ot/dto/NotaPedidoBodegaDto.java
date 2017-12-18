package com.tecnolpet.ot.dto;

import java.io.Serializable;

import com.tecnolpet.ot.model.NotaPedido;

public class NotaPedidoBodegaDto implements Serializable{

	private static final long serialVersionUID = -4097782980610412331L;
	
	private NotaPedido notaPedido;
	
	private Integer bodegaId;


	public NotaPedido getNotaPedido() {
		return notaPedido;
	}


	public void setNotaPedido(NotaPedido notaPedido) {
		this.notaPedido = notaPedido;
	}


	public Integer getBodegaId() {
		return bodegaId;
	}


	public void setBodegaId(Integer bodegaId) {
		this.bodegaId = bodegaId;
	}

}
