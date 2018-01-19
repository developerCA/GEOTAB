package com.tecnolpet.ot.geotab.dto;

public class ZonaTableroDto {

	private Integer codigoZona;
	private String nombreZona;
	
	public ZonaTableroDto(Integer codigoZona,String nombreZona  ){
		this.codigoZona=codigoZona;
		this.nombreZona=nombreZona;
		
	}

	public Integer getCodigoZona() {
		return codigoZona;
	}

	public void setCodigoZona(Integer codigoZona) {
		this.codigoZona = codigoZona;
	}

	public String getNombreZona() {
		return nombreZona;
	}

	public void setNombreZona(String nombreZona) {
		this.nombreZona = nombreZona;
	}
	
	
	
}
