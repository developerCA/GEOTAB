package com.tecnolpet.ot.geotab.dto;

import java.util.List;

import com.tecnolpet.ot.model.FechaDispositivo;

public class ProcesaDatosGeotabDto {

	private FechaDispositivo fechaDispositivo;
	
	List<LocalizazionesGeotabDto> listaDatos;

	public FechaDispositivo getFechaDispositivo() {
		return fechaDispositivo;
	}

	public void setFechaDispositivo(FechaDispositivo fechaDispositivo) {
		this.fechaDispositivo = fechaDispositivo;
	}

	public List<LocalizazionesGeotabDto> getListaDatos() {
		return listaDatos;
	}

	public void setListaDatos(List<LocalizazionesGeotabDto> listaDatos) {
		this.listaDatos = listaDatos;
	}
	
	
}
