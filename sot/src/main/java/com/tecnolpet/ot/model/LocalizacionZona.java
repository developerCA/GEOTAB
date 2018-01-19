package com.tecnolpet.ot.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the localizacion_zona database table.
 * 
 */
@Entity
@Table(name="localizacion_zona")
public class LocalizacionZona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long codigo;

	private String estado;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Column(name="fecha_utc")
	private String fechaUtc;

	private Time hora;

	private Integer proceso;
	
	@Column(name = "numero_vuelta")
	private Integer numeroVuelta;
	
	//bi-directional many-to-one association to Dispositivo
	@ManyToOne
	@JoinColumn(name="codigo_dispositivo")
	private Dispositivo dispositivo;

	//bi-directional many-to-one association to TipoHorario
	@ManyToOne
	@JoinColumn(name="codigo_tipo_horario")
	private TipoHorario tipoHorario;

	//bi-directional many-to-one association to Zona
	@ManyToOne
	@JoinColumn(name="codigo_zona")
	private Zona zona;

	public LocalizacionZona() {
	}

	public Long getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
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

	public String getFechaUtc() {
		return this.fechaUtc;
	}

	public void setFechaUtc(String fechaUtc) {
		this.fechaUtc = fechaUtc;
	}

	public Time getHora() {
		return this.hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public Dispositivo getDispositivo() {
		return this.dispositivo;
	}

	public void setDispositivo(Dispositivo dispositivo) {
		this.dispositivo = dispositivo;
	}

	public TipoHorario getTipoHorario() {
		return this.tipoHorario;
	}

	public void setTipoHorario(TipoHorario tipoHorario) {
		this.tipoHorario = tipoHorario;
	}

	public Zona getZona() {
		return this.zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}

	public Integer getProceso() {
		return proceso;
	}

	public void setProceso(Integer proceso) {
		this.proceso = proceso;
	}

	public Integer getNumeroVuelta() {
		return numeroVuelta;
	}

	public void setNumeroVuelta(Integer numeroVuelta) {
		this.numeroVuelta = numeroVuelta;
	}

}