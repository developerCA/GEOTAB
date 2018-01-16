package com.tecnolpet.ot.geotab.dto;

public class LocalizazionesGeotabDto {

	private String dateTime;
	private double latitude;
	private double longitude;
	
	private DispositivoGeotabDto device;

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public DispositivoGeotabDto getDevice() {
		return device;
	}

	public void setDevice(DispositivoGeotabDto device) {
		this.device = device;
	}
	
	
}
