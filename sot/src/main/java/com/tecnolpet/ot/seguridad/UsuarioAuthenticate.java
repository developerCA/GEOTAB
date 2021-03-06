package com.tecnolpet.ot.seguridad;

import java.io.Serializable;
import java.util.List;

import com.tecnolpet.ot.dto.MenuDto;
import com.tecnolpet.ot.model.Ruta;

public class UsuarioAuthenticate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1609061555533618886L;

	protected String username;
	protected String nombresCompletos;
	protected String email;
	protected String apellido;
	protected Integer id;
	protected String password;
	protected Integer perfil;
	protected Integer sucursal;
	protected Ruta ruta;

	protected List<MenuDto> permisos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNombresCompletos() {
		return nombresCompletos;
	}

	public void setNombresCompletos(String nombresCompletos) {
		this.nombresCompletos = nombresCompletos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getSucursal() {
		return sucursal;
	}

	public void setSucursal(Integer sucursal) {
		this.sucursal = sucursal;
	}

	public List<MenuDto> getPermisos() {
		return permisos;
	}

	public void setPermisos(List<MenuDto> permisos) {
		this.permisos = permisos;
	}

	public Ruta getRuta() {
		return ruta;
	}

	public void setRuta(Ruta ruta) {
		this.ruta = ruta;
	}

	public Integer getPerfil() {
		return perfil;
	}

	public void setPerfil(Integer perfil) {
		this.perfil = perfil;
	}

}
