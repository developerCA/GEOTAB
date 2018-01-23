package com.tecnolpet.ot.geotab.dto;

import java.util.List;

public class TableroGeoTabDto {


	
	private List<ZonaTableroDto> zonas;
	
	private List<TableroViewGeoTabDto> tablero;

	private List<DispositivoTableroDto> dispositivos;

	public List<TableroViewGeoTabDto> getTablero() {
		return tablero;
	}

	public void setTablero(List<TableroViewGeoTabDto> tablero) {
		this.tablero = tablero;
	}

	public List<ZonaTableroDto> getZonas() {
		return zonas;
	}

	public void setZonas(List<ZonaTableroDto> zonas) {
		this.zonas = zonas;
	}

	public List<DispositivoTableroDto> getDispositivos() {
		return dispositivos;
	}

	public void setDispositivos(List<DispositivoTableroDto> dispositivos) {
		this.dispositivos = dispositivos;
	}
	
}
