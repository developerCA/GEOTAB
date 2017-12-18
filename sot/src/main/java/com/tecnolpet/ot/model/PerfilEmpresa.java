package com.tecnolpet.ot.model;

import java.io.Serializable;
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
import javax.persistence.Transient;

import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;

/**
 * The persistent class for the perfil_producto database table.
 * 
 */
@Entity
@Table(name = "perfil_empresa")
public class PerfilEmpresa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_perfil_empresa")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(ViewOT.PublicView.class)
	private Integer id;

	// bi-directional many-to-one association to Perfil
	@ManyToOne
	@JoinColumn(name = "id_perfil")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)
	@JsonView(ViewOT.PublicView.class)
	private Perfil perfil;

	// bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name = "id_empresa")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)
	@JsonView(ViewOT.PublicView.class)
	private Empresa empresa;
	
	@Column(name="estado")
	@JsonView(ViewOT.PublicView.class)
	private Boolean estado;

	// bi-directional many-to-one association to PermisoPerfilProducto
	@OneToMany(mappedBy = "perfilEmpresa")
	
	private List<PermisoPerfilEmpresa> permisoPerfilEmpresa;

	 // bi-directional many-to-one association to Usuario
    @OneToMany(mappedBy = "perfilEmpresa")
    @JsonIgnore
    private List<Usuario> usuarios;

	@Transient
	@JsonView(ViewOT.PublicView.class)
	private String nombrePerfil;
	@Transient
	@JsonView(ViewOT.PublicView.class)
	private Integer idPermisoPerfil;
	@Transient
	@JsonView(ViewOT.PublicView.class)
	private Integer idPermiso;

	public PerfilEmpresa() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Perfil getPerfil() {
		return this.perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}



	public String getNombrePerfil() {
		if (perfil != null) {
			nombrePerfil = perfil.getNombrePerfil();
		}
		return nombrePerfil;
	}

	public void setNombrePerfil(String nombrePerfil) {
		this.nombrePerfil = nombrePerfil;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}