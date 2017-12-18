package com.tecnolpet.ot.model;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;


/**
 * The persistent class for the secuencia_orden database table.
 * 
 */
@Entity
@Table(name="secuencia_orden")
@NamedQuery(name="SecuenciaOrden.findAll", query="SELECT s FROM SecuenciaOrden s")
public class SecuenciaOrden implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_secuencia_orden")
	@JsonView(ViewOT.PublicView.class)
	private Integer id;

	@Column(name="numero_secuencia")
	@JsonView(ViewOT.PublicView.class)
	private Integer numero;

	//bi-directional many-to-one association to Empresa
	@ManyToOne
	@JoinColumn(name="id_empresa")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)
	@JsonView(ViewOT.PublicView.class)
	private Empresa empresa;

	//bi-directional many-to-one association to Secuencia
	@ManyToOne
	@JoinColumn(name="id_secuencia")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)
	@JsonView(ViewOT.PublicView.class)
	private Secuencia secuencia;

	public SecuenciaOrden() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumero() {
		return this.numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Secuencia getSecuencia() {
		return this.secuencia;
	}

	public void setSecuencia(Secuencia secuencia) {
		this.secuencia = secuencia;
	}

}