package com.tecnolpet.ot.model;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the renovacion database table.
 * 
 */
@Entity
@NamedQuery(name="Renovacion.findAll", query="SELECT r FROM Renovacion r")
public class Renovacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codigo")
	@JsonView(ViewOT.PublicView.class)
	private Integer id;

	@ManyToOne
	@JoinColumn(name="codigo_telerenovador")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)		
	@JsonView(ViewOT.PublicView.class)
	private Telerenovador telerenovador;

	@ManyToOne
	@JoinColumn(name="estado_renovacion")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)		
	@JsonView(ViewOT.PublicView.class)
	private Catalogo estadoRenovacion;
	
	@ManyToOne
	@JoinColumn(name="codigo_nota_pedido")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)		
	@JsonView(ViewOT.PublicView.class)
	private NotaPedido notaPedido;

	@Column(name="fecha_registro")
	@JsonView(ViewOT.PublicView.class)
	private Timestamp fechaRegistro;

	@Column(name="usuario_registro")
	@JsonView(ViewOT.PublicView.class)
	private Integer usuarioRegistro;

	//bi-directional many-to-one association to RenovacionDetalle
	@OneToMany(mappedBy="renovacion")
	@JsonIgnore
	private List<RenovacionDetalle> renovacionDetalles;

	public Renovacion() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public Catalogo getEstadoRenovacion() {
		return this.estadoRenovacion;
	}

	public void setEstadoRenovacion(Catalogo estadoRenovacion) {
		this.estadoRenovacion = estadoRenovacion;
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

	public List<RenovacionDetalle> getRenovacionDetalles() {
		return this.renovacionDetalles;
	}

	public void setRenovacionDetalles(List<RenovacionDetalle> renovacionDetalles) {
		this.renovacionDetalles = renovacionDetalles;
	}

	public RenovacionDetalle addRenovacionDetalle(RenovacionDetalle renovacionDetalle) {
		getRenovacionDetalles().add(renovacionDetalle);
		renovacionDetalle.setRenovacion(this);

		return renovacionDetalle;
	}

	public RenovacionDetalle removeRenovacionDetalle(RenovacionDetalle renovacionDetalle) {
		getRenovacionDetalles().remove(renovacionDetalle);
		renovacionDetalle.setRenovacion(null);

		return renovacionDetalle;
	}

	public Telerenovador getTelerenovador() {
		return telerenovador;
	}

	public void setTelerenovador(Telerenovador telerenovador) {
		this.telerenovador = telerenovador;
	}

	public NotaPedido getNotaPedido() {
		return notaPedido;
	}

	public void setNotaPedido(NotaPedido notaPedido) {
		this.notaPedido = notaPedido;
	}

}