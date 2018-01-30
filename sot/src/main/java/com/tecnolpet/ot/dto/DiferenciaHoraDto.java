package com.tecnolpet.ot.dto;

import java.sql.Time;

public class DiferenciaHoraDto {

	private Time horaDiferencia;
	
	private Boolean operacion;

	public DiferenciaHoraDto(){
		this.operacion=Boolean.TRUE;
	}
	public Time getHoraDiferencia() {
		return horaDiferencia;
	}

	public void setHoraDiferencia(Time horaDiferencia) {
		this.horaDiferencia = horaDiferencia;
	}

	public Boolean getOperacion() {
		return operacion;
	}

	public void setOperacion(Boolean operacion) {
		this.operacion = operacion;
	}
	
	
}
