package com.tecnolpet.ot.model.view;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "vw_mas_vendidos")
public class VistaVendidos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long cantidad;
	@Id
	private Long id;

	private String nombre;
	
	public VistaVendidos() {
	}

	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
