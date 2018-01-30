package com.tecnolpet.ot.geotab.dto;

public class ZonaTableroDto {

	private Integer codigoZona;
	private String nombreZona;
	private String orden;
	private Integer tiempo;
	
	public ZonaTableroDto(Integer codigoZona,String nombreZona,String orden,Integer tiempo  ){
		this.codigoZona=codigoZona;
		this.nombreZona=nombreZona;
		this.orden=orden;
		this.tiempo=tiempo;
				
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

	public Integer getTiempo() {
		return tiempo;
	}

	public void setTiempo(Integer tiempo) {
		this.tiempo = tiempo;
	}
	
	
	
}
