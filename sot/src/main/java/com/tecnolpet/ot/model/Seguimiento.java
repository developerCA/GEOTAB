package com.tecnolpet.ot.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;


/**
 * The persistent class for the seguimiento database table.
 * 
 */
@Entity
@NamedQuery(name="Seguimiento.findAll", query="SELECT s FROM Seguimiento s")
public class Seguimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codigo")
	@JsonView(ViewOT.PublicView.class)
	private Integer id;
	@JsonView(ViewOT.PublicView.class)
	private Integer cantidad;
	@JsonView(ViewOT.PublicView.class)
	private String observacion;

	//bi-directional many-to-one association to Catalogo
	@ManyToOne
	@JoinColumn(name="estado")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)
	@JsonView(ViewOT.PublicView.class)
	private Catalogo catalogo;

	//bi-directional many-to-one association to TareaDetalleNotaPedido
	@ManyToOne
	@JoinColumn(name="codigo_tarea_detalle_nota_pedido")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)
	@JsonView(ViewOT.PublicView.class)
	private TareaDetalleNotaPedido tareaDetalleNotaPedido;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha")
	@JsonView(ViewOT.PublicView.class)
	private Date fecha;

	public Seguimiento() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Catalogo getCatalogo() {
		return this.catalogo;
	}

	public void setCatalogo(Catalogo catalogo) {
		this.catalogo = catalogo;
	}

	public TareaDetalleNotaPedido getTareaDetalleNotaPedido() {
		return this.tareaDetalleNotaPedido;
	}

	public void setTareaDetalleNotaPedido(TareaDetalleNotaPedido tareaDetalleNotaPedido) {
		this.tareaDetalleNotaPedido = tareaDetalleNotaPedido;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}