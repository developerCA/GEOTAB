package com.tecnolpet.ot.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;

/**
 * The persistent class for the detalle_nota_pedido database table.
 * 
 */
@Entity
@Table(name = "detalle_nota_pedido")
public class DetalleNotaPedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo")
	@JsonView(ViewOT.PublicView.class)
	private Integer id;
	@JsonView(ViewOT.PublicView.class)
	private Integer cantidad;

	@Column(name = "cantidad_real")
	@JsonView(ViewOT.PublicView.class)
	private Integer cantidadReal;

	@Column(name = "costo_unitario")
	@JsonView(ViewOT.PublicView.class)
	private double costoUnitario;
	@JsonView(ViewOT.PublicView.class)
	private double descuento;

	@Transient
	@JsonView(ViewOT.PublicView.class)
	private Double descuentoAnt;

	@Transient
	@JsonView(ViewOT.PublicView.class)
	private Boolean visible;

	@Transient
	@JsonView(ViewOT.PublicView.class)
	private Boolean renovacion;

	public Boolean getRenovacion() {
		return renovacion;
	}

	public void setRenovacion(Boolean renovacion) {
		this.renovacion = renovacion;
	}

	@Column(name = "numero_accesos")
	@JsonView(ViewOT.PublicView.class)
	private Integer numeroAccesos;

	@Column(name = "numero_meses")
	@JsonView(ViewOT.PublicView.class)
	private Integer numeroMeses;
	@JsonView(ViewOT.PublicView.class)
	private double subtotal;
	@JsonView(ViewOT.PublicView.class)
	private double total;

	@Column(name = "valor_descuento")
	@JsonView(ViewOT.PublicView.class)
	private double valorDescuento;

	@Column(name = "valor_impuesto")
	@JsonView(ViewOT.PublicView.class)
	private double valorImpuesto;

	@Column(name = "valor_impuesto_0")
	@JsonView(ViewOT.PublicView.class)
	private double valorImpuesto0;

	@Column(name = "valor_real")
	@JsonView(ViewOT.PublicView.class)
	private double valorReal;

	// bi-directional many-to-one association to NotaPedido
	@ManyToOne
	@JoinColumn(name = "codigo_nota_pedido")
	@JsonView(ViewOT.PublicView.class)
	private NotaPedido notaPedido;

	// bi-directional many-to-one association to Catalogo
	@ManyToOne
	@JoinColumn(name = "id_catalogo")
	@JsonView(ViewOT.PublicView.class)
	private Catalogo catalogo;

	// bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name = "codigo_producto")
	@JsonView(ViewOT.PublicView.class)
	private Producto producto;

	@Transient
	@JsonView(ViewOT.PublicView.class)
	private Boolean eliminaNotaPedido;

	public DetalleNotaPedido() {
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

	public Integer getCantidadReal() {
		return this.cantidadReal;
	}

	public void setCantidadReal(Integer cantidadReal) {
		this.cantidadReal = cantidadReal;
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

	public Integer getNumeroAccesos() {
		return this.numeroAccesos;
	}

	public void setNumeroAccesos(Integer numeroAccesos) {
		this.numeroAccesos = numeroAccesos;
	}

	public Integer getNumeroMeses() {
		return this.numeroMeses;
	}

	public void setNumeroMeses(Integer numeroMeses) {
		this.numeroMeses = numeroMeses;
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

	public NotaPedido getNotaPedido() {
		return this.notaPedido;
	}

	public void setNotaPedido(NotaPedido notaPedido) {
		this.notaPedido = notaPedido;
	}

	public Catalogo getCatalogo() {
		return this.catalogo;
	}

	public void setCatalogo(Catalogo catalogo) {
		this.catalogo = catalogo;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Boolean getEliminaNotaPedido() {
		return eliminaNotaPedido;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public void setEliminaNotaPedido(Boolean eliminaNotaPedido) {
		this.eliminaNotaPedido = eliminaNotaPedido;
	}

	public Double getDescuentoAnt() {
		return descuentoAnt;
	}

	public void setDescuentoAnt(Double descuentoAnt) {
		this.descuentoAnt = descuentoAnt;
	}

}