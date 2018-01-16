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

}