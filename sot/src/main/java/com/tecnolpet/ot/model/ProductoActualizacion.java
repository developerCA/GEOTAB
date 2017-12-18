package com.tecnolpet.ot.model;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the producto_actualizacion database table.
 * 
 */
@Entity
@Table(name="producto_actualizacion")
@NamedQuery(name="ProductoActualizacion.findAll", query="SELECT p FROM ProductoActualizacion p")
public class ProductoActualizacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codigo")
	@JsonView(ViewOT.PublicView.class)
	private Integer id;
	
	@Column(name="actualizado_por")
	@JsonView(ViewOT.PublicView.class)
	private Integer actualizadoPor;

	@Column(name="creado_por")
	@JsonView(ViewOT.PublicView.class)
	private Integer creadoPor;

	//bi-directional many-to-one association to Producto
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

	@Column(name="version_actual")
	@JsonView(ViewOT.PublicView.class)
	private Integer versionActual;

	@Column(name="version_anterior")
	@JsonView(ViewOT.PublicView.class)
	private Integer versionAnterior;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)
	@JoinColumn(name="codigo_producto")
	@JsonView(ViewOT.PublicView.class)
	private Producto producto;

	//bi-directional many-to-one association to ProductoActualizacionDetalle
	@OneToMany(mappedBy="productoActualizacion")
	@JsonIgnore
	private List<ProductoActualizacionDetalle> productoActualizacionDetalles;
	
	public ProductoActualizacion() {
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

	public Integer getVersionActual() {
		return this.versionActual;
	}

	public void setVersionActual(Integer versionActual) {
		this.versionActual = versionActual;
	}

	public Integer getVersionAnterior() {
		return this.versionAnterior;
	}

	public void setVersionAnterior(Integer versionAnterior) {
		this.versionAnterior = versionAnterior;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public List<ProductoActualizacionDetalle> getProductoActualizacionDetalles() {
		return this.productoActualizacionDetalles;
	}

	public void setProductoActualizacionDetalles(List<ProductoActualizacionDetalle> productoActualizacionDetalles) {
		this.productoActualizacionDetalles = productoActualizacionDetalles;
	}

	public ProductoActualizacionDetalle addProductoActualizacionDetalle(ProductoActualizacionDetalle productoActualizacionDetalle) {
		getProductoActualizacionDetalles().add(productoActualizacionDetalle);
		productoActualizacionDetalle.setProductoActualizacion(this);

		return productoActualizacionDetalle;
	}

	public ProductoActualizacionDetalle removeProductoActualizacionDetalle(ProductoActualizacionDetalle productoActualizacionDetalle) {
		getProductoActualizacionDetalles().remove(productoActualizacionDetalle);
		productoActualizacionDetalle.setProductoActualizacion(null);

		return productoActualizacionDetalle;
	}

}