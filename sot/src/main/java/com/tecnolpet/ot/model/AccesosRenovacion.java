package com.tecnolpet.ot.model;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;


/**
 * The persistent class for the accesos_renovacion database table.
 * 
 */
@Entity
@Table(name="accesos_renovacion")
@NamedQuery(name="AccesosRenovacion.findAll", query="SELECT a FROM AccesosRenovacion a")
public class AccesosRenovacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codigo")
	@JsonView(ViewOT.PublicView.class)
	private Integer id;
	@JsonView(ViewOT.PublicView.class)
	private String clave;
	@JsonView(ViewOT.PublicView.class)
	private String email;
	@JsonView(ViewOT.PublicView.class)
	private String nombre;
	@JsonView(ViewOT.PublicView.class)
	private String usuario;

	//bi-directional many-to-one association to Catalogo
	@ManyToOne
	@JoinColumn(name="tipo_acceso")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)		
	@JsonView(ViewOT.PublicView.class)
	private Catalogo tipoAcceso;

	//bi-directional many-to-one association to Catalogo
	@ManyToOne
	@JoinColumn(name="estado")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)		
	@JsonView(ViewOT.PublicView.class)
	private Catalogo estado;

	//bi-directional many-to-one association to RenovacionDetalle
	@ManyToOne
	@JoinColumn(name="codigo_detalle_renovacion")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)		
	@JsonView(ViewOT.PublicView.class)
	private RenovacionDetalle renovacionDetalle;

	public AccesosRenovacion() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Catalogo getTipoAcceso() {
		return this.tipoAcceso;
	}

	public void setTipoAcceso(Catalogo tipoAcceso) {
		this.tipoAcceso = tipoAcceso;
	}

	public Catalogo getEstado() {
		return this.estado;
	}

	public void setEstado(Catalogo estado) {
		this.estado = estado;
	}

	public RenovacionDetalle getRenovacionDetalle() {
		return this.renovacionDetalle;
	}

	public void setRenovacionDetalle(RenovacionDetalle renovacionDetalle) {
		this.renovacionDetalle = renovacionDetalle;
	}

}