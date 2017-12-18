package com.tecnolpet.ot.model;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;

import java.sql.Timestamp;


/**
 * The persistent class for the distribucion database table.
 * 
 */
@Entity
public class Distribucion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codigo")
	@JsonView(ViewOT.PublicView.class)
	private Integer id;

	@ManyToOne
	@JoinColumn(name="estado")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)	
	@JsonView(ViewOT.PublicView.class)
	private Catalogo estado;

	@Column(name="fecha_registro")
	@JsonView(ViewOT.PublicView.class)
	private Timestamp fechaRegistro;

	@Column(name="usuario_registro")
	@JsonView(ViewOT.PublicView.class)
	private Integer usuarioRegistro;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="codigo_cliente")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)	
	@JsonView(ViewOT.PublicView.class)
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="codigo_sucursal")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)	
	@JsonView(ViewOT.PublicView.class)
	private Sucursal sucursal;

	//bi-directional many-to-one association to SubCategoria
	@ManyToOne
	@JoinColumn(name="codigo_subcategoria")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)	
	@JsonView(ViewOT.PublicView.class)
	private SubCategoria subCategoria;

	//bi-directional many-to-one association to Suscripcion
	@ManyToOne
	@JoinColumn(name="codigo_suscripcion")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)	
	@JsonView(ViewOT.PublicView.class)
	private Suscripcion suscripcion;

	//bi-directional many-to-one association to Telerenovador
	@ManyToOne
	@JoinColumn(name="codigo_telerenovador")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)	
	@JsonView(ViewOT.PublicView.class)
	private Telerenovador telerenovador;
	
	@ManyToOne
	@JoinColumn(name="codigo_fecharenovacion")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)	
	@JsonView(ViewOT.PublicView.class)
	private FechasRenovacion fechaRenovacion;

	
	@Column(name="motivo")
	@JsonView(ViewOT.PublicView.class)
	private String motivo;
	
	public Distribucion() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public Timestamp getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Timestamp fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Integer getUsuarioRegistro() {
		return this.usuarioRegistro;
	}

	public void setUsuarioRegistro(Integer usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public SubCategoria getSubCategoria() {
		return this.subCategoria;
	}

	public void setSubCategoria(SubCategoria subCategoria) {
		this.subCategoria = subCategoria;
	}

	public Suscripcion getSuscripcion() {
		return this.suscripcion;
	}

	public void setSuscripcion(Suscripcion suscripcion) {
		this.suscripcion = suscripcion;
	}

	public Telerenovador getTelerenovador() {
		return this.telerenovador;
	}

	public void setTelerenovador(Telerenovador telerenovador) {
		this.telerenovador = telerenovador;
	}

	public Catalogo getEstado() {
		return estado;
	}

	public void setEstado(Catalogo estado) {
		this.estado = estado;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public FechasRenovacion getFechaRenovacion() {
		return fechaRenovacion;
	}

	public void setFechaRenovacion(FechasRenovacion fechaRenovacion) {
		this.fechaRenovacion = fechaRenovacion;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

}