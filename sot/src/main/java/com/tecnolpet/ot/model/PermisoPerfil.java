package com.tecnolpet.ot.model;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;

/**
 * The persistent class for the permiso_perfil_producto database table.
 * 
 */
@Entity
@Table(name = "permiso_perfil")
public class PermisoPerfil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_permiso_perfil")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(ViewOT.PublicView.class)
	private Integer id;

	// bi-directional many-to-one association to PerfilProducto
	@ManyToOne
	@JoinColumn(name = "id_perfil")
	@JsonView(ViewOT.PublicView.class)
	private Perfil perfil;

	// bi-directional many-to-one association to Permiso
	@ManyToOne
	@JoinColumn(name = "id_permiso")
	@JsonView(ViewOT.PublicView.class)
	private Permiso permiso;

	@Transient
	@JsonView(ViewOT.PublicView.class)
	private Integer idPermisoPerfil;
	@Transient
	@JsonView(ViewOT.PublicView.class)
	private Integer idPermiso;

	public PermisoPerfil() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public Permiso getPermiso() {
		return this.permiso;
	}

	public void setPermiso(Permiso permiso) {
		this.permiso = permiso;
	}

	public Integer getIdPermisoPerfil() {
		return idPermisoPerfil;
	}

	public void setIdPermisoPerfil(Integer idPermisoPerfil) {
		this.idPermisoPerfil = idPermisoPerfil;
	}

	public Integer getIdPermiso() {
		return idPermiso;
	}

	public void setIdPermiso(Integer idPermiso) {
		this.idPermiso = idPermiso;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

}