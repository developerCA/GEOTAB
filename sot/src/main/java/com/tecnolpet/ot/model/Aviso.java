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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;


/**
 * The persistent class for the aviso database table.
 * 
 */
@Entity
@NamedQuery(name="Aviso.findAll", query="SELECT a FROM Aviso a")
public class Aviso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codigo")
	@JsonView(ViewOT.PublicView.class)
	private Integer id;

	@ManyToOne
	@JoinColumn(name="codigo_fechas_renovacion", referencedColumnName="codigo")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)
	@JsonView(ViewOT.PublicView.class)
	private FechasRenovacion codigoFechasRenovacion;
	
	@ManyToOne
	@JoinColumn(name="codigo_sucursal", referencedColumnName="codigo")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)	
	@JsonView(ViewOT.PublicView.class)
	private Sucursal sucursal;

	@Column(name="codigo_usuario_entrega")
	@JsonView(ViewOT.PublicView.class)
	private Integer codigoUsuarioEntrega;

	@Column(name="codigo_usuario_recibe")
	@JsonView(ViewOT.PublicView.class)
	private Integer codigoUsuarioRecibe;

	@Column(name="fecha_entrega_usuario")
	@JsonView(ViewOT.PublicView.class)
	private Timestamp fechaEntregaUsuario;

	@Column(name="fecha_recepcion_usuario")
	@JsonView(ViewOT.PublicView.class)
	private Timestamp fechaRecepcionUsuario;

	@Column(name="fecha_registro")
	@JsonView(ViewOT.PublicView.class)
	private Timestamp fechaRegistro;

	@Column(name="usuario_registro")
	@JsonView(ViewOT.PublicView.class)
	private Integer usuarioRegistro;
	
	@Column(name="codigo_usuario_cancelacion")
	@JsonView(ViewOT.PublicView.class)
	private Integer usuarioCancelacion;
	
	@JsonView(ViewOT.PublicView.class)
	private boolean procesado;
	@JsonView(ViewOT.PublicView.class)
	private String motivo;
		
	@OneToMany(mappedBy="aviso")
	@JsonIgnore
	private List<AvisoDetalle> avisosDetalle;

	
	
	@ManyToOne
	@JoinColumn(name="estado")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)		
	@JsonView(ViewOT.PublicView.class)
	private Catalogo estado;
	
	@ManyToOne
	@JoinColumn(name="cliente")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)		
	@JsonView(ViewOT.PublicView.class)
	private Cliente cliente;
	
	@Column(name="fecha_cancelacion")
	@JsonView(ViewOT.PublicView.class)
	private Timestamp fechaCancelacion;
	
	@ManyToOne
	@JoinColumn(name="codigo_subcategoria")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)		
	@JsonView(ViewOT.PublicView.class)
	private SubCategoria subcategoria;
	
	@ManyToOne
	@JoinColumn(name="codigo_enlace")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)		
	@JsonView(ViewOT.PublicView.class)
	private Enlace enlace;
	
	@Column(name="nombre_recibe")
	@JsonView(ViewOT.PublicView.class)
	private String nombreRecibe;
	
	@Column(name="fecha_entrega")
	@JsonView(ViewOT.PublicView.class)
	private Date fechaEntrega;


	public Aviso() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public FechasRenovacion getCodigoFechasRenovacion() {
		return this.codigoFechasRenovacion;
	}

	public void setCodigoFechasRenovacion(FechasRenovacion codigoFechasRenovacion) {
		this.codigoFechasRenovacion = codigoFechasRenovacion;
	}

	public Integer getCodigoUsuarioEntrega() {
		return this.codigoUsuarioEntrega;
	}

	public void setCodigoUsuarioEntrega(Integer codigoUsuarioEntrega) {
		this.codigoUsuarioEntrega = codigoUsuarioEntrega;
	}

	public Integer getCodigoUsuarioRecibe() {
		return this.codigoUsuarioRecibe;
	}

	public void setCodigoUsuarioRecibe(Integer codigoUsuarioRecibe) {
		this.codigoUsuarioRecibe = codigoUsuarioRecibe;
	}

	public Timestamp getFechaEntregaUsuario() {
		return this.fechaEntregaUsuario;
	}

	public void setFechaEntregaUsuario(Timestamp fechaEntregaUsuario) {
		this.fechaEntregaUsuario = fechaEntregaUsuario;
	}

	public Timestamp getFechaRecepcionUsuario() {
		return this.fechaRecepcionUsuario;
	}

	public void setFechaRecepcionUsuario(Timestamp fechaRecepcionUsuario) {
		this.fechaRecepcionUsuario = fechaRecepcionUsuario;
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
	
	public Integer getUsuarioCancelacion() {
		return usuarioCancelacion;
	}

	public void setUsuarioCancelacion(Integer usuarioCancelacion) {
		this.usuarioCancelacion = usuarioCancelacion;
	}

	public List<AvisoDetalle> getAvisosDetalle() {
		return avisosDetalle;
	}

	public void setAvisosDetalle(List<AvisoDetalle> avisosDetalle) {
		this.avisosDetalle = avisosDetalle;
	}

	public Catalogo getEstado() {
		return estado;
	}

	public void setEstado(Catalogo estado) {
		this.estado = estado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	

	public boolean isProcesado() {
		return procesado;
	}

	public void setProcesado(boolean procesado) {
		this.procesado = procesado;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public Timestamp getFechaCancelacion() {
		return fechaCancelacion;
	}

	public void setFechaCancelacion(Timestamp fechaCancelacion) {
		this.fechaCancelacion = fechaCancelacion;
	}

	public SubCategoria getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(SubCategoria subcategoria) {
		this.subcategoria = subcategoria;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public Enlace getEnlace() {
		return enlace;
	}

	public void setEnlace(Enlace enlace) {
		this.enlace = enlace;
	}

	public String getNombreRecibe() {
		return nombreRecibe;
	}

	public void setNombreRecibe(String nombreRecibe) {
		this.nombreRecibe = nombreRecibe;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}	
	
}