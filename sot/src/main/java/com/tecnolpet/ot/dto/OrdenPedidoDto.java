package com.tecnolpet.ot.dto;

import java.io.Serializable;
import java.util.List;

import com.tecnolpet.ot.model.DetalleNotaPedido;

public class OrdenPedidoDto implements Serializable{

	private static final long serialVersionUID = 1468262219796726629L;
	
	private List<DetalleNotaPedido> detalle;

	private TotalesDto totales;
	
	private Integer bodega;
	

	public List<DetalleNotaPedido> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<DetalleNotaPedido> detalle) {
		this.detalle = detalle;
	}

	public TotalesDto getTotales() {
		return totales;
	}

	public void setTotales(TotalesDto totales) {
		this.totales = totales;
	}

	public Integer getBodega() {
		return bodega;
	}

	public void setBodega(Integer bodega) {
		this.bodega = bodega;
	}
	
}
