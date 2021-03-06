package com.tecnolpet.ot.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;


/**
 * The persistent class for the producto database table.
 * 
 */
@Entity
public class Empresa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_empresa")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonView(ViewOT.PublicView.class)
	private Integer id;

	@Column(name="estado_empresa")
	@JsonView(ViewOT.PublicView.class)
	private Boolean estado;

	@Column(name="nombre_empresa")
	@JsonView(ViewOT.PublicView.class)
	private String nombre;
	
	@Column(name="direccion_empresa")
	@JsonView(ViewOT.PublicView.class)
	private String direccion;
	
	@Column(name="ruc_empresa")
	@JsonView(ViewOT.PublicView.class)
	private String ruc;
	
	@Column(name="telefono_empresa")
	@JsonView(ViewOT.PublicView.class)
	private String telefono;
	
	@Column(name="codigo_cooperativa")
	@JsonView(ViewOT.PublicView.class)
	private String codigoCooperativa;
	

	  

	
	

	public Empresa() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	

	

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCodigoCooperativa() {
		return codigoCooperativa;
	}

	public void setCodigoCooperativa(String codigoCooperativa) {
		this.codigoCooperativa = codigoCooperativa;
	}
	
	

}