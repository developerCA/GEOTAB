package com.tecnolpet.ot.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;


/**
 * The persistent class for the sub_categoria database table.
 * 
 */
@Entity
@Table(name="sub_categoria")
@NamedQuery(name="SubCategoria.findAll", query="SELECT s FROM SubCategoria s")
public class SubCategoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codigo")
	@JsonView(ViewOT.PublicView.class)
	private Integer id;
	@JsonView(ViewOT.PublicView.class)
	private String nombre;



	public SubCategoria() {
	}



	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}

	

	
}