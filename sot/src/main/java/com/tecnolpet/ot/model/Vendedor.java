package com.tecnolpet.ot.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;


/**
 * The persistent class for the vendedor database table.
 * 
 */
@Entity
public class Vendedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codigo")
	@JsonView(ViewOT.PublicView.class)
	private Integer id;
	
	@JsonView(ViewOT.PublicView.class)
	private String apellidos;
	
	@JsonView(ViewOT.PublicView.class)
	private String cedula;
	
	@JsonView(ViewOT.PublicView.class)
	private String celular;
	
	@ManyToOne
	@JoinColumn(name="estado")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)	
	@JsonView(ViewOT.PublicView.class)
	private Catalogo estado;

	@JsonView(ViewOT.PublicView.class)
	private String cargo;
	
	@JsonView(ViewOT.PublicView.class)
	private String email;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_nacimiento")
	@JsonView(ViewOT.PublicView.class)
	private Date fechaNacimiento;
	
	@JsonView(ViewOT.PublicView.class)
	private String nombres;
	
	@JsonView(ViewOT.PublicView.class)
	private String telefono;
	


	public Vendedor() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	

	public Catalogo getEstado() {
		return estado;
	}

	public void setEstado(Catalogo estado) {
		this.estado = estado;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}



	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}



}