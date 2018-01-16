package com.tecnolpet.ot.geotab.dto;

import java.util.List;

public class ZonaGeotabDto {

	private String id;
	private String name;
	
	private List<ZonaRutaGeotabDto> groups;
	
	private List<ZonaTypeGeotabDto> zoneTypes;
	
	private List<PuntoZonaGeotabDto> points;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ZonaRutaGeotabDto> getGroups() {
		return groups;
	}

	public void setGroups(List<ZonaRutaGeotabDto> groups) {
		this.groups = groups;
	}
	
	public List<ZonaTypeGeotabDto> getZoneTypes() {
		return zoneTypes;
	}

	public List<PuntoZonaGeotabDto> getPoints() {
		return points;
	}

	public void setPoints(List<PuntoZonaGeotabDto> points) {
		this.points = points;
	}

	public void setZoneTypes(List<ZonaTypeGeotabDto> zoneTypes) {
		this.zoneTypes = zoneTypes;
	}
	
	

}
