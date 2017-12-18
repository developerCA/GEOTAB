package com.tecnolpet.ot.model;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;

import java.util.List;


/**
 * The persistent class for the regional database table.
 * 
 */
@Entity
@NamedQuery(name="Regional.findAll", query="SELECT r FROM Regional r")
public class Regional implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codigo")
	@JsonView(ViewOT.PublicView.class)
	private Integer id;
	@JsonView(ViewOT.PublicView.class)
	private String nombre;

	//bi-directional many-to-one association to Distribuidore
	@OneToMany(mappedBy="regionalBean")
	@JsonIgnore
	private List<Distribuidores> distribuidores;

	public Regional() {
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

	public List<Distribuidores> getDistribuidores() {
		return this.distribuidores;
	}

	public void setDistribuidores(List<Distribuidores> distribuidores) {
		this.distribuidores = distribuidores;
	}

	public Distribuidores addDistribuidore(Distribuidores distribuidore) {
		getDistribuidores().add(distribuidore);
		distribuidore.setRegionalBean(this);

		return distribuidore;
	}

	public Distribuidores removeDistribuidore(Distribuidores distribuidore) {
		getDistribuidores().remove(distribuidore);
		distribuidore.setRegionalBean(null);

		return distribuidore;
	}

}