package com.tecnolpet.ot.dto;

import java.io.Serializable;
import java.sql.Date;

import com.tecnolpet.ot.model.Catalogo;

public class VendedorDto implements Serializable{
	private static final long serialVersionUID = 6045009946950371118L;
	private Integer codigo_sucur;
	private String nombres;
	private String apellidos;
	private String cedula;
	private Date fecha_nacimiento;
	private String telefono;
	private String celular;
	private String email ;
	private String cargo;
	private String codigo_kohinor;
	private Catalogo estado;
	private Integer id;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Catalogo getEstado() {
		return estado;
	}
	public void setEstado(Catalogo estado) {
		this.estado = estado;
	}
	
	public Integer getCodigo_sucur() {
		return codigo_sucur;
	}
	public void setCodigo_sucur(Integer codigo_sucur) {
		this.codigo_sucur = codigo_sucur;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}
	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getCodigo_kohinor() {
		return codigo_kohinor;
	}
	public void setCodigo_kohinor(String codigo_kohinor) {
		this.codigo_kohinor = codigo_kohinor;
	}
	
	
}
	
	
	


