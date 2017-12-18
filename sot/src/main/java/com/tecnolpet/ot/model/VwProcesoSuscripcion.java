package com.tecnolpet.ot.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the vw_proceso_suscripcion database table.
 * 
 */
@Entity
@Table(name="vw_proceso_suscripcion")
@NamedQuery(name="VwProcesoSuscripcion.findAll", query="SELECT v FROM VwProcesoSuscripcion v")
public class VwProcesoSuscripcion implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long cantidad;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Id
	private Integer id;

	private String tipo;

	public VwProcesoSuscripcion() {
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

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}