package com.tecnolpet.ot.model;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;


/**
 * The persistent class for the detalles_tareas_suscripciones database table.
 * 
 */
@Entity
@Table(name="detalles_tareas_suscripciones")
@NamedQuery(name="DetallesTareasSuscripciones.findAll", query="SELECT d FROM DetallesTareasSuscripciones d")
public class DetallesTareasSuscripciones implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codigo")
	@JsonView(ViewOT.PublicView.class)
	private Integer id;

	//bi-directional many-to-one association to Catalogo
	@ManyToOne
	@JoinColumn(name="estado")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)		
	@JsonView(ViewOT.PublicView.class)
	private Catalogo catalogo;

	//bi-directional many-to-one association to Suscripcion
	@ManyToOne
	@JoinColumn(name="codigo_suscripcion")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)		
	@JsonView(ViewOT.PublicView.class)
	private Suscripcion suscripcion;
	
	@ManyToOne
	@JoinColumn(name="codigo_tarea_suscripcion")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)	
	@JsonView(ViewOT.PublicView.class)
	private TareasSuscripcion tareasSuscripcion;

	public DetallesTareasSuscripciones() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Catalogo getCatalogo() {
		return this.catalogo;
	}

	public void setCatalogo(Catalogo catalogo) {
		this.catalogo = catalogo;
	}

	public Suscripcion getSuscripcion() {
		return this.suscripcion;
	}

	public void setSuscripcion(Suscripcion suscripcion) {
		this.suscripcion = suscripcion;
	}

	public TareasSuscripcion getTareasSuscripcion() {
		return tareasSuscripcion;
	}

	public void setTareasSuscripcion(TareasSuscripcion tareasSuscripcion) {
		this.tareasSuscripcion = tareasSuscripcion;
	}

}