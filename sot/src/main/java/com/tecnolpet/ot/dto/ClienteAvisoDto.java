package com.tecnolpet.ot.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.tecnolpet.ot.model.Cliente;

public class ClienteAvisoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5516261714739052316L;

	private Cliente cliente;
	private  List<ContactoAvisoDto> listaContactos=new ArrayList<ContactoAvisoDto>();
	
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ContactoAvisoDto> getListaContactos() {
		return listaContactos;
	}

	public void setListaContactos(List<ContactoAvisoDto> listaContactos) {
		this.listaContactos = listaContactos;
	}

		
	
}
