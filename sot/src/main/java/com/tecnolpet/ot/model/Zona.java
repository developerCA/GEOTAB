package com.tecnolpet.ot.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the zona database table.
 * 
 */
@Entity
@NamedQuery(name="Zona.findAll", query="SELECT z FROM Zona z")
public class Zona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigo;

	private String identificador;

	private String nombre;
	
	private String orden;
	
	private Integer tiempo;
	
	@Column(name="valida_zona")
	private Boolean  valida;
	
	@Column(name="inicio_zona")
	private Boolean inicioZona;

	//bi-directional many-to-one association to Ruta
	@ManyToOne
	@JoinColumn(name="codigo_ruta")
	private Ruta ruta;
	
	@ManyToOne
	@JoinColumn(name="codigo_tipo_zona")
	private TipoZona tipoZona;

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

}