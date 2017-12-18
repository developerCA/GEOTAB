package com.tecnolpet.ot.model;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;

import java.util.List;


/**
 * The persistent class for the secuencia database table.
 * 
 */
@Entity
@NamedQuery(name="Secuencia.findAll", query="SELECT s FROM Secuencia s")
public class Secuencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_secuencia")
	@JsonView(ViewOT.PublicView.class)
	private Integer id;

	@Column(name="nombre_secuencia")
	@JsonView(ViewOT.PublicView.class)
	private String nombre;

	//bi-directional many-to-one association to SecuenciaOrden
	@OneToMany(mappedBy="secuencia")
	@JsonView(ViewOT.PublicView.class)
	private List<SecuenciaOrden> secuenciaOrdens;

	public Secuencia() {
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

	public List<SecuenciaOrden> getSecuenciaOrdens() {
		return this.secuenciaOrdens;
	}

	public void setSecuenciaOrdens(List<SecuenciaOrden> secuenciaOrdens) {
		this.secuenciaOrdens = secuenciaOrdens;
	}

	public SecuenciaOrden addSecuenciaOrden(SecuenciaOrden secuenciaOrden) {
		getSecuenciaOrdens().add(secuenciaOrden);
		secuenciaOrden.setSecuencia(this);

		return secuenciaOrden;
	}

	public SecuenciaOrden removeSecuenciaOrden(SecuenciaOrden secuenciaOrden) {
		getSecuenciaOrdens().remove(secuenciaOrden);
		secuenciaOrden.setSecuencia(null);

		return secuenciaOrden;
	}

}