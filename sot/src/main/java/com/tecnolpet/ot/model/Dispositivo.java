package com.tecnolpet.ot.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;

/**
 * The persistent class for the instrumento database table.
 * 
 */
@Entity
public class Dispositivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo")
	@JsonView(ViewOT.PublicView.class)
	private Integer id;
	@JsonView(ViewOT.PublicView.class)
	private String fabricante;
	@JsonView(ViewOT.PublicView.class)
	private String graduacion;
	@JsonView(ViewOT.PublicView.class)
	private String modelo;
	@JsonView(ViewOT.PublicView.class)
	private String nombre;
	@JsonView(ViewOT.PublicView.class)
	private String rango;
	@JsonView(ViewOT.PublicView.class)
	private String serie;

	@JsonView(ViewOT.PublicView.class)
	private String placa;
	@JsonView(ViewOT.PublicView.class)
	private String habilitacion;
	@JsonView(ViewOT.PublicView.class)
	private String codigoDispositivo;
	
	// bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name = "codigo_ruta")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@RestResource(exported = false)
	@JsonView(ViewOT.PublicView.class)
	private Ruta ruta;

	

	// bi-directional many-to-one association to Catalogo
	@ManyToOne
	@JoinColumn(name = "estado")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@RestResource(exported = false)
	@JsonView(ViewOT.PublicView.class)
	private Catalogo catalogo;

	public Dispositivo() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFabricante() {
		return this.fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getGraduacion() {
		return this.graduacion;
	}

	public void setGraduacion(String graduacion) {
		this.graduacion = graduacion;
	}

	public String getModelo() {
		return this.modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRango() {
		return this.rango;
	}

	public void setRango(String rango) {
		this.rango = rango;
	}

	public String getSerie() {
		return this.serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	

	

	public Catalogo getCatalogo() {
		return this.catalogo;
	}

	public void setCatalogo(Catalogo catalogo) {
		this.catalogo = catalogo;
	}

	
	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getHabilitacion() {
		return habilitacion;
	}

	public void setHabilitacion(String habilitacion) {
		this.habilitacion = habilitacion;
	}

		public Ruta getRuta() {
		return ruta;
	}

	public void setRuta(Ruta ruta) {
		this.ruta = ruta;
	}

	public String getCodigoDispositivo() {
		return codigoDispositivo;
	}

	public void setCodigoDispositivo(String codigoDispositivo) {
		this.codigoDispositivo = codigoDispositivo;
	}
}