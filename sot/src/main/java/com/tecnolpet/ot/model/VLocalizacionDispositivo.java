package com.tecnolpet.ot.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

/**
 * The persistent class for the v_localizacion database table.
 * 
 */
@Entity
@Table(name = "v_localizacion_dispositivo")
public class VLocalizacionDispositivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name = "codigo_dispositivo")
	private Integer codigoDispositivo;

	@Column(name = "codigo_zona")
	private Integer codigoZona;

	private String estado;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Column(name = "hora_entrada")
	private Time horaEntrada;

	@Column(name = "hora_salida")
	private Time horaSalida;

	private Integer proceso;

	public VLocalizacionDispositivo() {
	}

	public Integer getCodigoDispositivo() {
		return this.codigoDispositivo;
	}

	public void setCodigoDispositivo(Integer codigoDispositivo) {
		this.codigoDispositivo = codigoDispositivo;
	}

	public Integer getCodigoZona() {
		return this.codigoZona;
	}

	public void setCodigoZona(Integer codigoZona) {
		this.codigoZona = codigoZona;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Time getHoraEntrada() {
		return this.horaEntrada;
	}

	public void setHoraEntrada(Time horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public Time getHoraSalida() {
		return this.horaSalida;
	}

	public void setHoraSalida(Time horaSalida) {
		this.horaSalida = horaSalida;
	}

	public Integer getProceso() {
		return this.proceso;
	}

	public void setProceso(Integer proceso) {
		this.proceso = proceso;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}