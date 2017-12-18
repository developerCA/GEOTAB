package com.tecnolpet.ot.model;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;

import java.util.List;


/**
 * The persistent class for the enlace database table.
 * 
 */
@Entity
@NamedQuery(name="Enlace.findAll", query="SELECT e FROM Enlace e")
public class Enlace implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codigo")
	@JsonView(ViewOT.PublicView.class)
	private Integer id;
	@JsonView(ViewOT.PublicView.class)
	private String apellidos;
	@JsonView(ViewOT.PublicView.class)
	private String celular;

	@Column(name="departamento_trabajo")
	@JsonView(ViewOT.PublicView.class)
	private String departamentoTrabajo;
	@JsonView(ViewOT.PublicView.class)
	private String direccion;
	@JsonView(ViewOT.PublicView.class)
	private String email;
	@JsonView(ViewOT.PublicView.class)
	private String extension;

	@Column(name="horario_atencion")
	@JsonView(ViewOT.PublicView.class)
	private String horarioAtencion;
	@JsonView(ViewOT.PublicView.class)
	private String identificacion;
	@JsonView(ViewOT.PublicView.class)
	private String nombres;
	@JsonView(ViewOT.PublicView.class)
	private String observaciones;

	@Column(name="telefono_1")
	@JsonView(ViewOT.PublicView.class)
	private String telefono1;

	@Column(name="telefono_2")
	@JsonView(ViewOT.PublicView.class)
	private String telefono2;

	//bi-directional many-to-one association to Ciudad
	@ManyToOne
	@JoinColumn(name="codigo_ciudad")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)	
	@JsonProperty(value="ciudadE")
	@JsonView(ViewOT.PublicView.class)
	private Ciudad ciudad;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="codigo_cliente")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)	
	@JsonProperty(value="clienteE")
	@JsonView(ViewOT.PublicView.class)
	private Cliente cliente;

	//bi-directional many-to-one association to Pais
	@ManyToOne
	@JoinColumn(name="codigo_pais")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)
	@JsonProperty(value="paisE")
	@JsonView(ViewOT.PublicView.class)
	private Pais pais;

	//bi-directional many-to-one association to Profesion
	@ManyToOne
	@JoinColumn(name="codigo_profesion")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)	
	@JsonProperty(value="profesionE")
	@JsonView(ViewOT.PublicView.class)
	private Profesion profesion;

	//bi-directional many-to-one association to Provincia
	@ManyToOne
	@JoinColumn(name="codigo_provincia")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)
	@JsonProperty(value="provinciaE")
	@JsonView(ViewOT.PublicView.class)
	private Provincia provincia;

	//bi-directional many-to-one association to Suscripcion
	@OneToMany(mappedBy="enlace")
	@JsonIgnore
	private List<Suscripcion> suscripciones;

	public Enlace() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getDepartamentoTrabajo() {
		return this.departamentoTrabajo;
	}

	public void setDepartamentoTrabajo(String departamentoTrabajo) {
		this.departamentoTrabajo = departamentoTrabajo;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getExtension() {
		return this.extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getHorarioAtencion() {
		return this.horarioAtencion;
	}

	public void setHorarioAtencion(String horarioAtencion) {
		this.horarioAtencion = horarioAtencion;
	}

	public String getIdentificacion() {
		return this.identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getTelefono1() {
		return this.telefono1;
	}

	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}

	public String getTelefono2() {
		return this.telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	public Ciudad getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Pais getPais() {
		return this.pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Profesion getProfesion() {
		return this.profesion;
	}

	public void setProfesion(Profesion profesion) {
		this.profesion = profesion;
	}

	public Provincia getProvincia() {
		return this.provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public List<Suscripcion> getSuscripciones() {
		return this.suscripciones;
	}

	public void setSuscripciones(List<Suscripcion> suscripciones) {
		this.suscripciones = suscripciones;
	}

	public Suscripcion addSuscripcion(Suscripcion suscripcion) {
		getSuscripciones().add(suscripcion);
		suscripcion.setEnlace(this);

		return suscripcion;
	}

	public Suscripcion removeSuscripcion(Suscripcion suscripcion) {
		getSuscripciones().remove(suscripcion);
		suscripcion.setEnlace(null);

		return suscripcion;
	}

}