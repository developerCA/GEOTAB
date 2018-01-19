package com.tecnolpet.ot.geotab.dto;

public class DispositivoTableroDto {
	private Integer codigoDispositivo;
	private String dispositivo;
	
	public DispositivoTableroDto(Integer codigoDispositivo,String dispositivo  ){
		this.codigoDispositivo=codigoDispositivo;
		this.dispositivo=dispositivo;
		
	}

	public Integer getCodigoDispositivo() {
		return codigoDispositivo;
	}

	public void setCodigoDispositivo(Integer codigoDispositivo) {
		this.codigoDispositivo = codigoDispositivo;
	}

	public String getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(String dispositivo) {
		this.dispositivo = dispositivo;
	}
	
	
}
