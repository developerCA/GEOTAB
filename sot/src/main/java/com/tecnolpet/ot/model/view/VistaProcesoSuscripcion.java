package com.tecnolpet.ot.model.view;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the vw_proceso_suscripcion database table.
 * 
 */
@Entity
@Table(name = "vw_proceso_suscripcion")
@NamedQuery(name = "VistaProcesoSuscripcion.findAll", query = "SELECT v FROM VistaProcesoSuscripcion v")
public class VistaProcesoSuscripcion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long cantidad;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Id
	private Long id;

	private String tipo;

	public VistaProcesoSuscripcion() {
	}

	public Long getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}