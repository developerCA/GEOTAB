package com.tecnolpet.ot.dto;

import java.io.Serializable;
import java.util.List;

public class GestionComercialDto implements Serializable{

	private static final long serialVersionUID = 949593956595010066L;
	
	private GestionDetalleDto suscripcionesQueSeMantienen;
	
	private List<SuscripcionesAccesosWrapperDto> accesosCambioProducto;
	
	private Integer clienteId;
	
	private List<NuevasRenovacionesDto> nuevas;

	public GestionDetalleDto getSuscripcionesQueSeMantienen() {
		return suscripcionesQueSeMantienen;
	}

	public void setSuscripcionesQueSeMantienen(GestionDetalleDto suscripcionesQueSeMantienen) {
		this.suscripcionesQueSeMantienen = suscripcionesQueSeMantienen;
	}

	public List<SuscripcionesAccesosWrapperDto> getAccesosCambioProducto() {
		return accesosCambioProducto;
	}

	public void setAccesosCambioProducto(List<SuscripcionesAccesosWrapperDto> accesosCambioProducto) {
		this.accesosCambioProducto = accesosCambioProducto;
	}

	public Integer getClienteId() {
		return clienteId;
	}

	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
	}

	public List<NuevasRenovacionesDto> getNuevas() {
		return nuevas;
	}

	public void setNuevas(List<NuevasRenovacionesDto> nuevas) {
		this.nuevas = nuevas;
	}

}
