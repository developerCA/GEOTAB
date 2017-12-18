package com.tecnolpet.ot.model;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;

import java.sql.Timestamp;


/**
 * The persistent class for the incidencia_detalle_nota_pedido database table.
 * 
 */
@Entity
@Table(name="incidencia_detalle_nota_pedido")
@NamedQuery(name="IncidenciaDetalleNotaPedido.findAll", query="SELECT i FROM IncidenciaDetalleNotaPedido i")
public class IncidenciaDetalleNotaPedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codigo")
	@JsonView(ViewOT.PublicView.class)
	private Integer id;

	@Column(name="fecha_actualizacion")
	@JsonView(ViewOT.PublicView.class)
	private Timestamp fechaActualizacion;

	@Column(name="fecha_registro")
	@JsonView(ViewOT.PublicView.class)
	private Timestamp fechaRegistro;

	@ManyToOne
	@JoinColumn(name="id_detalle_nota_pedido", referencedColumnName="codigo")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)
	@JsonView(ViewOT.PublicView.class)
	private DetalleNotaPedido detalleNotaPedido;
	@JsonView(ViewOT.PublicView.class)
	private String observaciones;

	@ManyToOne
	@JoinColumn(name="tipo", referencedColumnName="codigo")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)
	@JsonView(ViewOT.PublicView.class)
	private Catalogo tipo;

	@Column(name="usuario_modificado")
	@JsonView(ViewOT.PublicView.class)
	private Integer usuarioModificado;

	@Column(name="usuario_registro")
	@JsonView(ViewOT.PublicView.class)
	private Integer usuarioRegistro;

	public IncidenciaDetalleNotaPedido() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(Timestamp fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public Timestamp getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Timestamp fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public DetalleNotaPedido getDetalleNotaPedido() {
		return this.detalleNotaPedido;
	}

	public void setDetalleNotaPedido(DetalleNotaPedido detalleNotaPedido) {
		this.detalleNotaPedido = detalleNotaPedido;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Catalogo getTipo() {
		return this.tipo;
	}

	public void setTipo(Catalogo tipo) {
		this.tipo = tipo;
	}

	public Integer getUsuarioModificado() {
		return this.usuarioModificado;
	}

	public void setUsuarioModificado(Integer usuarioModificado) {
		this.usuarioModificado = usuarioModificado;
	}

	public Integer getUsuarioRegistro() {
		return this.usuarioRegistro;
	}

	public void setUsuarioRegistro(Integer usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}

}