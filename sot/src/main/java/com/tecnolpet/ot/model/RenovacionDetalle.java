package com.tecnolpet.ot.model;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;


/**
 * The persistent class for the renovacion_detalle database table.
 * 
 */
@Entity
@Table(name="renovacion_detalle")
@NamedQuery(name="RenovacionDetalle.findAll", query="SELECT r FROM RenovacionDetalle r")
public class RenovacionDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codigo")
	@JsonView(ViewOT.PublicView.class)
	private Integer id;
	@JsonView(ViewOT.PublicView.class)
	private Integer cantidad;

	

	@ManyToOne
	@JoinColumn(name="codigo_producto")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)		
	@JsonView(ViewOT.PublicView.class)
	private Producto codigoProducto;
	
	@ManyToOne
	@JoinColumn(name="codigo_producto_renovado")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)		
	@JsonView(ViewOT.PublicView.class)
	private Producto productoRenovado;
	
	@ManyToOne
	@JoinColumn(name="codigo_producto_venta")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)		
	@JsonView(ViewOT.PublicView.class)
	private Producto productoVenta;
	

	@ManyToOne
	@JoinColumn(name="codigo_suscripcion")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)		
	@JsonView(ViewOT.PublicView.class)
	private Suscripcion codigoSuscripcion;

	//bi-directional many-to-one association to Renovacion
	@ManyToOne
	@JoinColumn(name="codigo_renovacion")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)		
	@JsonView(ViewOT.PublicView.class)
	private Renovacion renovacion;
	
	@Column(name="numero_accesos")
	@JsonView(ViewOT.PublicView.class)
	private Integer numeroAccesos;
	@JsonView(ViewOT.PublicView.class)
	private double descuento;

	public RenovacionDetalle() {
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

	

	public Producto getCodigoProducto() {
		return this.codigoProducto;
	}

	public void setCodigoProducto(Producto codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public Suscripcion getCodigoSuscripcion() {
		return this.codigoSuscripcion;
	}

	public void setCodigoSuscripcion(Suscripcion codigoSuscripcion) {
		this.codigoSuscripcion = codigoSuscripcion;
	}

	public Renovacion getRenovacion() {
		return this.renovacion;
	}

	public void setRenovacion(Renovacion renovacion) {
		this.renovacion = renovacion;
	}

	public Integer getNumeroAccesos() {
		return numeroAccesos;
	}

	public void setNumeroAccesos(Integer numeroAccesos) {
		this.numeroAccesos = numeroAccesos;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public Producto getProductoRenovado() {
		return productoRenovado;
	}

	public Producto getProductoVenta() {
		return productoVenta;
	}

	public void setProductoRenovado(Producto productoRenovado) {
		this.productoRenovado = productoRenovado;
	}

	public void setProductoVenta(Producto productoVenta) {
		this.productoVenta = productoVenta;
	}

}