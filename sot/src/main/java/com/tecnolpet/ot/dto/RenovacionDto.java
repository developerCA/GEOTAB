/**
 * 
 */
package com.tecnolpet.ot.dto;

import java.io.Serializable;
import java.util.List;

import com.tecnolpet.ot.model.DetalleNotaPedido;
import com.tecnolpet.ot.model.NotaPedido;
import com.tecnolpet.ot.model.Suscripcion;

/**
 * @author administrador
 *
 */


public class RenovacionDto implements Serializable{

	private static final long serialVersionUID = -872400455707274448L;
	
	private NotaPedido notaPedido;
	
	private List<DetalleNotaPedido> detalleNotaPedido;
	
	private List<Suscripcion> suscripciones;
	
	private Integer renovacion;

	public NotaPedido getNotaPedido() {
		return notaPedido;
	}

	public void setNotaPedido(NotaPedido notaPedido) {
		this.notaPedido = notaPedido;
	}

	public List<DetalleNotaPedido> getDetalleNotaPedido() {
		return detalleNotaPedido;
	}

	public void setDetalleNotaPedido(List<DetalleNotaPedido> detalleNotaPedido) {
		this.detalleNotaPedido = detalleNotaPedido;
	}

	public List<Suscripcion> getSuscripciones() {
		return suscripciones;
	}

	public void setSuscripciones(List<Suscripcion> suscripciones) {
		this.suscripciones = suscripciones;
	}

	public Integer getRenovacion() {
		return renovacion;
	}

	public void setRenovacion(Integer renovacion) {
		this.renovacion = renovacion;
	}

}
