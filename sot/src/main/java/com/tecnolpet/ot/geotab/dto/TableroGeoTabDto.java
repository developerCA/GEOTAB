package com.tecnolpet.ot.geotab.dto;

import java.util.List;
import com.tecnolpet.ot.model.VTablero;

public class TableroGeoTabDto {


	
	private List<ZonaTableroDto> zonas;
	
	private List<VTablero> tablero;

	private List<DispositivoTableroDto> dispositivos;

	public List<VTablero> getTablero() {
		return tablero;
	}

	public void setTablero(List<VTablero> tablero) {
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
