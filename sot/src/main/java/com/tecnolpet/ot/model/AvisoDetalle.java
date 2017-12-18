package com.tecnolpet.ot.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;


/**
 * The persistent class for the aviso_detalle database table.
 * 
 */
@Entity
@Table(name="aviso_detalle")
@NamedQuery(name="AvisoDetalle.findAll", query="SELECT a FROM AvisoDetalle a")
public class AvisoDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codigo")
	@JsonView(ViewOT.PublicView.class)
	private Integer id;

	//bi-directional many-to-one association to Aviso
	@ManyToOne
	@JoinColumn(name="codigo_aviso", referencedColumnName="codigo")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)
	@JsonView(ViewOT.PublicView.class)
	private Aviso aviso;

	//bi-directional many-to-one association to Suscripcion
	@ManyToOne
	@JoinColumn(name="codigo_suscripcion", referencedColumnName="codigo")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)	
	@JsonView(ViewOT.PublicView.class)
	private Suscripcion suscripcion;

	public AvisoDetalle() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Aviso getAviso() {
		return this.aviso;
	}

	public void setAviso(Aviso aviso) {
		this.aviso = aviso;
	}

	public Suscripcion getSuscripcion() {
		return this.suscripcion;
	}

	public void setSuscripcion(Suscripcion suscripcion) {
		this.suscripcion = suscripcion;
	}

}