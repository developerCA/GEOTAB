package com.tecnolpet.ot.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;
import com.tecnolpet.ot.model.DetalleNotaPedido;
import com.tecnolpet.ot.model.NotaPedido;
import com.tecnolpet.ot.model.Suscripcion;

public class SuscripcionPedidoDto implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -377212419727140124L;
	
	@JsonView(ViewOT.PublicView.class)
	private Integer id;
	
	@JsonView(ViewOT.PublicView.class)
	private NotaPedido notaPedido;
	
	@JsonView(ViewOT.PublicView.class)
	private List<DetalleNotaPedido> detalles;
	
	@JsonView(ViewOT.PublicView.class)	
	private List<Suscripcion> suscripciones;
	
	@JsonView(ViewOT.PublicView.class)
	private List<DetalleTareaDto> detalleServicio;
	
	public NotaPedido getNotaPedido() {
		return notaPedido;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setNotaPedido(NotaPedido notaPedido) {
		this.notaPedido = notaPedido;
	}
	public List<DetalleNotaPedido> getDetalles() {
		return detalles;
	}
	public void setDetalles(List<DetalleNotaPedido> detalles) {
		this.detalles = detalles;
	}
	public List<Suscripcion> getSuscripciones() {
		return suscripciones;
	}
	public void setSuscripciones(List<Suscripcion> suscripciones) {
		this.suscripciones = suscripciones;
	}
	public List<DetalleTareaDto> getDetalleServicio() {
		return detalleServicio;
	}
	public void setDetalleServicio(List<DetalleTareaDto> detalleServicio) {
		this.detalleServicio = detalleServicio;
	}	
	
}
