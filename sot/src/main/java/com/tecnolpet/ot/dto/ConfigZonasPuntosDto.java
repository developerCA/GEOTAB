package com.tecnolpet.ot.dto;

import java.util.List;

import com.tecnolpet.ot.model.Punto;
import com.tecnolpet.ot.model.Zona;

public class ConfigZonasPuntosDto {
	private List<Zona> listaZonas;
	private List<Punto> listaPuntos;
	
	public List<Zona> getListaZonas() {
		return listaZonas;
	}
	public void setListaZonas(List<Zona> listaZonas) {
		this.listaZonas = listaZonas;
	}
	public List<Punto> getListaPuntos() {
		return listaPuntos;
	}
	public void setListaPuntos(List<Punto> listaPuntos) {
		this.listaPuntos = listaPuntos;
	}
	
}
