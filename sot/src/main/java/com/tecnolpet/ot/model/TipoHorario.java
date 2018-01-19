package com.tecnolpet.ot.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.List;


/**
 * The persistent class for the tipo_horario database table.
 * 
 */
@Entity
@Table(name="tipo_horario")
public class TipoHorario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigo;

	@Column(name="hora_fin")
	private Time horaFin;

	@Column(name="hora_inicio")
	private Time horaInicio;

	@Column(name="nombre_tipo_hora")
	private String nombreTipoHora;

	//bi-directional many-to-one association to LocalizacionZona
	@OneToMany(mappedBy="tipoHorario")
	private List<LocalizacionZona> localizacionZonas;

	public TipoHorario() {
	}

	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Time getHoraFin() {
		return this.horaFin;
	}

	public void setHoraFin(Time horaFin) {
		this.horaFin = horaFin;
	}

	public Time getHoraInicio() {
		return this.horaInicio;
	}

	public void setHoraInicio(Time horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getNombreTipoHora() {
		return this.nombreTipoHora;
	}

	public void setNombreTipoHora(String nombreTipoHora) {
		this.nombreTipoHora = nombreTipoHora;
	}

	public List<LocalizacionZona> getLocalizacionZonas() {
		return this.localizacionZonas;
	}

	public void setLocalizacionZonas(List<LocalizacionZona> localizacionZonas) {
		this.localizacionZonas = localizacionZonas;
	}

	public LocalizacionZona addLocalizacionZona(LocalizacionZona localizacionZona) {
		getLocalizacionZonas().add(localizacionZona);
		localizacionZona.setTipoHorario(this);

		return localizacionZona;
	}

	public LocalizacionZona removeLocalizacionZona(LocalizacionZona localizacionZona) {
		getLocalizacionZonas().remove(localizacionZona);
		localizacionZona.setTipoHorario(null);

		return localizacionZona;
	}

}