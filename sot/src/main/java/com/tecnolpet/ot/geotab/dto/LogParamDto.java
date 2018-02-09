package com.tecnolpet.ot.geotab.dto;

public class LogParamDto {

	private String typeName;
	
	private LogSearchDto search;
	
	private LogCredentialsDto credentials;

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public LogSearchDto getSearch() {
		return search;
	}

	public void setSearch(LogSearchDto search) {
		this.search = search;
	}

	public LogCredentialsDto getCredentials() {
		return credentials;
	}

	public void setCredentials(LogCredentialsDto credentials) {
		this.credentials = credentials;
	}
	
	
}
