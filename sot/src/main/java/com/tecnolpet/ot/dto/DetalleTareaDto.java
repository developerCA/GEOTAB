package com.tecnolpet.ot.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;
import com.tecnolpet.ot.model.DetalleNotaPedido;
import com.tecnolpet.ot.model.TareaDetalleNotaPedido;

public class DetalleTareaDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5105493817947536792L;

	@JsonView(ViewOT.PublicView.class)
	private DetalleNotaPedido detalleNotapedido;
	@JsonView(ViewOT.PublicView.class)
	private List<TareaDetalleNotaPedido> listaTareas=new ArrayList<TareaDetalleNotaPedido>();

	public DetalleNotaPedido getDetalleNotapedido() {
		return detalleNotapedido;
	}

	public void setDetalleNotapedido(DetalleNotaPedido detalleNotapedido) {
		this.detalleNotapedido = detalleNotapedido;
	}

	public List<TareaDetalleNotaPedido> getListaTareas() {
		return listaTareas;
	}

	public void setListaTareas(List<TareaDetalleNotaPedido> listaTareas) {
		this.listaTareas = listaTareas;
	}
	
	
}
