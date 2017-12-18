package com.tecnolpet.ot.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MenuDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1913343239521207422L;
	private Integer id;
	private String iconoPermiso;
	private String nombrePermiso;
	private String ordenPermiso;
	private String urlPermiso;
	private List<MenuDto> permisos =new ArrayList<MenuDto>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIconoPermiso() {
		return iconoPermiso;
	}
	public void setIconoPermiso(String iconoPermiso) {
		this.iconoPermiso = iconoPermiso;
	}
	public String getNombrePermiso() {
		return nombrePermiso;
	}
	public void setNombrePermiso(String nombrePermiso) {
		this.nombrePermiso = nombrePermiso;
	}
	public String getOrdenPermiso() {
		return ordenPermiso;
	}
	public void setOrdenPermiso(String ordenPermiso) {
		this.ordenPermiso = ordenPermiso;
	}
	public String getUrlPermiso() {
		return urlPermiso;
	}
	public void setUrlPermiso(String urlPermiso) {
		this.urlPermiso = urlPermiso;
	}
	public List<MenuDto> getPermisos() {
		return permisos;
	}
	public void setPermisos(List<MenuDto> permisos) {
		this.permisos = permisos;
	}

	
}
