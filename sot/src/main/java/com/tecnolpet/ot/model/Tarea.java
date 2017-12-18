package com.tecnolpet.ot.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;


/**
 * The persistent class for the tarea database table.
 * 
 */
@Entity
public class Tarea implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codigo")
	@JsonView(ViewOT.PublicView.class)
	private Integer id;
	
	@JsonView(ViewOT.PublicView.class)
	private String nombre;

	@ManyToOne
	@JoinColumn(name="estado")
	@JsonView(ViewOT.PublicView.class)
	private Catalogo catalogo;
	
	@ManyToOne
	@JoinColumn(name = "codigo_empresa")
	@JsonView(ViewOT.PublicView.class)
	private Empresa empresa;

	
	
	@Transient
	@JsonView(ViewOT.PublicView.class)
	private String label;
	
	@Transient
	@JsonView(ViewOT.PublicView.class)
	private String alcance;
	
	@Transient
	@JsonView(ViewOT.PublicView.class)
	private Boolean seleccion;
	public Tarea() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	

	public String getLabel() {
		return this.nombre;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Catalogo getCatalogo() {
		return catalogo;
	}

	public void setCatalogo(Catalogo catalogo) {
		this.catalogo = catalogo;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getAlcance() {
		return alcance;
	}

	public void setAlcance(String alcance) {
		this.alcance = alcance;
	}

	public Boolean getSeleccion() {
		return seleccion;
	}

	public void setSeleccion(Boolean seleccion) {
		this.seleccion = seleccion;
	}

}