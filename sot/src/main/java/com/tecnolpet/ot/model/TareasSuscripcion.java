package com.tecnolpet.ot.model;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;

import java.sql.Timestamp;


/**
 * The persistent class for the tareas_suscripcion database table.
 * 
 */
@Entity
@Table(name="tareas_suscripcion")
@NamedQuery(name="TareasSuscripcion.findAll", query="SELECT t FROM TareasSuscripcion t")
public class TareasSuscripcion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codigo")
	@JsonView(ViewOT.PublicView.class)
	private Integer id;

	@Column(name="fecha_procesamiento")
	@JsonView(ViewOT.PublicView.class)
	private Timestamp fechaProcesamiento;

	@Column(name="nro_suscripciones_desactivadas")
	@JsonView(ViewOT.PublicView.class)
	private Integer nroSuscripcionesDesactivadas;

	@Column(name="nro_suscripciones_procesadas")
	@JsonView(ViewOT.PublicView.class)
	private Integer nroSuscripcionesProcesadas;

	@Column(name="nro_suscripciones_renovadas")
	@JsonView(ViewOT.PublicView.class)
	private Integer nroSuscripcionesRenovadas;

	public TareasSuscripcion() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getFechaProcesamiento() {
		return this.fechaProcesamiento;
	}

	public void setFechaProcesamiento(Timestamp fechaProcesamiento) {
		this.fechaProcesamiento = fechaProcesamiento;
	}

	public Integer getNroSuscripcionesDesactivadas() {
		return this.nroSuscripcionesDesactivadas;
	}

	public void setNroSuscripcionesDesactivadas(Integer nroSuscripcionesDesactivadas) {
		this.nroSuscripcionesDesactivadas = nroSuscripcionesDesactivadas;
	}

	public Integer getNroSuscripcionesProcesadas() {
		return this.nroSuscripcionesProcesadas;
	}

	public void setNroSuscripcionesProcesadas(Integer nroSuscripcionesProcesadas) {
		this.nroSuscripcionesProcesadas = nroSuscripcionesProcesadas;
	}

	public Integer getNroSuscripcionesRenovadas() {
		return this.nroSuscripcionesRenovadas;
	}

	public void setNroSuscripcionesRenovadas(Integer nroSuscripcionesRenovadas) {
		this.nroSuscripcionesRenovadas = nroSuscripcionesRenovadas;
	}

}