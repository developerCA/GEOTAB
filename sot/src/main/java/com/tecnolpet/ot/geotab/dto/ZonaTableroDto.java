package com.tecnolpet.ot.geotab.dto;

public class ZonaTableroDto {

	private Integer codigoZona;
	private String nombreZona;
	private String orden;
	
	public ZonaTableroDto(Integer codigoZona,String nombreZona,String orden  ){
		this.codigoZona=codigoZona;
		this.nombreZona=nombreZona;
		this.orden=orden;
				
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

	public String getOrden() {
		return orden;
	}

	public void setOrden(String orden) {
		this.orden = orden;
	}
	
	
	
}
