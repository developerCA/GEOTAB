package com.tecnolpet.ot.model;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;

import java.util.Date;

/**
 * The persistent class for the calibracion database table.
 * 
 */
@Entity
public class Calibracion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo")
	@JsonView(ViewOT.PublicView.class)
	private Integer id;
	
	@JsonView(ViewOT.PublicView.class)
	private String archivo;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_calibracion")
	@JsonView(ViewOT.PublicView.class)
	private Date fechaCalibracion;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_registro")
	@JsonView(ViewOT.PublicView.class)
	private Date fechaRegistro;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_calibracion_futura")
	@JsonView(ViewOT.PublicView.class)
	private Date fechaCalibracionFutura;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_verificacion_intermedia")
	@JsonView(ViewOT.PublicView.class)
	private Date fechaVerificacionIntermedia;
	
	@JsonView(ViewOT.PublicView.class)
	private String observacion;

	@JsonView(ViewOT.PublicView.class)
	@Column(name = "archivo_verificacion")
	private String archivoVerificacion;
	

	@ManyToOne
	@JoinColumn(name = "estado")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@RestResource(exported = false)
	@JsonView(ViewOT.PublicView.class)
	private Catalogo catalogo;

	// bi-directional many-to-one association to Instrumento
	@ManyToOne
	@JoinColumn(name = "codigo_instrumento")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@RestResource(exported = false)
	@JsonView(ViewOT.PublicView.class)
	private Instrumento instrumento;

	public Calibracion() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getArchivo() {
		return this.archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	public Date getFechaCalibracion() {
		return this.fechaCalibracion;
	}

	public void setFechaCalibracion(Date fechaCalibracion) {
		this.fechaCalibracion = fechaCalibracion;
	}

	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Catalogo getCatalogo() {
		return this.catalogo;
	}

	public void setCatalogo(Catalogo catalogo) {
		this.catalogo = catalogo;
	}

	public Instrumento getInstrumento() {
		return this.instrumento;
	}

	public void setInstrumento(Instrumento instrumento) {
		this.instrumento = instrumento;
	}

	public Date getFechaCalibracionFutura() {
		return fechaCalibracionFutura;
	}

	public void setFechaCalibracionFutura(Date fechaCalibracionFutura) {
		this.fechaCalibracionFutura = fechaCalibracionFutura;
	}

	public Date getFechaVerificacionIntermedia() {
		return fechaVerificacionIntermedia;
	}

	public void setFechaVerificacionIntermedia(Date fechaVerificacionIntermedia) {
		this.fechaVerificacionIntermedia = fechaVerificacionIntermedia;
	}

	public String getArchivoVerificacion() {
		return archivoVerificacion;
	}

	public void setArchivoVerificacion(String archivoVerificacion) {
		this.archivoVerificacion = archivoVerificacion;
	}

}