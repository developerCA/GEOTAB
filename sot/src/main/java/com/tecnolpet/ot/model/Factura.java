package com.tecnolpet.ot.model;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the factura database table.
 * 
 */
@Entity
@NamedQuery(name="Factura.findAll", query="SELECT f FROM Factura f")
public class Factura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codigo")
	@JsonView(ViewOT.PublicView.class)
	private Integer id;
	@JsonView(ViewOT.PublicView.class)
	private double descuento;
	@JsonView(ViewOT.PublicView.class)
	private Long estado;

	@Column(name="fecha_hora_aprobacion")
	@JsonView(ViewOT.PublicView.class)
	private Timestamp fechaHoraAprobacion;

	@Column(name="fecha_hora_cancelacion")
	@JsonView(ViewOT.PublicView.class)
	private Timestamp fechaHoraCancelacion;

	@Column(name="fecha_hora_registro")
	@JsonView(ViewOT.PublicView.class)
	private Timestamp fechaHoraRegistro;

	@Column(name="porcentaje_impuesto")
	@JsonView(ViewOT.PublicView.class)
	private double porcentajeImpuesto;
	@JsonView(ViewOT.PublicView.class)
	private double subtotal;
	@JsonView(ViewOT.PublicView.class)
	private double total;

	@Column(name="usuario_aprobacion")
	@JsonView(ViewOT.PublicView.class)
	private Long usuarioAprobacion;

	@Column(name="usuario_cancelacion")
	@JsonView(ViewOT.PublicView.class)
	private Long usuarioCancelacion;

	@Column(name="usuario_registro")
	@JsonView(ViewOT.PublicView.class)
	private Long usuarioRegistro;

	@Column(name="valor_con_impuesto")
	@JsonView(ViewOT.PublicView.class)
	private double valorConImpuesto;

	@Column(name="valor_sin_impuesto")
	@JsonView(ViewOT.PublicView.class)
	private double valorSinImpuesto;

	//bi-directional many-to-one association to DetalleFactura
	@OneToMany(mappedBy="factura")
	@JsonIgnore
	private List<DetalleFactura> detalleFacturas;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="codigo_cliente")
	@JsonView(ViewOT.PublicView.class)
	private Cliente cliente;

	//bi-directional many-to-one association to NotaPedido
	@ManyToOne
	@JoinColumn(name="codigo_nota_pedido")
	@JsonView(ViewOT.PublicView.class)
	private NotaPedido notaPedido;

	//bi-directional many-to-one association to Region
	@ManyToOne
	@JoinColumn(name="codigo_region")
	@JsonView(ViewOT.PublicView.class)
	private Region region;

	//bi-directional many-to-one association to Sucursal
	@ManyToOne
	@JoinColumn(name="codigo_sucursal")
	@JsonView(ViewOT.PublicView.class)
	private Sucursal sucursal;

	//bi-directional many-to-one association to Vendedor
	@ManyToOne
	@JoinColumn(name="codigo_vendedor")
	@JsonView(ViewOT.PublicView.class)
	private Vendedor vendedor;

	public Factura() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getDescuento() {
		return this.descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public Long getEstado() {
		return this.estado;
	}

	public void setEstado(Long estado) {
		this.estado = estado;
	}

	public Timestamp getFechaHoraAprobacion() {
		return this.fechaHoraAprobacion;
	}

	public void setFechaHoraAprobacion(Timestamp fechaHoraAprobacion) {
		this.fechaHoraAprobacion = fechaHoraAprobacion;
	}

	public Timestamp getFechaHoraCancelacion() {
		return this.fechaHoraCancelacion;
	}

	public void setFechaHoraCancelacion(Timestamp fechaHoraCancelacion) {
		this.fechaHoraCancelacion = fechaHoraCancelacion;
	}

	public Timestamp getFechaHoraRegistro() {
		return this.fechaHoraRegistro;
	}

	public void setFechaHoraRegistro(Timestamp fechaHoraRegistro) {
		this.fechaHoraRegistro = fechaHoraRegistro;
	}

	public double getPorcentajeImpuesto() {
		return this.porcentajeImpuesto;
	}

	public void setPorcentajeImpuesto(double porcentajeImpuesto) {
		this.porcentajeImpuesto = porcentajeImpuesto;
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

	public Long getUsuarioAprobacion() {
		return this.usuarioAprobacion;
	}

	public void setUsuarioAprobacion(Long usuarioAprobacion) {
		this.usuarioAprobacion = usuarioAprobacion;
	}

	public Long getUsuarioCancelacion() {
		return this.usuarioCancelacion;
	}

	public void setUsuarioCancelacion(Long usuarioCancelacion) {
		this.usuarioCancelacion = usuarioCancelacion;
	}

	public Long getUsuarioRegistro() {
		return this.usuarioRegistro;
	}

	public void setUsuarioRegistro(Long usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}

	public double getValorConImpuesto() {
		return this.valorConImpuesto;
	}

	public void setValorConImpuesto(double valorConImpuesto) {
		this.valorConImpuesto = valorConImpuesto;
	}

	public double getValorSinImpuesto() {
		return this.valorSinImpuesto;
	}

	public void setValorSinImpuesto(double valorSinImpuesto) {
		this.valorSinImpuesto = valorSinImpuesto;
	}

	public List<DetalleFactura> getDetalleFacturas() {
		return this.detalleFacturas;
	}

	public void setDetalleFacturas(List<DetalleFactura> detalleFacturas) {
		this.detalleFacturas = detalleFacturas;
	}

	public DetalleFactura addDetalleFactura(DetalleFactura detalleFactura) {
		getDetalleFacturas().add(detalleFactura);
		detalleFactura.setFactura(this);

		return detalleFactura;
	}

	public DetalleFactura removeDetalleFactura(DetalleFactura detalleFactura) {
		getDetalleFacturas().remove(detalleFactura);
		detalleFactura.setFactura(null);

		return detalleFactura;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public NotaPedido getNotaPedido() {
		return this.notaPedido;
	}

	public void setNotaPedido(NotaPedido notaPedido) {
		this.notaPedido = notaPedido;
	}

	public Region getRegion() {
		return this.region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public Sucursal getSucursal() {
		return this.sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public Vendedor getVendedor() {
		return this.vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

}