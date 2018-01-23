package com.tecnolpet.ot.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the localizacion_dispositivo database table.
 * 
 */
@Entity
@Table(name="localizacion_dispositivo")
public class LocalizacionDispositivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long codigo;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Column(name="hora_entrada")
	private Time horaEntrada;

	@Column(name="hora_salida")
	private Time horaSalida;

	private Integer proceso;
	
	@Column(name = "numero_vuelta")
	private Integer numeroVuelta;
	
	private Integer tiempo;
	
	@Column(name="tiempo_real")
	private Time tiempoReal;


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

	public LocalizacionDispositivo() {
	}

	public Long getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
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

	public Integer getNumeroVuelta() {
		return numeroVuelta;
	}

	public void setNumeroVuelta(Integer numeroVuelta) {
		this.numeroVuelta = numeroVuelta;
	}

	public Integer getTiempo() {
		return tiempo;
	}

	public void setTiempo(Integer tiempo) {
		this.tiempo = tiempo;
	}

	public Time getTiempoReal() {
		return tiempoReal;
	}

	public void setTiempoReal(Time tiempoReal) {
		this.tiempoReal = tiempoReal;
	}

}