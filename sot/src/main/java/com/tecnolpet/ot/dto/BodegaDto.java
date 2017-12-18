package com.tecnolpet.ot.dto;

import java.io.Serializable;

import com.tecnolpet.ot.model.Catalogo;

public class BodegaDto implements Serializable {

	private static final long serialVersionUID = 1737263145252266061L;
	
	private String codigo_Koynor;
	private Integer id;
	private Catalogo estado;

	public Catalogo getEstado() {
		return estado;
	}

	public void setEstado(Catalogo estado) {
		this.estado = estado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private String nombre;

	public String getCodigoKoynor() {
		return codigo_Koynor;
	}

	public void setCodigoKoynor(String codigo_Koynor) {
		this.codigo_Koynor = codigo_Koynor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
