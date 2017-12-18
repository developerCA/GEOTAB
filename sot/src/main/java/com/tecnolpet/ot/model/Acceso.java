package com.tecnolpet.ot.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;


/**
 * The persistent class for the acceso database table.
 * 
 */
@Entity
public class Acceso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codigo")
	@JsonView(ViewOT.PublicView.class)
	private Long id;
	@JsonView(ViewOT.PublicView.class)
	private String clave;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_inicio")
	@JsonView(ViewOT.PublicView.class)
	private Date fechaInicio;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_vencimiento")
	@JsonView(ViewOT.PublicView.class)
	private Date fechaVencimiento;
	@JsonView(ViewOT.PublicView.class)
	private String usuario;
	@JsonView(ViewOT.PublicView.class)
	private String email;
	@JsonView(ViewOT.PublicView.class)
	private String nombre;
	
	//bi-directional many-to-one association to Catalogo
	@ManyToOne
	@JoinColumn(name="tipo_acceso")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)	
	@JsonView(ViewOT.PublicView.class)
	private Catalogo catalogoTipoAcceso;

	//bi-directional many-to-one association to Catalogo
	@ManyToOne
	@JoinColumn(name="estado")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)	
	@JsonView(ViewOT.PublicView.class)
	private Catalogo catalogoEstado;

	//bi-directional many-to-one association to Suscripcion
	@ManyToOne
	@JoinColumn(name="codigo_suscripcion")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)	
	@JsonView(ViewOT.PublicView.class)
	private Suscripcion suscripcion;
	@JsonView(ViewOT.PublicView.class)
	private Boolean activo;
	
	@Column(name="ip_inicial")
	@JsonView(ViewOT.PublicView.class)
	private String ipInicial;
	
	@Column(name="ip_final")
	@JsonView(ViewOT.PublicView.class)
	private String ipFinal;
	@JsonView(ViewOT.PublicView.class)
	private Boolean enviado;
	
	@Column(name="cantidad_ip")
	@JsonView(ViewOT.PublicView.class)
	private Integer cantidadIps;
	
	@Column(name="rango_ip")
	@JsonView(ViewOT.PublicView.class)
	private Integer rangoIp;

	public Acceso() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaVencimiento() {
		return this.fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Suscripcion getSuscripcion() {
		return this.suscripcion;
	}

	public void setSuscripcion(Suscripcion suscripcion) {
		this.suscripcion = suscripcion;
	}

	public Catalogo getCatalogoTipoAcceso() {
		return catalogoTipoAcceso;
	}

	public void setCatalogoTipoAcceso(Catalogo catalogoTipoAcceso) {
		this.catalogoTipoAcceso = catalogoTipoAcceso;
	}

	public Catalogo getCatalogoEstado() {
		return catalogoEstado;
	}

	public void setCatalogoEstado(Catalogo catalogoEstado) {
		this.catalogoEstado = catalogoEstado;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public String getIpInicial() {
		return ipInicial;
	}

	public void setIpInicial(String ipInicial) {
		this.ipInicial = ipInicial;
	}

	public String getIpFinal() {
		return ipFinal;
	}

	public void setIpFinal(String ipFinal) {
		this.ipFinal = ipFinal;
	}

	public Boolean isEnviado() {
		return enviado;
	}

	public void setEnviado(Boolean enviado) {
		this.enviado = enviado;
	}

	public Integer getCantidadIps() {
		return cantidadIps;
	}

	public void setCantidadIps(Integer cantidadIps) {
		this.cantidadIps = cantidadIps;
	}

	public Integer getRangoIp() {
		return rangoIp;
	}

	public void setRangoIp(Integer rangoIp) {
		this.rangoIp = rangoIp;
	}

}