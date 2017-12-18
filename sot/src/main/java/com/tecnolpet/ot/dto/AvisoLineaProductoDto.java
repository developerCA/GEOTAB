package com.tecnolpet.ot.dto;

import java.io.Serializable;
import java.util.List;

import com.tecnolpet.ot.model.FechasRenovacion;

public class AvisoLineaProductoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2287922230518939151L;
	private List<LineaProductoDto> lineasProductos;
	private FechasRenovacion fechaRenovacion;
	private Boolean estado;
	private String mensaje;
	
	public List<LineaProductoDto> getLineasProductos() {
		return lineasProductos;
	}
	public void setLineasProductos(List<LineaProductoDto> lineasProductos) {
		this.lineasProductos = lineasProductos;
	}
	public FechasRenovacion getFechaRenovacion() {
		return fechaRenovacion;
	}
	public void setFechaRenovacion(FechasRenovacion fechaRenovacion) {
		this.fechaRenovacion = fechaRenovacion;
	}
	public Boolean getEstado() {
		return estado;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
