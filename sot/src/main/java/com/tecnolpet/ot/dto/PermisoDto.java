package com.tecnolpet.ot.dto;


import java.util.List;

public class PermisoDto {

	private String label;
	private Integer id;

	private List<PermisoDto> children;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<PermisoDto> getChildren() {
		return children;
	}

	public void setChildren(List<PermisoDto> children) {
		this.children = children;
	}


}