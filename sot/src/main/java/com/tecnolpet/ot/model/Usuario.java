package com.tecnolpet.ot.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;

/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
// @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class,
// property = "@id")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_usuario")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonView(ViewOT.PublicView.class)
	protected Integer id;

	@Column(name = "apellido_usuario")
	@JsonView(ViewOT.PublicView.class)
	protected String apellidoUsuario;

	@Column(name = "email_usuario")
	@JsonView(ViewOT.PublicView.class)
	protected String emailUsuario;

	@Column(name = "nombre_usuario")
	@JsonView(ViewOT.PublicView.class)
	protected String nombreUsuario;
	
	@Column(name = "estado_usuario")
	@JsonView(ViewOT.PublicView.class)
	protected Boolean estadoUsuario;
	
	
	
	@Column(name = "id_region")
	@JsonView(ViewOT.PublicView.class)
	protected Integer region;
	@JsonView(ViewOT.PublicView.class)
	protected String username;
	@JsonView(ViewOT.PublicView.class)
	protected String password;
	

	// bi-directional many-to-one association to PerfilProducto
	@ManyToOne
	@JoinColumn(name = "id_perfil_empresa")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)
	@JsonView(ViewOT.PublicView.class)
	private PerfilEmpresa perfilEmpresa;
	
	@ManyToOne
	@JoinColumn(name = "id_sucursal",referencedColumnName="codigo")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)	
	@JsonView(ViewOT.PublicView.class)
	private Sucursal sucursal;
	
	@ManyToOne
	@JoinColumn(name="codigo_ruta")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)		
	@JsonView(ViewOT.PublicView.class)
	private Ruta ruta;
	

	// bi-directional many-to-one association to UsuarioRol
	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
	private List<UsuarioRol> usuarioRols;

	public Usuario() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApellidoUsuario() {
		return this.apellidoUsuario;
	}

	public void setApellidoUsuario(String apellidoUsuario) {
		this.apellidoUsuario = apellidoUsuario;
	}

	public String getEmailUsuario() {
		return this.emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public String getNombreUsuario() {
		return this.nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public Boolean getEstadoUsuario() {
		return this.estadoUsuario;
	}

	public void setEstadoUsuario(Boolean estadoUsuario) {
		this.estadoUsuario = estadoUsuario;
	}

	

	public PerfilEmpresa getPerfilEmpresa() {
		return perfilEmpresa;
	}

	public void setPerfilEmpresa(PerfilEmpresa perfilEmpresa) {
		this.perfilEmpresa = perfilEmpresa;
	}

	@JsonIgnore
	@JsonProperty(value = "usuarioRols")
	public List<UsuarioRol> getUsuarioRols() {
		return this.usuarioRols;
	}

	@JsonProperty(value = "usuarioRols")
	public void setUsuarioRols(List<UsuarioRol> usuarioRols) {
		this.usuarioRols = usuarioRols;
	}

	public UsuarioRol addUsuarioRol(UsuarioRol usuarioRol) {
		getUsuarioRols().add(usuarioRol);
		usuarioRol.setUsuario(this);

		return usuarioRol;
	}

	public UsuarioRol removeUsuarioRol(UsuarioRol usuarioRol) {
		getUsuarioRols().remove(usuarioRol);
		usuarioRol.setUsuario(null);

		return usuarioRol;
	}

	

	public Integer getRegion() {
		return region;
	}

	public void setRegion(Integer region) {
		this.region = region;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public Ruta getRuta() {
		return ruta;
	}

	public void setRuta(Ruta ruta) {
		this.ruta = ruta;
	}

	

}