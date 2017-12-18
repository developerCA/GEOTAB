package com.tecnolpet.ot.model;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;

import java.util.List;


/**
 * The persistent class for the rol database table.
 * 
 */
@Entity
@NamedQuery(name="Rol.findAll", query="SELECT r FROM Rol r")
public class Rol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_rol")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonView(ViewOT.PublicView.class)
	private Integer id;

	@Column(name="descripcion_rol")
	@JsonView(ViewOT.PublicView.class)
	private String descripcionRol;

	@Column(name="estado_rol")
	@JsonView(ViewOT.PublicView.class)
	private Boolean estadoRol;

	@Column(name="nombre_rol")
	@JsonView(ViewOT.PublicView.class)
	private String nombreRol;

	//bi-directional many-to-one association to UsuarioRol
	@OneToMany(mappedBy="rol")
	@JsonView(ViewOT.PublicView.class)
	private List<UsuarioRol> usuarioRols;

	public Rol() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcionRol() {
		return this.descripcionRol;
	}

	public void setDescripcionRol(String descripcionRol) {
		this.descripcionRol = descripcionRol;
	}

	public Boolean getEstadoRol() {
		return this.estadoRol;
	}

	public void setEstadoRol(Boolean estadoRol) {
		this.estadoRol = estadoRol;
	}

	public String getNombreRol() {
		return this.nombreRol;
	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}

	@JsonIgnore
	@JsonProperty(value = "usuarioRols")
	public List<UsuarioRol> getUsuarioRols() {
		return this.usuarioRols;
	}
	@JsonProperty(value = "usuarioRols")
	public void setUsuarioRols(List<UsuarioRol> usuarioRols) {
		this.usuarioRols = usuarioRols;
	}

	public UsuarioRol addUsuarioRol(UsuarioRol usuarioRol) {
		getUsuarioRols().add(usuarioRol);
		usuarioRol.setRol(this);

		return usuarioRol;
	}

	public UsuarioRol removeUsuarioRol(UsuarioRol usuarioRol) {
		getUsuarioRols().remove(usuarioRol);
		usuarioRol.setRol(null);

		return usuarioRol;
	}

}