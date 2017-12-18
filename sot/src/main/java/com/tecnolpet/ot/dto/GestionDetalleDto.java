/**
 * 
 */
package com.tecnolpet.ot.dto;

import java.io.Serializable;
import java.util.List;

import com.tecnolpet.ot.model.Aviso;
import com.tecnolpet.ot.model.Suscripcion;

/**
 * @author Armando
 *
 */
public class GestionDetalleDto implements Serializable{

	private static final long serialVersionUID = -3628618467307174029L;

	private Aviso aviso;
	
	private List<Suscripcion> suscripciones;
	
	private RenovacionAvisoDto renovacionesDto;
	
	private Integer telerenovador;
	
	public Aviso getAviso() {
		return aviso;
	}
	public void setAviso(Aviso aviso) {
		this.aviso = aviso;
	}
	public List<Suscripcion> getSuscripciones() {
		return suscripciones;
	}
	public void setSuscripciones(List<Suscripcion> suscripciones) {
		this.suscripciones = suscripciones;
	}
	public Integer getTelerenovador() {
		return telerenovador;
	}
	public void setTelerenovador(Integer telerenovador) {
		this.telerenovador = telerenovador;
	}
	public RenovacionAvisoDto getRenovacionesDto() {
		return renovacionesDto;
	}
	public void setRenovacionesDto(RenovacionAvisoDto renovacionesDto) {
		this.renovacionesDto = renovacionesDto;
	}

	
}
