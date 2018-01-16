package com.tecnolpet.ot.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the localizacion database table.
 * 
 */
@Entity
public class Localizacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long codigo;

	@Column(name="fecha_hora")
	private String fechaHora;

	private double latitude;

	private double longitud;
	
	private String estado;
	

	//bi-directional many-to-one association to Dispositivo
	@ManyToOne
	@JoinColumn(name="codigo_dispositivo")
	private Dispositivo dispositivo;

	public Localizacion() {
	}

	public Long getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getFechaHora() {
		return this.fechaHora;
	}

	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitud() {
		return this.longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public Dispositivo getDispositivo() {
		return this.dispositivo;
	}

	public void setDispositivo(Dispositivo dispositivo) {
		this.dispositivo = dispositivo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}