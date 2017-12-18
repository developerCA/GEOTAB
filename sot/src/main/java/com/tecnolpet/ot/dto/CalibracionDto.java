package com.tecnolpet.ot.dto;

import java.io.Serializable;

import com.tecnolpet.ot.model.Calibracion;

public class CalibracionDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5523583361603250843L;

	
	private Calibracion calibracion;
	
	private Integer dias;
	
	private Integer diasFaltantes;
	
	private Integer diasFaltantesVerificacion;
	
	private String semaforo;
	
	private String semaforoVerificacion;
	
	public Calibracion getCalibracion() {
		return calibracion;
	}

	public void setCalibracion(Calibracion calibracion) {
		this.calibracion = calibracion;
	}

	public Integer getDias() {
		return dias;
	}

	public void setDias(Integer dias) {
		this.dias = dias;
	}

	public String getSemaforo() {
		return semaforo;
	}

	public void setSemaforo(String semaforo) {
		this.semaforo = semaforo;
	}

	public Integer getDiasFaltantes() {
		return diasFaltantes;
	}

	public void setDiasFaltantes(Integer diasFaltantes) {
		this.diasFaltantes = diasFaltantes;
	}

	public String getSemaforoVerificacion() {
		return semaforoVerificacion;
	}

	public void setSemaforoVerificacion(String semaforoVerificacion) {
		this.semaforoVerificacion = semaforoVerificacion;
	}

	public Integer getDiasFaltantesVerificacion() {
		return diasFaltantesVerificacion;
	}

	public void setDiasFaltantesVerificacion(Integer diasFaltantesVerificacion) {
		this.diasFaltantesVerificacion = diasFaltantesVerificacion;
	}
	
	
	
}
