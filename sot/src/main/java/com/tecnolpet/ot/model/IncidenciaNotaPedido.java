package com.tecnolpet.ot.model;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;

import java.sql.Timestamp;


/**
 * The persistent class for the incidencia_nota_pedido database table.
 * 
 */
@Entity
@Table(name="incidencia_nota_pedido")
@NamedQuery(name="IncidenciaNotaPedido.findAll", query="SELECT i FROM IncidenciaNotaPedido i")
public class IncidenciaNotaPedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "codigo")
	@JsonView(ViewOT.PublicView.class)
	private Integer id;

	@Column(name="fecha_registro")
	@JsonView(ViewOT.PublicView.class)
	private Timestamp fechaRegistro;

	@ManyToOne
	@JoinColumn(name="id_nota_pedido", referencedColumnName="codigo")
	@JsonView(ViewOT.PublicView.class)
	private NotaPedido notaPedido;
	@JsonView(ViewOT.PublicView.class)
	private String observaciones;
	@JsonView(ViewOT.PublicView.class)
	private Integer tipo;

	@Column(name="usuario_registro")
	@JsonView(ViewOT.PublicView.class)
	private Integer usuarioRegistro;

	@Column(name="usuario_modificado")
	@JsonView(ViewOT.PublicView.class)
	private Integer usuarioModificado;
	
	@Column(name="fecha_actualizacion")
	@JsonView(ViewOT.PublicView.class)
	private Timestamp fechaActualizacion;

	public IncidenciaNotaPedido() {
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

	public NotaPedido getNotaPedido() {
		return this.notaPedido;
	}

	public void setIdNotaPedido(NotaPedido notaPedido) {
		this.notaPedido = notaPedido;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Integer getTipo() {
		return this.tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public Integer getUsuarioRegistro() {
		return this.usuarioRegistro;
	}

	public void setUsuarioRegistro(Integer usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}

	public Integer getUsuarioModificado() {
		return usuarioModificado;
	}

	public void setUsuarioModificado(Integer usuarioModificado) {
		this.usuarioModificado = usuarioModificado;
	}

	public Timestamp getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Timestamp fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

}