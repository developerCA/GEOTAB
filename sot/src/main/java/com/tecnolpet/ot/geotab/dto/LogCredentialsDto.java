package com.tecnolpet.ot.geotab.dto;

public class LogCredentialsDto {

	private String database;
	private String userName;
	private String password;

	public LogCredentialsDto() {
		this.database = "mobility";
		this.userName = "raul.mediavilla@mobility.com.ec";
		this.password = "adri1979";
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
