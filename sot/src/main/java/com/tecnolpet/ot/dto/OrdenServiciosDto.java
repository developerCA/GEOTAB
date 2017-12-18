package com.tecnolpet.ot.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;
import com.tecnolpet.ot.model.TareaDetalleNotaPedido;

public class OrdenServiciosDto {
	@JsonView(ViewOT.PublicView.class)
	private List<TareaDetalleNotaPedido> listaServicios=new ArrayList<TareaDetalleNotaPedido>();

	public List<TareaDetalleNotaPedido> getListaServicios() {
		return listaServicios;
	}

	public void setListaServicios(List<TareaDetalleNotaPedido> listaServicios) {
		this.listaServicios = listaServicios;
	}

	
}
