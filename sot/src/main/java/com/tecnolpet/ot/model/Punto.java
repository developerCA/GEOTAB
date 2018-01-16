package com.tecnolpet.ot.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the punto database table.
 * 
 */
@Entity
@NamedQuery(name="Punto.findAll", query="SELECT p FROM Punto p")
public class Punto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigo;

	private double posx;

	private double posy;

	//bi-directional many-to-one association to Zona
	@ManyToOne
	@JoinColumn(name="codigo_zona")
	private Zona zona;

	public Punto() {
	}

	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public double getPosx() {
		return this.posx;
	}

	public void setPosx(double posx) {
		this.posx = posx;
	}

	public double getPosy() {
		return this.posy;
	}

	public void setPosy(double posy) {
		this.posy = posy;
	}

	public Zona getZona() {
		return this.zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}

}