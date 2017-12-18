package com.tecnolpet.ot.dto;

import java.io.Serializable;
import java.util.List;

import com.tecnolpet.ot.model.Acceso;
import com.tecnolpet.ot.model.Cliente;
import com.tecnolpet.ot.model.Suscripcion;

public class FielWebDto implements Serializable{

	private static final long serialVersionUID = -5655542191743763056L;
	
	private Cliente cliente;
	
	private Suscripcion suscripcion;
	
	private List<Acceso> accesos;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Suscripcion getSuscripcion() {
		return suscripcion;
	}

	public void setSuscripcion(Suscripcion suscripcion) {
		this.suscripcion = suscripcion;
	}

	public List<Acceso> getAccesos() {
		return accesos;
	}

	public void setAccesos(List<Acceso> accesos) {
		this.accesos = accesos;
	}

}
