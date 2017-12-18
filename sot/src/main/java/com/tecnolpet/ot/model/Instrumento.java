package com.tecnolpet.ot.model;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;

import java.util.List;

/**
 * The persistent class for the instrumento database table.
 * 
 */
@Entity
@NamedQuery(name = "Instrumento.findAll", query = "SELECT i FROM Instrumento i")
public class Instrumento implements Serializable {
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

	// bi-directional many-to-one association to Calibracion
	@OneToMany(mappedBy = "instrumento")
	@JsonIgnore
	private List<Calibracion> calibracions;

	// bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name = "codigo_empresa")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@RestResource(exported = false)
	@JsonView(ViewOT.PublicView.class)
	private Empresa empresa;

	// bi-directional many-to-one association to Catalogo
	@ManyToOne
	@JoinColumn(name = "estado")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@RestResource(exported = false)
	@JsonView(ViewOT.PublicView.class)
	private Catalogo catalogo;

	public Instrumento() {
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

	public List<Calibracion> getCalibracions() {
		return this.calibracions;
	}

	public void setCalibracions(List<Calibracion> calibracions) {
		this.calibracions = calibracions;
	}

	public Calibracion addCalibracion(Calibracion calibracion) {
		getCalibracions().add(calibracion);
		calibracion.setInstrumento(this);

		return calibracion;
	}

	public Calibracion removeCalibracion(Calibracion calibracion) {
		getCalibracions().remove(calibracion);
		calibracion.setInstrumento(null);

		return calibracion;
	}

	public Catalogo getCatalogo() {
		return this.catalogo;
	}

	public void setCatalogo(Catalogo catalogo) {
		this.catalogo = catalogo;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

}