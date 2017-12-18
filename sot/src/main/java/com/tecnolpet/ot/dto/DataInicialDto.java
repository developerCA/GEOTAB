package com.tecnolpet.ot.dto;

import java.io.Serializable;

public class DataInicialDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4336354889223216295L;

	
	private Long totalorden;
	public Long getTotalorden() {
		return totalorden;
	}
	public void setTotalorden(Long totalorden) {
		this.totalorden = totalorden;
	}
	public Long getPoraprobar() {
		return poraprobar;
	}
	public void setPoraprobar(Long poraprobar) {
		this.poraprobar = poraprobar;
	}
	private Long poraprobar;
	
	
}
