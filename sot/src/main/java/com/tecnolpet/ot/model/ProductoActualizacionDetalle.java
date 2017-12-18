package com.tecnolpet.ot.model;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;

import java.util.Date;


/**
 * The persistent class for the producto_actualizacion_detalle database table.
 * 
 */
@Entity
@Table(name="producto_actualizacion_detalle")
@NamedQuery(name="ProductoActualizacionDetalle.findAll", query="SELECT p FROM ProductoActualizacionDetalle p")
public class ProductoActualizacionDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codigo")
	@JsonView(ViewOT.PublicView.class)
	private Integer id;
	
	@Column(name="actualizado_por")
	@JsonView(ViewOT.PublicView.class)
	private Integer actualizadoPor;

	@Column(name="codigo_barras")
	@JsonView(ViewOT.PublicView.class)
	private String codigoBarras;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)
	@JoinColumn(name="codigo_suscripcion")
	@JsonView(ViewOT.PublicView.class)
	private Suscripcion suscripcion;

	@Column(name="creado_por")
	@JsonView(ViewOT.PublicView.class)
	private Integer creadoPor;

	@ManyToOne
	@JoinColumn(name="estado")
	@JsonView(ViewOT.PublicView.class)
	private Catalogo estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_actualizacion")
	@JsonView(ViewOT.PublicView.class)
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	@JsonView(ViewOT.PublicView.class)
	private Date fechaCreacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_recibido")
	@JsonView(ViewOT.PublicView.class)
	private Date fechaRecibido;
	@JsonView(ViewOT.PublicView.class)
	private String observacion;
	@JsonView(ViewOT.PublicView.class)
	private Boolean recibido;
	@JsonView(ViewOT.PublicView.class)
	private Integer version;

	//bi-directional many-to-one association to ProductoActualizacion
	@ManyToOne
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)
	@JoinColumn(name="codigo_producto_actualizacion")
	@JsonView(ViewOT.PublicView.class)
	private ProductoActualizacion productoActualizacion;
	
	//Variable que indica (solo en memoria) si un detalle es una actualización
	@Transient
	@JsonView(ViewOT.PublicView.class)
	private boolean esActualizacion;

	public boolean isEsActualizacion() {
		return esActualizacion;
	}

	public void setEsActualizacion(boolean esActualizacion) {
		this.esActualizacion = esActualizacion;
	}

	public ProductoActualizacionDetalle() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getActualizadoPor() {
		return this.actualizadoPor;
	}

	public void setActualizadoPor(Integer actualizadoPor) {
		this.actualizadoPor = actualizadoPor;
	}

	public String getCodigoBarras() {
		return this.codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public Integer getCreadoPor() {
		return this.creadoPor;
	}

	public void setCreadoPor(Integer creadoPor) {
		this.creadoPor = creadoPor;
	}

	public Catalogo getEstado() {
		return this.estado;
	}

	public void setEstado(Catalogo estado) {
		this.estado = estado;
	}

	public Date getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaRecibido() {
		return this.fechaRecibido;
	}

	public void setFechaRecibido(Date fechaRecibido) {
		this.fechaRecibido = fechaRecibido;
	}

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Boolean getRecibido() {
		return this.recibido;
	}

	public void setRecibido(Boolean recibido) {
		this.recibido = recibido;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public ProductoActualizacion getProductoActualizacion() {
		return this.productoActualizacion;
	}

	public void setProductoActualizacion(ProductoActualizacion productoActualizacion) {
		this.productoActualizacion = productoActualizacion;
	}

	public Suscripcion getSuscripcion() {
		return this.suscripcion;
	}

	public void setSuscripcion(Suscripcion suscripcion) {
		this.suscripcion = suscripcion;
	}

}