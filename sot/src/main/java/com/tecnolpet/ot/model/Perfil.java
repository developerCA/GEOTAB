package com.tecnolpet.ot.model;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
//import com.thoughtworks.qdox.model.BeanProperty;


import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;

import java.util.List;


/**
 * The persistent class for the perfil database table.
 * 
 */
@Entity
@NamedQuery(name="Perfil.findAll", query="SELECT p FROM Perfil p")
public class Perfil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_perfil")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonView(ViewOT.PublicView.class)
	private Integer id;

	@Column(name="descripcion_perfil")
	@JsonView(ViewOT.PublicView.class)
	private String descripcionPerfil;

	@Column(name="estado_perfil")
	@JsonView(ViewOT.PublicView.class)
	private Boolean estadoPerfil;

	@Column(name="nombre_perfil")
	@JsonView(ViewOT.PublicView.class)
	private String nombrePerfil;

	//bi-directional many-to-one association to PerfilProducto
	@OneToMany(mappedBy="perfil")
	private List<PerfilEmpresa> perfilProductos;

	public Perfil() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id= id;
	}

	public String getDescripcionPerfil() {
		return this.descripcionPerfil;
	}

	public void setDescripcionPerfil(String descripcionPerfil) {
		this.descripcionPerfil = descripcionPerfil;
	}

	public Boolean getEstadoPerfil() {
		return this.estadoPerfil;
	}

	public void setEstadoPerfil(Boolean estadoPerfil) {
		this.estadoPerfil = estadoPerfil;
	}

	public String getNombrePerfil() {
		return this.nombrePerfil;
	}

	public void setNombrePerfil(String nombrePerfil) {
		this.nombrePerfil = nombrePerfil;
	}

	@JsonIgnore
	@JsonProperty(value="perfilProductos")
	public List<PerfilEmpresa> getPerfilProductos() {
		return this.perfilProductos;
	}

	@JsonProperty(value="perfilProductos")
	public void setPerfilProductos(List<PerfilEmpresa> perfilProductos) {
		this.perfilProductos = perfilProductos;
	}

	public PerfilEmpresa addPerfilProducto(PerfilEmpresa perfilProducto) {
		getPerfilProductos().add(perfilProducto);
		perfilProducto.setPerfil(this);

		return perfilProducto;
	}

	public PerfilEmpresa removePerfilProducto(PerfilEmpresa perfilProducto) {
		getPerfilProductos().remove(perfilProducto);
		perfilProducto.setPerfil(null);

		return perfilProducto;
	}

}