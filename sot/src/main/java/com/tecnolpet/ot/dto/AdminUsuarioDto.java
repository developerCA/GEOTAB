package com.tecnolpet.ot.dto;

import java.io.Serializable;

import com.tecnolpet.ot.model.Empresa;
import com.tecnolpet.ot.model.Perfil;
import com.tecnolpet.ot.model.Usuario;

public class AdminUsuarioDto implements Serializable {

	private static final long serialVersionUID = 5082783408709603703L;

	private Usuario usuario;
	private String password;
	private Perfil perfil;
	private Empresa empresa;

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

}
