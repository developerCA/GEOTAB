package com.tecnolpet.ot.model;

import java.io.Serializable;
import javax.persistence.*;

import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;


/**
 * The persistent class for the zona database table.
 * 
 */
@Entity
public class Zona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonView(ViewOT.PublicView.class)
	private Integer codigo;

	@JsonView(ViewOT.PublicView.class)
	private String identificador;

	@JsonView(ViewOT.PublicView.class)
	private String nombre;
	
	@JsonView(ViewOT.PublicView.class)
	private String orden;
	
	@JsonView(ViewOT.PublicView.class)
	private Integer tiempo;
	
	@JsonView(ViewOT.PublicView.class)
	@Column(name="valida_zona")
	private Boolean  valida;
	
	@JsonView(ViewOT.PublicView.class)
	@Column(name="inicio_zona")
	private Boolean inicioZona;
	
	@JsonView(ViewOT.PublicView.class)
	@Column(name="zona_retorno")
	private Boolean zonaRetorno;
	@JsonView(ViewOT.PublicView.class)
	private Boolean estado;
	
	@JsonView(ViewOT.PublicView.class)
	@Column(name="inicio_zona_retorno")
	private Boolean inicioZonaRetorno;
	
	//bi-directional many-to-one association to Ruta
	@JsonView(ViewOT.PublicView.class)
	@ManyToOne
	@JoinColumn(name="codigo_ruta")
	private Ruta ruta;
	
	@JsonView(ViewOT.PublicView.class)
	@ManyToOne
	@JoinColumn(name="codigo_tipo_zona")
	private TipoZona tipoZona;
	
	@JsonView(ViewOT.PublicView.class)
	@JoinColumn(name="zona_enlace")
	@ManyToOne
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)		
	private Zona zonaEnlace;
	
	
	public Zona() {
	}

	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getIdentificador() {
		return this.identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Ruta getRuta() {
		return this.ruta;
	}

	public void setRuta(Ruta ruta) {
		this.ruta = ruta;
	}

	public TipoZona getTipoZona() {
		return tipoZona;
	}

	public void setTipoZona(TipoZona tipoZona) {
		this.tipoZona = tipoZona;
	}

	public String getOrden() {
		return orden;
	}

	public void setOrden(String orden) {
		this.orden = orden;
	}

	public Integer getTiempo() {
		return tiempo;
	}

	public void setTiempo(Integer tiempo) {
		this.tiempo = tiempo;
	}

	public Boolean getValida() {
		return valida;
	}

	public void setValida(Boolean valida) {
		this.valida = valida;
	}

	public Boolean getInicioZona() {
		return inicioZona;
	}

	public void setInicioZona(Boolean inicioZona) {
		this.inicioZona = inicioZona;
	}

	public Zona getZonaEnlace() {
		return zonaEnlace;
	}

	public void setZonaEnlace(Zona zonaEnlace) {
		this.zonaEnlace = zonaEnlace;
	}

	public Boolean getZonaRetorno() {
		return zonaRetorno;
	}

	public void setZonaRetorno(Boolean zonaRetorno) {
		this.zonaRetorno = zonaRetorno;
	}

	public Boolean getInicioZonaRetorno() {
		return inicioZonaRetorno;
	}

	public void setInicioZonaRetorno(Boolean inicioZonaRetorno) {
		this.inicioZonaRetorno = inicioZonaRetorno;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}



}