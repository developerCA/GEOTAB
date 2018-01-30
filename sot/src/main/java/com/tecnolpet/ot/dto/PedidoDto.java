package com.tecnolpet.ot.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;
import com.tecnolpet.ot.model.DetalleNotaPedido;
import com.tecnolpet.ot.model.NotaPedido;

public class PedidoDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6734720136207777014L;
	@JsonView(ViewOT.PublicView.class)
	private NotaPedido notaPedido;
	@JsonView(ViewOT.PublicView.class)
	private List<DetalleNotaPedido> detalles;
	
	@JsonView(ViewOT.PublicView.class)
	private DescuentoTotalDto descuento;
	@JsonView(ViewOT.PublicView.class)
	private double descuentoTotal;
	@JsonView(ViewOT.PublicView.class)
	private Integer totalServicios;
	
	public PedidoDto(){
		
	}
	public NotaPedido getNotaPedido() {
		return notaPedido;
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
	public DescuentoTotalDto getDescuento() {
		return descuento;
	}
	public void setDescuento(DescuentoTotalDto descuento) {
		this.descuento = descuento;
	}
	public double getDescuentoTotal() {
		return descuentoTotal;
	}
	public void setDescuentoTotal(double descuentoTotal) {
		this.descuentoTotal = descuentoTotal;
	}
	public Integer getTotalServicios() {
		return totalServicios;
	}
	public void setTotalServicios(Integer totalServicios) {
		this.totalServicios = totalServicios;
	}
	
	
}
