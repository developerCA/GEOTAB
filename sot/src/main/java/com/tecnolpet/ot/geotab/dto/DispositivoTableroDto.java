package com.tecnolpet.ot.geotab.dto;


public class DispositivoTableroDto {
	private Integer codigoDispositivo;
	private String dispositivo;
	private Integer numeroVuelta;

	public DispositivoTableroDto(Integer codigoDispositivo, String dispositivo,
			Integer numeroVuelta) {
		this.codigoDispositivo = codigoDispositivo;
		this.dispositivo = dispositivo;
		this.numeroVuelta = numeroVuelta;

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

	public Integer getNumeroVuelta() {
		return numeroVuelta;
	}

	public void setNumeroVuelta(Integer numeroVuelta) {
		this.numeroVuelta = numeroVuelta;
	}

	

}
