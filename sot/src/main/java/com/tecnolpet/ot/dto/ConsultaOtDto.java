package com.tecnolpet.ot.dto;

import java.io.Serializable;
import java.util.Date;

import com.tecnolpet.ot.model.Cliente;
import com.tecnolpet.ot.model.Vendedor;

public class ConsultaOtDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7914528413270681101L;
	private Date fechaDesde;
	private Date fechaHasta;
	private Integer tipo;
	private String equipo;
	private String servicio;
	private String serie;
	private String oet;
	private Integer empresa;
	private Cliente cliente;
	private Vendedor tecnico;
	private String oit;
	private String reporte;
	
	public Integer getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Integer empresa) {
		this.empresa = empresa;
	}
	public Date getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public Date getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	public Integer getTipo() {
		return tipo;
	}
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	public String getEquipo() {
		return equipo;
	}
	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}
	public String getServicio() {
		return servicio;
	}
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getOet() {
		return oet;
	}
	public void setOet(String oet) {
		this.oet = oet;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getOit() {
		return oit;
	}
	public void setOit(String oit) {
		this.oit = oit;
	}
	public String getReporte() {
		return reporte;
	}
	public void setReporte(String reporte) {
		this.reporte = reporte;
	}
	public Vendedor getTecnico() {
		return tecnico;
	}
	public void setTecnico(Vendedor tecnico) {
		this.tecnico = tecnico;
	}
	
}
