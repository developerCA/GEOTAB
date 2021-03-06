package com.tecnolpet.ot.model;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;


/**
 * The persistent class for the usuario_rol database table.
 * 
 */
@Entity
@Table(name="usuario_rol")
@NamedQuery(name="UsuarioRol.findAll", query="SELECT u FROM UsuarioRol u")
public class UsuarioRol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_usuario_rol")
	@JsonView(ViewOT.PublicView.class)
	private Integer idUsuarioRol;

	//bi-directional many-to-one association to Rol
	@ManyToOne
	@JoinColumn(name="id_rol")
	@JsonView(ViewOT.PublicView.class)
	private Rol rol;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	@JsonView(ViewOT.PublicView.class)
	private Usuario usuario;

	public UsuarioRol() {
	}

	public Integer getIdUsuarioRol() {
		return this.idUsuarioRol;
	}

	public void setIdUsuarioRol(Integer idUsuarioRol) {
		this.idUsuarioRol = idUsuarioRol;
	}

	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}