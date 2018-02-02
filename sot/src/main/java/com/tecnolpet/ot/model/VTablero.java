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
@Table(name = "v_tablero")
public class VTablero implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long codigo;

	@Column(name = "codigo_ruta")
	private Integer codigoRuta;

	@Column(name = "codigo_dispositivo")
	private Integer codigoDispositivo;

	@Column(name = "codigo_zona")
	private Integer codigoZona;

	private String dispositivo;

	@Column(name = "hora_entrada")
	private Time horaEntrada;

	@Column(name = "hora_salida")
	private Time horaSalida;

	private String ruta;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Column(name = "numero_vuelta")
	private Integer numeroVuelta;

	private String orden;

	private String zona;

	private Integer tiempo;

	@Column(name = "hora_programadatmp")
	private Time horaProgramadaTmp;

	@Column(name = "diferencia_tiempo")
	private Time diferenciaTiempo;

	@Column(name = "hora_programada")
	private Time horaProgramada;

	@Column(name = "cumple_tiempo")
	private Boolean cumpleTiempo;

	@Column(name = "inicio_zona")
	private Boolean inicioZona;

	@Column(name = "codigo_enlace")
	private Long codigoEnlace;

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

	public Time getDiferenciaTiempo() {
		return diferenciaTiempo;
	}

	public void setDiferenciaTiempo(Time diferenciaTiempo) {
		this.diferenciaTiempo = diferenciaTiempo;
	}

	public String getOrden() {
		return orden;
	}

	public void setOrden(String orden) {
		this.orden = orden;
	}

	public Integer getTiempo() {
		return tiempo;
	}

	public void setTiempo(Integer tiempo) {
		this.tiempo = tiempo;
	}

	public Boolean getInicioZona() {
		return inicioZona;
	}

	public void setInicioZona(Boolean inicioZona) {
		this.inicioZona = inicioZona;
	}

	public Time getHoraProgramada() {
		return horaProgramada;
	}

	public void setHoraProgramada(Time horaProgramada) {
		this.horaProgramada = horaProgramada;
	}

	public Boolean getCumpleTiempo() {
		return cumpleTiempo;
	}

	public void setCumpleTiempo(Boolean cumpleTiempo) {
		this.cumpleTiempo = cumpleTiempo;
	}

	public Long getCodigoEnlace() {
		return codigoEnlace;
	}

	public void setCodigoEnlace(Long codigoEnlace) {
		this.codigoEnlace = codigoEnlace;
	}

	public Time getHoraProgramadaTmp() {
		return horaProgramadaTmp;
	}

	public void setHoraProgramadaTmp(Time horaProgramadaTmp) {
		this.horaProgramadaTmp = horaProgramadaTmp;
	}

}