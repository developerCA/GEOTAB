/**
 * 
 */
package com.tecnolpet.ot.dto;

import java.io.Serializable;
import java.util.List;

import com.tecnolpet.ot.model.FechasRenovacion;
import com.tecnolpet.ot.model.Suscripcion;

/**
 * @author Armando Ariel Suárez Pons ss
 *
 */
public class AvisoDto implements Serializable{
	
	private static final long serialVersionUID = -3685751770021764812L;
	
	private FechasRenovacion aviso;
	private List<Suscripcion> suscripciones;
	
	public FechasRenovacion getAviso() {
		return aviso;
	}
	public void setAviso(FechasRenovacion aviso) {
		this.aviso = aviso;
	}
	public List<Suscripcion> getSuscripciones() {
		return suscripciones;
	}
	public void setSuscripciones(List<Suscripcion> suscripciones) {
		this.suscripciones = suscripciones;
	}
	
}
