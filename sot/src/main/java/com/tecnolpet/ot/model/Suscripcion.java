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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;

/**
 * The persistent class for the suscripcion database table.
 * 
 */
@Entity
public class Suscripcion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo")
	@JsonView(ViewOT.PublicView.class)
	private Integer id;

	@Column(name = "codigo_barras")
	@JsonView(ViewOT.PublicView.class)
	private String codigoBarras;
	@JsonView(ViewOT.PublicView.class)
	private Boolean demo;

	@Column(name = "fecha_hora_aprobacion")
	@JsonView(ViewOT.PublicView.class)
	private Timestamp fechaHoraAprobacion;

	@Column(name = "fecha_hora_cancelacion")
	@JsonView(ViewOT.PublicView.class)
	private Timestamp fechaHoraCancelacion;

	@Column(name = "fecha_hora_registro")
	@JsonView(ViewOT.PublicView.class)
	private Timestamp fechaHoraRegistro;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_inicio")
	@JsonView(ViewOT.PublicView.class)
	private Date fechaInicio;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_vencimiento")
	@JsonView(ViewOT.PublicView.class)
	private Date fechaVencimiento;

	@Column(name = "usuario_aprobacion")
	@JsonView(ViewOT.PublicView.class)
	private Integer usuarioAprobacion;

	@Column(name = "usuario_cancelacion")
	@JsonView(ViewOT.PublicView.class)
	private Integer usuarioCancelacion;

	@Column(name = "usuario_registro")
	@JsonView(ViewOT.PublicView.class)
	private Integer usuarioRegistro;

	@Column(name = "numero_actualizacion_producto")
	@JsonView(ViewOT.PublicView.class)
	private Integer numeroActualizacion;
	@JsonView(ViewOT.PublicView.class)
	private double costo;
    @Transient 
    @JsonView(ViewOT.PublicView.class)
	private Integer RenovacionDetalle;

	// bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name = "codigo_cliente", referencedColumnName = "codigo")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@RestResource(exported = false)
	@JsonProperty(value = "clienteP")
	@JsonView(ViewOT.PublicView.class)
	private Cliente cliente;

	// bi-directional many-to-one association to DetalleNotaPedido
	@ManyToOne
	@JoinColumn(name = "codigo_detalle_nota_pedido")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@RestResource(exported = false)
	@JsonProperty(value = "detalleNotaPedidoP")
	@JsonView(ViewOT.PublicView.class)
	private DetalleNotaPedido detalleNotaPedido;

	// bi-directional many-to-one association to Enlace
	@ManyToOne
	@JoinColumn(name = "codigo_enlace")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@RestResource(exported = false)
	@JsonProperty(value = "enlaceP")
	@JsonView(ViewOT.PublicView.class)
	private Enlace enlace;

	// bi-directional many-to-one association to NotaPedido
	@ManyToOne
	@JoinColumn(name = "codigo_nota_pedido")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@RestResource(exported = false)
	@JsonProperty(value = "notaPedidoP")
	@JsonView(ViewOT.PublicView.class)
	private NotaPedido notaPedido;

	// bi-directional many-to-one association to TipoOperacion
	@ManyToOne
	@JoinColumn(name = "codigo_tipo_operacion")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@RestResource(exported = false)
	@JsonProperty(value = "tipoOperacionP")
	@JsonView(ViewOT.PublicView.class)
	private TipoOperacion tipoOperacion;
	
	@ManyToOne
	@JoinColumn(name = "codigo_producto")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@RestResource(exported = false)
	@JsonView(ViewOT.PublicView.class)
	private Producto producto;

	@ManyToOne
	@JoinColumn(name = "codigo_sucursal")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@RestResource(exported = false)
	@JsonView(ViewOT.PublicView.class)
	private Sucursal sucursal;

	// bi-directional many-to-one association to Catalogo
	@ManyToOne
	@JoinColumn(name = "estado")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@RestResource(exported = false)
	@JsonProperty(value = "catalogoP")
	@JsonView(ViewOT.PublicView.class)
	private Catalogo catalogo;
	
	//bi-directional many-to-one association to ProductoActualizacionDetalle
	@OneToMany(mappedBy="productoActualizacion")
	private List<ProductoActualizacionDetalle> productoActualizacionDetalles;
	@JsonView(ViewOT.PublicView.class)
	private Integer suscripcionInicial;
	
	@Column(name="fecha_hora_activacion")
	@JsonView(ViewOT.PublicView.class)
	private Timestamp fechaHoraActivacion;
	
	@Column(name="fecha_hora_desactivacion")
	@JsonView(ViewOT.PublicView.class)
	private Timestamp fechaHoraDesactivacion;
	
	@Column(name="usuario_activacion")
	@JsonView(ViewOT.PublicView.class)
	private Integer usuarioActivacion;

	public Suscripcion() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigoBarras() {
		return this.codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public Boolean getDemo() {
		return this.demo;
	}

	public void setDemo(Boolean demo) {
		this.demo = demo;
	}

	public Timestamp getFechaHoraAprobacion() {
		return this.fechaHoraAprobacion;
	}

	public void setFechaHoraAprobacion(Timestamp fechaHoraAprobacion) {
		this.fechaHoraAprobacion = fechaHoraAprobacion;
	}

	public Catalogo getCatalogo() {
		return catalogo;
	}

	public void setCatalogo(Catalogo catalogo) {
		this.catalogo = catalogo;
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

	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaVencimiento() {
		return this.fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public Integer getUsuarioAprobacion() {
		return this.usuarioAprobacion;
	}

	public void setUsuarioAprobacion(Integer usuarioAprobacion) {
		this.usuarioAprobacion = usuarioAprobacion;
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

	public Integer getRenovacionDetalle() {
		return RenovacionDetalle;
	}

	public void setRenovacionDetalle(Integer renovacionDetalle) {
		RenovacionDetalle = renovacionDetalle;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public DetalleNotaPedido getDetalleNotaPedido() {
		return this.detalleNotaPedido;
	}

	public void setDetalleNotaPedido(DetalleNotaPedido detalleNotaPedido) {
		this.detalleNotaPedido = detalleNotaPedido;
	}

	public Enlace getEnlace() {
		return this.enlace;
	}

	public void setEnlace(Enlace enlace) {
		this.enlace = enlace;
	}

	public NotaPedido getNotaPedido() {
		return this.notaPedido;
	}

	public void setNotaPedido(NotaPedido notaPedido) {
		this.notaPedido = notaPedido;
	}

	public TipoOperacion getTipoOperacion() {
		return this.tipoOperacion;
	}

	public void setTipoOperacion(TipoOperacion tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public Integer getSuscripcionInicial() {
		return suscripcionInicial;
	}

	public void setSuscripcionInicial(Integer suscripcionInicial) {
		this.suscripcionInicial = suscripcionInicial;
	}

	public Timestamp getFechaHoraActivacion() {
		return fechaHoraActivacion;
	}

	public void setFechaHoraActivacion(Timestamp fechaHoraActivacion) {
		this.fechaHoraActivacion = fechaHoraActivacion;
	}

	public Timestamp getFechaHoraDesactivacion() {
		return fechaHoraDesactivacion;
	}

	public void setFechaHoraDesactivacion(Timestamp fechaHoraDesactivacion) {
		this.fechaHoraDesactivacion = fechaHoraDesactivacion;
	}

	public Integer getNumeroActualizacion() {
		return numeroActualizacion;
	}

	public void setNumeroActualizacion(Integer numeroActualizacion) {
		this.numeroActualizacion = numeroActualizacion;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public Integer getUsuarioActivacion() {
		return usuarioActivacion;
	}

	public void setUsuarioActivacion(Integer usuarioActivacion) {
		this.usuarioActivacion = usuarioActivacion;
	}

}
