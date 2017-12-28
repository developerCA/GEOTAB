package com.tecnolpet.ot.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;

@Entity
@Table(name = "geotab_grupo")
public class GeotabGrupo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2921600904747445922L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo")
	@JsonView(ViewOT.PublicView.class)
	private Integer codigo;

	@JsonView(ViewOT.PublicView.class)
	@Column(name = "name")
	private String nombre;

	@JsonView(ViewOT.PublicView.class)
	@Column(name = "id")
	private String identificador;
	
	@JsonView(ViewOT.PublicView.class)
	@Column(name = "children")
	private String hijos;
	
	@Column(name = "level_group")
	@JsonView(ViewOT.PublicView.class)
	private Integer nivel;
	
	

	public GeotabGrupo() {
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getHijos() {
		return hijos;
	}

	public void setHijos(String hijos) {
		this.hijos = hijos;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

}
