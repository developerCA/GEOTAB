package com.tecnolpet.ot.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;



import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.dto.SeguimientoDto;
import com.tecnolpet.ot.jview.ViewOT;

/**
 * The persistent class for the tarea_detalle_nota_pedido database table.
 * 
 */
@Entity
@Table(name = "tarea_detalle_nota_pedido")
@NamedQuery(name = "TareaDetalleNotaPedido.findAll", query = "SELECT t FROM TareaDetalleNotaPedido t")
public class TareaDetalleNotaPedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo")
	@JsonView(ViewOT.PublicView.class)
	private Integer id;

	@Column(name = "archivo_tarea")
	@JsonView(ViewOT.PublicView.class)
	private String archivoTarea;

	@Column(name = "archivo_real")
	@JsonView(ViewOT.PublicView.class)
	private String archivoReal;
	@JsonView(ViewOT.PublicView.class)
	private Double costo;
	@JsonView(ViewOT.PublicView.class)
	private Double total;

	@JsonView(ViewOT.PublicView.class)
	private String alcance;
	
	@Column(name = "codigo_reporte")
	@JsonView(ViewOT.PublicView.class)
	private Integer codigoReporte;

	// bi-directional many-to-one association to Tarea
	@ManyToOne
	@JoinColumn(name = "codigo_tarea")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@RestResource(exported = false)
	@JsonView(ViewOT.PublicView.class)
	private Tarea tarea;

	// bi-directional many-to-one association to DetalleNotaPedido
	@ManyToOne
	@JoinColumn(name = "codigo_detalle_nota_pedido")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@RestResource(exported = false)
	@JsonView(ViewOT.PublicView.class)
	private DetalleNotaPedido detalleNotaPedido;

	// bi-directional many-to-one association to Vendedor
	@ManyToOne
	@JoinColumn(name = "codigo_asistente")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@RestResource(exported = false)
	@JsonView(ViewOT.PublicView.class)
	private Vendedor asistente;

	// bi-directional many-to-one association to Vendedor
	@ManyToOne
	@JoinColumn(name = "codigo_responsable")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@RestResource(exported = false)
	@JsonView(ViewOT.PublicView.class)
	private Vendedor responsable;

	@Transient
	@JsonView(ViewOT.PublicView.class)
	private SeguimientoDto seguimiento;

	public TareaDetalleNotaPedido() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getArchivoTarea() {
		return this.archivoTarea;
	}

	public void setArchivoTarea(String archivoTarea) {
		this.archivoTarea = archivoTarea;
	}

	public Tarea getTarea() {
		return this.tarea;
	}

	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}

	public DetalleNotaPedido getDetalleNotaPedido() {
		return this.detalleNotaPedido;
	}

	public void setDetalleNotaPedido(DetalleNotaPedido detalleNotaPedido) {
		this.detalleNotaPedido = detalleNotaPedido;
	}

	public String getArchivoReal() {
		return archivoReal;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public void setArchivoReal(String archivoReal) {
		this.archivoReal = archivoReal;
	}

	public SeguimientoDto getSeguimiento() {
		return seguimiento;
	}

	public void setSeguimiento(SeguimientoDto seguimiento) {
		this.seguimiento = seguimiento;
	}

	public Integer getCodigoReporte() {
		return codigoReporte;
	}

	public void setCodigoReporte(Integer codigoReporte) {
		this.codigoReporte = codigoReporte;
	}

	public Vendedor getAsistente() {
		return asistente;
	}

	public void setAsistente(Vendedor asistente) {
		this.asistente = asistente;
	}

	public Vendedor getResponsable() {
		return responsable;
	}

	public void setResponsable(Vendedor responsable) {
		this.responsable = responsable;
	}

	public String getAlcance() {
		return alcance;
	}

	public void setAlcance(String alcance) {
		this.alcance = alcance;
	}

}