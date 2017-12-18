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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;

/**
 * The persistent class for the permiso database table.
 * 
 */
@Entity
@NamedQuery(name = "Permiso.findAll", query = "SELECT p FROM Permiso p")
public class Permiso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_permiso")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(ViewOT.PublicView.class)
	private Integer id;

	@Column(name = "icono_permiso")
	@JsonView(ViewOT.PublicView.class)
	private String iconoPermiso;

	@Column(name = "nombre_permiso")
	@JsonView(ViewOT.PublicView.class)
	private String nombrePermiso;

	@Column(name = "orden_permiso")
	@JsonView(ViewOT.PublicView.class)
	private String ordenPermiso;

	@Column(name = "url_permiso")
	@JsonView(ViewOT.PublicView.class)
	private String urlPermiso;

	// bi-directional many-to-one association to Permiso
	@ManyToOne
	@JoinColumn(name = "per_id_permiso")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@RestResource(exported = false)
	@JsonView(ViewOT.PublicView.class)
	private Permiso permiso;

	// bi-directional many-to-one association to Permiso
	@OneToMany(mappedBy = "permiso")
	@JsonIgnore
	private List<Permiso> permisos;

	// bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name = "id_empresa")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@JsonView(ViewOT.PublicView.class)
	@RestResource(exported = false)
	private Empresa empresa;

	// bi-directional many-to-one association to PermisoPerfilProducto
	@OneToMany(mappedBy = "permiso")
	@JsonIgnore
	private List<PermisoPerfilEmpresa> permisoPerfilProductos;

	@Column(name = "estado")
	@JsonView(ViewOT.PublicView.class)
	private Boolean estado;

	@Transient
	@JsonView(ViewOT.PublicView.class)
	private Integer idEmpresa;

	@Transient
	@JsonView(ViewOT.PublicView.class)
	private Integer idPermisoPadre;

	public Permiso() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIconoPermiso() {
		return this.iconoPermiso;
	}

	public void setIconoPermiso(String iconoPermiso) {
		this.iconoPermiso = iconoPermiso;
	}

	public String getNombrePermiso() {
		return this.nombrePermiso;
	}

	public void setNombrePermiso(String nombrePermiso) {
		this.nombrePermiso = nombrePermiso;
	}

	public String getOrdenPermiso() {
		return this.ordenPermiso;
	}

	public void setOrdenPermiso(String ordenPermiso) {
		this.ordenPermiso = ordenPermiso;
	}

	public String getUrlPermiso() {
		return this.urlPermiso;
	}

	public void setUrlPermiso(String urlPermiso) {
		this.urlPermiso = urlPermiso;
	}

	public Permiso getPermiso() {
		return this.permiso;
	}

	public void setPermiso(Permiso permiso) {
		this.permiso = permiso;
	}

	public List<Permiso> getPermisos() {
		return this.permisos;
	}

	public void setPermisos(List<Permiso> permisos) {
		this.permisos = permisos;
	}

	public Permiso addPermiso(Permiso permiso) {
		getPermisos().add(permiso);
		permiso.setPermiso(this);

		return permiso;
	}

	public Permiso removePermiso(Permiso permiso) {
		getPermisos().remove(permiso);
		permiso.setPermiso(null);

		return permiso;
	}

	public List<PermisoPerfilEmpresa> getPermisoPerfilProductos() {
		return this.permisoPerfilProductos;
	}

	public void setPermisoPerfilProductos(
			List<PermisoPerfilEmpresa> permisoPerfilProductos) {
		this.permisoPerfilProductos = permisoPerfilProductos;
	}

	public PermisoPerfilEmpresa addPermisoPerfilProducto(
			PermisoPerfilEmpresa permisoPerfilProducto) {
		getPermisoPerfilProductos().add(permisoPerfilProducto);
		permisoPerfilProducto.setPermiso(this);

		return permisoPerfilProducto;
	}

	public PermisoPerfilEmpresa removePermisoPerfilProducto(
			PermisoPerfilEmpresa permisoPerfilProducto) {
		getPermisoPerfilProductos().remove(permisoPerfilProducto);
		permisoPerfilProducto.setPermiso(null);

		return permisoPerfilProducto;
	}

	public Integer getIdPermisoPadre() {
		if (idPermisoPadre == null && permiso != null
				&& permiso.getId() != null) {
			idPermisoPadre = permiso.getId();
		}
		return idPermisoPadre;
	}

	public void setIdPermisoPadre(Integer idPermisoPadre) {
		this.idPermisoPadre = idPermisoPadre;
	}

	public Boolean isEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

}