package com.tecnolpet.ot.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;

@Entity
@Table(name = "ruta")
public class Ruta  implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2055672925621969061L;

	@Id
	@Column(name = "codigo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(ViewOT.PublicView.class)
	private Integer codigo;
	
	@JsonView(ViewOT.PublicView.class)
	@Column(name = "nombre")
	private String nombre;
	
	@JsonView(ViewOT.PublicView.class)
	@Column(name = "identificador")
	private String identificador;
	
	@ManyToOne
	@JoinColumn(name = "codigo_empresa")
	@RestResource(exported=false)
	@JsonView(ViewOT.PublicView.class)
	private Empresa empresa;
	
	public Ruta() {
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

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	

	
}
