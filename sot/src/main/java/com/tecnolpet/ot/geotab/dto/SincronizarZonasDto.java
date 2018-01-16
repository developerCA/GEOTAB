package com.tecnolpet.ot.geotab.dto;

import java.util.List;

public class SincronizarZonasDto {

	private List<TipoZonaGeotabDto> tipoZonaGeotabDto;
	private List<ZonaGeotabDto> zonaGeotabDtos;
	

	public List<TipoZonaGeotabDto> getTipoZonaGeotabDto() {
		return tipoZonaGeotabDto;
	}

	public void setTipoZonaGeotabDto(List<TipoZonaGeotabDto> tipoZonaGeotabDto) {
		this.tipoZonaGeotabDto = tipoZonaGeotabDto;
	}

	public List<ZonaGeotabDto> getZonaGeotabDtos() {
		return zonaGeotabDtos;
	}

	public void setZonaGeotabDtos(List<ZonaGeotabDto> zonaGeotabDtos) {
		this.zonaGeotabDtos = zonaGeotabDtos;
	}
	
	
	
}
