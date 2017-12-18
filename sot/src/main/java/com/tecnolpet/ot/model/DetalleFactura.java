package com.tecnolpet.ot.model;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;


/**
 * The persistent class for the detalle_factura database table.
 * 
 */
@Entity
@Table(name="detalle_factura")
@NamedQuery(name="DetalleFactura.findAll", query="SELECT d FROM DetalleFactura d")
public class DetalleFactura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codigo")
	@JsonView(ViewOT.PublicView.class)
	private Integer id;
	@JsonView(ViewOT.PublicView.class)
	private Integer cantidad;

	@Column(name="costo_unitario")
	@JsonView(ViewOT.PublicView.class)
	private double costoUnitario;
	@JsonView(ViewOT.PublicView.class)
	private double descuento;
	@JsonView(ViewOT.PublicView.class)
	private double subtotal;
	@JsonView(ViewOT.PublicView.class)
	private double total;

	@Column(name="valor_descuento")
	@JsonView(ViewOT.PublicView.class)
	private double valorDescuento;

	@Column(name="valor_impuesto")
	@JsonView(ViewOT.PublicView.class)
	private double valorImpuesto;

	@Column(name="valor_impuesto_0")
	@JsonView(ViewOT.PublicView.class)
	private double valorImpuesto0;

	@Column(name="valor_real")
	@JsonView(ViewOT.PublicView.class)
	private double valorReal;

	//bi-directional many-to-one association to Factura
	@ManyToOne
	@JoinColumn(name="codigo_factura")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)	
	@JsonView(ViewOT.PublicView.class)
	private Factura factura;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="codigo_producto")
	@JsonView(ViewOT.PublicView.class)
	private Producto producto;

	public DetalleFactura() {
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

	public double getCostoUnitario() {
		return this.costoUnitario;
	}

	public void setCostoUnitario(double costoUnitario) {
		this.costoUnitario = costoUnitario;
	}

	public double getDescuento() {
		return this.descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public double getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getTotal() {
		return this.total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getValorDescuento() {
		return this.valorDescuento;
	}

	public void setValorDescuento(double valorDescuento) {
		this.valorDescuento = valorDescuento;
	}

	public double getValorImpuesto() {
		return this.valorImpuesto;
	}

	public void setValorImpuesto(double valorImpuesto) {
		this.valorImpuesto = valorImpuesto;
	}

	public double getValorImpuesto0() {
		return this.valorImpuesto0;
	}

	public void setValorImpuesto0(double valorImpuesto0) {
		this.valorImpuesto0 = valorImpuesto0;
	}

	public double getValorReal() {
		return this.valorReal;
	}

	public void setValorReal(double valorReal) {
		this.valorReal = valorReal;
	}

	public Factura getFactura() {
		return this.factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}