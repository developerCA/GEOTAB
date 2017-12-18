package com.tecnolpet.ot.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;
import com.tecnolpet.ot.model.NotaPedido;
import com.tecnolpet.ot.model.TareaDetalleNotaPedido;

public class OrdenOTDto {

	@JsonView(ViewOT.PublicView.class)
	private TareaDetalleNotaPedido servicio;
	@JsonView(ViewOT.PublicView.class)
	private NotaPedido ordenCompleta;
	
	
	public TareaDetalleNotaPedido getServicio() {
		return servicio;
	}

	public void setServicio(TareaDetalleNotaPedido servicio) {
		this.servicio = servicio;
	}

	public NotaPedido getOrdenCompleta() {
		return ordenCompleta;
	}

	public void setOrdenCompleta(NotaPedido ordenCompleta) {
		this.ordenCompleta = ordenCompleta;
	}

	

	

}
