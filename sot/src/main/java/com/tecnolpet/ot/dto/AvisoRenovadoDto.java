/**
 * 
 */
package com.tecnolpet.ot.dto;

import java.io.Serializable;
import java.util.List;

import com.tecnolpet.ot.model.Aviso;

/**
 * @author administrador
 *
 */
public class AvisoRenovadoDto implements Serializable{

	private static final long serialVersionUID = -1900057083475986582L;
	
	private Aviso aviso;
	private List<SuscripcionesAccesosWrapperDto> suscripcionesAccesosWrapperDtoList;
	private List<NuevasRenovacionesDto> nuevas;
	private Integer idSuscripcion;
	
	public Aviso getAviso() {
		return aviso;
	}
	public void setAviso(Aviso aviso) {
		this.aviso = aviso;
	}
	public List<SuscripcionesAccesosWrapperDto> getSuscripcionesAccesosWrapperDtoList() {
		return suscripcionesAccesosWrapperDtoList;
	}
	public void setSuscripcionesAccesosWrapperDtoList(List<SuscripcionesAccesosWrapperDto> suscripcionesAccesosWrapperDtoList) {
		this.suscripcionesAccesosWrapperDtoList = suscripcionesAccesosWrapperDtoList;
	}
	public List<NuevasRenovacionesDto> getNuevas() {
		return nuevas;
	}
	public void setNuevas(List<NuevasRenovacionesDto> nuevas) {
		this.nuevas = nuevas;
	}
	public Integer getIdSuscripcion() {
		return idSuscripcion;
	}
	public void setIdSuscripcion(Integer idSuscripcion) {
		this.idSuscripcion = idSuscripcion;
	}	
	
}
