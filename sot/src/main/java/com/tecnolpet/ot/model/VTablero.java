package com.tecnolpet.ot.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the v_tablero database table.
 * 
 */
@Entity
@Table(name="v_tablero")

public class VTablero implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long codigo;

	@Column(name="codigo_ruta")
	private Integer codigoRuta;
	
	@Column(name="codigo_dispositivo")
	private Integer codigoDispositivo;

	@Column(name="codigo_zona")
	private Integer codigoZona;

	private String dispositivo;

	@Column(name="hora_entrada")
	private Time horaEntrada;

	@Column(name="hora_salida")
	private Time horaSalida;

	private String ruta;
	
	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Column(name="tipo_horario")
	private String tipoHorario;

	@Column(name = "numero_vuelta")
	private Integer numeroVuelta;
	
	private String zona;

	public VTablero() {
	}

	public Long getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigoRuta() {
		return this.codigoRuta;
	}

	public void setCodigoRuta(Integer codigoRuta) {
		this.codigoRuta = codigoRuta;
	}

	public Integer getCodigoZona() {
		return this.codigoZona;
	}

	public void setCodigoZona(Integer codigoZona) {
		this.codigoZona = codigoZona;
	}

	public String getDispositivo() {
		return this.dispositivo;
	}

	public void setDispositivo(String dispositivo) {
		this.dispositivo = dispositivo;
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

	public String getRuta() {
		return this.ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public String getTipoHorario() {
		return this.tipoHorario;
	}

	public void setTipoHorario(String tipoHorario) {
		this.tipoHorario = tipoHorario;
	}

	public String getZona() {
		return this.zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getCodigoDispositivo() {
		return codigoDispositivo;
	}

	public void setCodigoDispositivo(Integer codigoDispositivo) {
		this.codigoDispositivo = codigoDispositivo;
	}

	public Integer getNumeroVuelta() {
		return numeroVuelta;
	}

	public void setNumeroVuelta(Integer numeroVuelta) {
		this.numeroVuelta = numeroVuelta;
	}

	

}