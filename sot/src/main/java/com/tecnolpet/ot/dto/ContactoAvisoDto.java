package com.tecnolpet.ot.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.tecnolpet.ot.model.AvisoDetalle;
import com.tecnolpet.ot.model.Enlace;

public class ContactoAvisoDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -520994767715125065L;


	private Enlace enlace;
	

	private List<AvisoDetalle> listaDetalle=new ArrayList<AvisoDetalle>();


	public Enlace getEnlace() {
		return enlace;
	}


	public List<AvisoDetalle> getListaDetalle() {
		return listaDetalle;
	}


	public void setEnlace(Enlace enlace) {
		this.enlace = enlace;
	}


	public void setListaDetalle(List<AvisoDetalle> listaDetalle) {
		this.listaDetalle = listaDetalle;
	}
}
