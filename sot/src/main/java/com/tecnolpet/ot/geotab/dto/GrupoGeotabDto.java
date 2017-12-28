	package com.tecnolpet.ot.geotab.dto;

import java.util.List;

public class GrupoGeotabDto {

	
	private String comments;
	private String id;
	private String name;
	
	private List<GrupoChildrenGeoTabDto> children;

	

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<GrupoChildrenGeoTabDto> getChildren() {
		return children;
	}

	public void setChildren(List<GrupoChildrenGeoTabDto> children) {
		this.children = children;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
