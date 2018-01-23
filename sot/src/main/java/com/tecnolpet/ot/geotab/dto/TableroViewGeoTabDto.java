package com.tecnolpet.ot.geotab.dto;

import java.util.List;

import com.tecnolpet.ot.model.VTablero;

public class TableroViewGeoTabDto {

	private DispositivoTableroDto dispositivoTableroDto;
	
	private List<VTablero> listaHoras;

	public DispositivoTableroDto getDispositivoTableroDto() {
		return dispositivoTableroDto;
	}

	public void setDispositivoTableroDto(DispositivoTableroDto dispositivoTableroDto) {
		this.dispositivoTableroDto = dispositivoTableroDto;
	}

	public List<VTablero> getListaHoras() {
		return listaHoras;
	}

	public void setListaHoras(List<VTablero> listaHoras) {
		this.listaHoras = listaHoras;
	}
	
	
}
