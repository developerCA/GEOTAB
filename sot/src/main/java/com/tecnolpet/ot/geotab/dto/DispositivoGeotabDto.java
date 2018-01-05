package com.tecnolpet.ot.geotab.dto;

import java.util.List;

public class DispositivoGeotabDto {

	private String vehicleIdentificationNumber;
	private String id;
	private String licensePlate;
	private String name;
	private List<GrupoDispositivoDto> groups;
	private String serialNumber;

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getVehicleIdentificationNumber() {
		return vehicleIdentificationNumber;
	}

	public void setVehicleIdentificationNumber(String vehicleIdentificationNumber) {
		this.vehicleIdentificationNumber = vehicleIdentificationNumber;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<GrupoDispositivoDto> getGroups() {
		return groups;
	}

	public void setGroups(List<GrupoDispositivoDto> groups) {
		this.groups = groups;
	}
	
	
}
