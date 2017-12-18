/**
 * 
 */
package com.tecnolpet.ot.dto;

import java.io.Serializable;

/**
 * @author administrador
 *
 */
public class CodigoDto implements Serializable{	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4912380366854669796L;
	
	
	private int codigo;
	private String segmento2;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getSegmento2() {
		return segmento2;
	}
	public void setSegmento2(String segmento2) {
		this.segmento2 = segmento2;
	}	 
}
