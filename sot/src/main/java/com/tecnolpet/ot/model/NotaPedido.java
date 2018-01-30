package com.tecnolpet.ot.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;

/**
 * The persistent class for the nota_pedido database table.
 * 
 */
@Entity
@Table(name = "nota_pedido")
public class NotaPedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo")
	@JsonView(ViewOT.PublicView.class)
	private Integer id;
	@JsonView(ViewOT.PublicView.class)
	private double descuento;

	@Column(name = "fecha_hora_aprobacion")
	@JsonView(ViewOT.PublicView.class)
	private Timestamp fechaHoraAprobacion;

	@Column(name = "fecha_hora_cancelacion")
	@JsonView(ViewOT.PublicView.class)
	private Timestamp fechaHoraCancelacion;

	@Column(name = "fecha_hora_registro")
	@JsonView(ViewOT.PublicView.class)
	private Timestamp fechaHoraRegistro;

	@Column(name = "numero_referencia")
	@JsonView(ViewOT.PublicView.class)
	private String numeroReferencia;

	@Column(name = "porcentaje_impuesto")
	@JsonView(ViewOT.PublicView.class)
	private double porcentajeImpuesto;
	@JsonView(ViewOT.PublicView.class)
	private double subtotal;
	@JsonView(ViewOT.PublicView.class)
	private double total;
	@JsonView(ViewOT.PublicView.class)
	private String observacion;

	@ManyToOne
	@JoinColumn(name = "usuario_aprobacion")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@RestResource(exported = false)
	@JsonView(ViewOT.PublicView.class)
	private Usuario usuarioAprobador;

	@Column(name = "usuario_cancelacion")
	@JsonView(ViewOT.PublicView.class)
	private Integer usuarioCancelacion;

	@Column(name = "usuario_registro")
	@JsonView(ViewOT.PublicView.class)
	private Integer usuarioRegistro;

	@Column(name = "orden_interna")
	@JsonView(ViewOT.PublicView.class)
	private Integer ordenInterna;

	@Column(name = "valor_impuesto")
	@JsonView(ViewOT.PublicView.class)
	private double valorImpuesto;

	@Column(name = "valor_impuesto_0")
	@JsonView(ViewOT.PublicView.class)
	private double valorImpuesto0;

	@OneToMany(mappedBy = "notaPedido")
	@JsonIgnore
	private List<DetalleNotaPedido> detalleNotaPedidos;

	@ManyToOne
	@JoinColumn(name = "id_catalogo")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@RestResource(exported = false)
	@JsonView(ViewOT.PublicView.class)
	private Catalogo idCatalogo;

	@ManyToOne
	@JoinColumn(name = "codigo_telerenovador")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@RestResource(exported = false)
	@JsonView(ViewOT.PublicView.class)
	private Telerenovador telerenovador;

	// bi-directional many-to-one association to Catalogo
	@ManyToOne
	@JoinColumn(name = "estado")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@RestResource(exported = false)
	@JsonView(ViewOT.PublicView.class)
	private Catalogo estado;

	@ManyToOne
	@JoinColumn(name = "codigo_cliente")
	@JsonView(ViewOT.PublicView.class)
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "codigo_cliente_final")
	@JsonView(ViewOT.PublicView.class)
	private Cliente clienteFinal;

	@ManyToOne
	@JoinColumn(name = "codigo_vendedor")
	@JsonView(ViewOT.PublicView.class)
	private Vendedor vendedor;
	@JsonView(ViewOT.PublicView.class)
	private boolean prefactura;
	@JsonView(ViewOT.PublicView.class)
	private boolean pagado;

	@ManyToOne
	@JoinColumn(name = "codigo_empresa")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@RestResource(exported = false)
	@JsonView(ViewOT.PublicView.class)
	private Empresa empresa;

	@ManyToOne
	@JoinColumn(name = "codigo_sucursal")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@RestResource(exported = false)
	@JsonView(ViewOT.PublicView.class)
	private Sucursal sucursal;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_gestion")
	@JsonView(ViewOT.PublicView.class)
	private Date fechaGestion;

	@Column(name = "fecha_apertura")
	@JsonView(ViewOT.PublicView.class)
	private Timestamp fechaApertura;

	@Column(name = "descuento_0")
	@JsonView(ViewOT.PublicView.class)
	private double descuento0;
	@Column(name = "descuento_12")
	@JsonView(ViewOT.PublicView.class)
	private double descuento12;

	@Column(name = "archivo")
	@JsonView(ViewOT.PublicView.class)
	private String archivo;

	@Column(name = "archivo_real")
	@JsonView(ViewOT.PublicView.class)
	private String archivoReal;
	@Transient
	@JsonView(ViewOT.PublicView.class)
	private Integer cantidadEquipos;

	public NotaPedido() {
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

	public Timestamp getFechaHoraAprobacion() {
		return this.fechaHoraAprobacion;
	}

	public void setFechaHoraAprobacion(Timestamp fechaHoraAprobacion) {
		this.fechaHoraAprobacion = fechaHoraAprobacion;
	}

	public Timestamp getFechaHoraCancelacion() {
		return this.fechaHoraCancelacion;
	}

	/**
	 * @return the ordenInterna
	 */
	public Integer getOrdenInterna() {
		return ordenInterna;
	}

	/**
	 * @param ordenInterna
	 *            the ordenInterna to set
	 */
	public void setOrdenInterna(Integer ordenInterna) {
		this.ordenInterna = ordenInterna;
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

	public String getNumeroReferencia() {
		return this.numeroReferencia;
	}

	public void setNumeroReferencia(String numeroReferencia) {
		this.numeroReferencia = numeroReferencia;
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

	public Cliente getClienteFinal() {
		return clienteFinal;
	}

	public void setClienteFinal(Cliente clienteFinal) {
		this.clienteFinal = clienteFinal;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Integer getUsuarioCancelacion() {
		return this.usuarioCancelacion;
	}

	public void setUsuarioCancelacion(Integer usuarioCancelacion) {
		this.usuarioCancelacion = usuarioCancelacion;
	}

	public Integer getUsuarioRegistro() {
		return this.usuarioRegistro;
	}

	public void setUsuarioRegistro(Integer usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}

	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	public Integer getCantidadEquipos() {
		return cantidadEquipos;
	}

	public void setCantidadEquipos(Integer cantidadEquipos) {
		this.cantidadEquipos = cantidadEquipos;
	}

	public String getArchivoReal() {
		return archivoReal;
	}

	public void setArchivoReal(String archivoReal) {
		this.archivoReal = archivoReal;
	}

	public Timestamp getFechaApertura() {
		return fechaApertura;
	}

	public void setFechaApertura(Timestamp fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	public double getValorImpuesto() {
		return this.valorImpuesto;
	}

	public void setValorImpuesto(double valorImpuesto) {
		this.valorImpuesto = valorImpuesto;
	}

	public Date getFechaGestion() {
		return fechaGestion;
	}

	public void setFechaGestion(Date fechaGestion) {
		this.fechaGestion = fechaGestion;
	}

	public double getValorImpuesto0() {
		return this.valorImpuesto0;
	}

	public void setValorImpuesto0(double valorImpuesto0) {
		this.valorImpuesto0 = valorImpuesto0;
	}

	public List<DetalleNotaPedido> getDetalleNotaPedidos() {
		return this.detalleNotaPedidos;
	}

	public void setDetalleNotaPedidos(List<DetalleNotaPedido> detalleNotaPedidos) {
		this.detalleNotaPedidos = detalleNotaPedidos;
	}

	/*
	 * public DetalleNotaPedido addDetalleNotaPedido(DetalleNotaPedido
	 * detalleNotaPedido) { getDetalleNotaPedidos().add(detalleNotaPedido);
	 * detalleNotaPedido.setNotaPedido(this);
	 * 
	 * return detalleNotaPedido; }
	 * 
	 * public DetalleNotaPedido removeDetalleNotaPedido(DetalleNotaPedido
	 * detalleNotaPedido) { getDetalleNotaPedidos().remove(detalleNotaPedido);
	 * detalleNotaPedido.setNotaPedido(null);
	 * 
	 * return detalleNotaPedido; }
	 */

	public Catalogo getIdCatalogo() {
		return this.idCatalogo;
	}

	public void setIdCatalogo(Catalogo idCatalogo) {
		this.idCatalogo = idCatalogo;
	}

	public Catalogo getEstado() {
		return this.estado;
	}

	public void setEstado(Catalogo estado) {
		this.estado = estado;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Usuario getUsuarioAprobador() {
		return usuarioAprobador;
	}

	public void setUsuarioAprobador(Usuario usuarioAprobador) {
		this.usuarioAprobador = usuarioAprobador;
	}

	public Vendedor getVendedor() {
		return this.vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public boolean isPrefactura() {
		return prefactura;
	}

	public void setPrefactura(boolean prefactura) {
		this.prefactura = prefactura;
	}

	public boolean isPagado() {
		return pagado;
	}

	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public double getDescuento0() {
		return descuento0;
	}

	public void setDescuento0(double descuento0) {
		this.descuento0 = descuento0;
	}

	public double getDescuento12() {
		return descuento12;
	}

	public void setDescuento12(double descuento12) {
		this.descuento12 = descuento12;
	}

	public Telerenovador getTelerenovador() {
		return telerenovador;
	}

	public void setTelerenovador(Telerenovador telerenovador) {
		this.telerenovador = telerenovador;
	}

}