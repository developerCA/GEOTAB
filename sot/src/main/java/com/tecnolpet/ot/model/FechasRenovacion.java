package com.tecnolpet.ot.model;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;

import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the fechas_renovacion database table.
 * 
 */
@Entity
@Table(name="fechas_renovacion")
@NamedQuery(name="FechasRenovacion.findAll", query="SELECT f FROM FechasRenovacion f")
public class FechasRenovacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codigo")
	@JsonView(ViewOT.PublicView.class)
	private Integer id;

	@ManyToOne
	@JoinColumn(name="estado", referencedColumnName="codigo")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)		
	@JsonView(ViewOT.PublicView.class)
	private Catalogo estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_fin")
	@JsonView(ViewOT.PublicView.class)
	private Date fechaFin;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_inicio")
	@JsonView(ViewOT.PublicView.class)
	private Date fechaInicio;

	@Column(name="fecha_proceso")
	@JsonView(ViewOT.PublicView.class)
	private Timestamp fechaProceso;

	@Column(name="fecha_registro")
	@JsonView(ViewOT.PublicView.class)
	private Timestamp fechaRegistro;

	@Column(name="usuario_procesa")
	@JsonView(ViewOT.PublicView.class)
	private Integer usuarioProcesa;

	@Column(name="usuario_registro")
	@JsonView(ViewOT.PublicView.class)
	private Integer usuarioRegistro;
	
	

	public FechasRenovacion() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Catalogo getEstado() {
		return this.estado;
	}

	public void setEstado(Catalogo estado) {
		this.estado = estado;
	}

	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Timestamp getFechaProceso() {
		return this.fechaProceso;
	}

	public void setFechaProceso(Timestamp fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

	public Timestamp getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Timestamp fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Integer getUsuarioProcesa() {
		return this.usuarioProcesa;
	}

	public void setUsuarioProcesa(Integer usuarioProcesa) {
		this.usuarioProcesa = usuarioProcesa;
	}

	public Integer getUsuarioRegistro() {
		return this.usuarioRegistro;
	}

	public void setUsuarioRegistro(Integer usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}

	

}