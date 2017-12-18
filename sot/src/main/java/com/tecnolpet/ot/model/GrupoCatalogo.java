package com.tecnolpet.ot.model;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;

import java.util.List;


/**
 * The persistent class for the grupo_catalogo database table.
 * 
 */
@Entity
@Table(name="grupo_catalogo")
@NamedQuery(name="GrupoCatalogo.findAll", query="SELECT g FROM GrupoCatalogo g")
public class GrupoCatalogo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codigo")
	@JsonView(ViewOT.PublicView.class)
	private Integer id;
	@JsonView(ViewOT.PublicView.class)
	private String descripcion;
	@JsonView(ViewOT.PublicView.class)
	private String nombre;
	@JsonView(ViewOT.PublicView.class)
	private String siglas;

	//bi-directional many-to-one association to Catalogo
	@OneToMany(mappedBy="grupoCatalogo")
	@JsonIgnore
	private List<Catalogo> catalogos;

	public GrupoCatalogo() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSiglas() {
		return this.siglas;
	}

	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}

	public List<Catalogo> getCatalogos() {
		return this.catalogos;
	}

	public void setCatalogos(List<Catalogo> catalogos) {
		this.catalogos = catalogos;
	}

	public Catalogo addCatalogo(Catalogo catalogo) {
		getCatalogos().add(catalogo);
		catalogo.setGrupoCatalogo(this);

		return catalogo;
	}

	public Catalogo removeCatalogo(Catalogo catalogo) {
		getCatalogos().remove(catalogo);
		catalogo.setGrupoCatalogo(null);

		return catalogo;
	}

}