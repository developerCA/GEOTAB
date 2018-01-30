package com.tecnolpet.ot.model;

import java.io.Serializable;
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;

/**
 * The persistent class for the cliente database table.
 * 
 */
@Entity
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo")
	@JsonView(ViewOT.PublicView.class)
	private Integer id;

	@Column(name = "actividad_economica")
	@JsonView(ViewOT.PublicView.class)
	private String actividadEconomica;
	@JsonView(ViewOT.PublicView.class)
	private String apellidos;
	@Column(name = "celular_1")
	@JsonView(ViewOT.PublicView.class)
	private String celular1;

	@Column(name = "celular_2")
	@JsonView(ViewOT.PublicView.class)
	private String celular2;
	@JsonView(ViewOT.PublicView.class)
	private String direccion;

	@Column(name = "direccion_trabajo")
	@JsonView(ViewOT.PublicView.class)
	private String direccionTrabajo;
	@JsonView(ViewOT.PublicView.class)
	private String email;

	@Column(name = "extension_telefono_trabajo")
	@JsonView(ViewOT.PublicView.class)
	private String extensionTelefonoTrabajo;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_nacimiento")
	@JsonView(ViewOT.PublicView.class)
	private Date fechaNacimiento;

	@Column(name = "horario_atencion")
	@JsonView(ViewOT.PublicView.class)
	private String horarioAtencion;
	@JsonView(ViewOT.PublicView.class)
	private String identificacion;

	@Column(name = "lugar_trabajo")
	@JsonView(ViewOT.PublicView.class)
	private String lugarTrabajo;
	@JsonView(ViewOT.PublicView.class)
	private String nombres;
	@JsonView(ViewOT.PublicView.class)
	private String observaciones;
	@Column(name = "telefono_1")
	@JsonView(ViewOT.PublicView.class)
	private String telefono1;
	@Column(name = "telefono_2")
	@JsonView(ViewOT.PublicView.class)
	private String telefono2;
	@Column(name = "telefono_trabajo")
	@JsonView(ViewOT.PublicView.class)
	private String telefonoTrabajo;
	@JsonView(ViewOT.PublicView.class)
	private String titulo;
	@JsonView(ViewOT.PublicView.class)
	private String website;
	@Column(name = "direccion_entrega")
	@JsonView(ViewOT.PublicView.class)
	private String direccionEntrega;

	@Transient
	@JsonView(ViewOT.PublicView.class)
	private String nombresCompletos;

	// bi-directional many-to-one association to Catalogo
	@ManyToOne
	@JoinColumn(name = "catalogo")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@RestResource(exported = false)
	@JsonView(ViewOT.PublicView.class)
	private Catalogo catalogo;

	// bi-directional many-to-one association to Ciudad
	@ManyToOne
	@JoinColumn(name = "codigo_ciudad")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@RestResource(exported = false)
	@JsonView(ViewOT.PublicView.class)
	private Ciudad ciudad;

	// bi-directional many-to-one association to Pai
	@ManyToOne
	@JoinColumn(name = "codigo_pais")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@RestResource(exported = false)
	@JsonView(ViewOT.PublicView.class)
	private Pais pais;

	// bi-directional many-to-one association to Pai
	@ManyToOne
	@JoinColumn(name = "codigo_pais_nacionalidad")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@RestResource(exported = false)
	@JsonView(ViewOT.PublicView.class)
	private Pais paisNacionalidad;

	// bi-directional many-to-one association to Profesion
	@ManyToOne
	@JoinColumn(name = "codigo_profesion")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@RestResource(exported = false)
	@JsonView(ViewOT.PublicView.class)
	private Profesion profesion;

	// bi-directional many-to-one association to Provincia
	@ManyToOne
	@JoinColumn(name = "codigo_provincia")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@RestResource(exported = false)
	@JsonView(ViewOT.PublicView.class)
	private Provincia provincia;

	// bi-directional many-to-one association to Region
	@ManyToOne
	@JoinColumn(name = "codigo_region")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@RestResource(exported = false)
	@JsonView(ViewOT.PublicView.class)
	private Region region;

	@ManyToOne
	@JoinColumn(name = "codigo_sucursal")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@RestResource(exported = false)
	@JsonView(ViewOT.PublicView.class)
	private Sucursal sucursal;

	@ManyToOne
	@JoinColumn(name = "codigo_tipo_cliente")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@RestResource(exported = false)
	@JsonView(ViewOT.PublicView.class)
	private TipoCliente tipoCliente;

	@ManyToOne
	@JoinColumn(name = "codigo_empresa")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@RestResource(exported = false)
	@JsonView(ViewOT.PublicView.class)
	private Empresa empresa;

	@OneToMany(mappedBy = "cliente")
	@JsonIgnore
	private List<Enlace> enlaces;

	@ManyToOne
	@JoinColumn(name = "codigo_regional")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@RestResource(exported = false)
	@JsonView(ViewOT.PublicView.class)
	private Regional regional;
	@JsonView(ViewOT.PublicView.class)
	private String logo;
	@JsonView(ViewOT.MasterDetailView.class)
	private String logo_data;

	@Transient
	@JsonView(ViewOT.MasterDetailView.class)
	private String logoTmp;

	public Cliente() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getActividadEconomica() {
		return this.actividadEconomica;
	}

	public void setActividadEconomica(String actividadEconomica) {
		this.actividadEconomica = actividadEconomica;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCelular1() {
		return this.celular1;
	}

	public void setCelular1(String celular1) {
		this.celular1 = celular1;
	}

	public String getCelular2() {
		return this.celular2;
	}

	public void setCelular2(String celular2) {
		this.celular2 = celular2;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDireccionTrabajo() {
		return this.direccionTrabajo;
	}

	public void setDireccionTrabajo(String direccionTrabajo) {
		this.direccionTrabajo = direccionTrabajo;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getExtensionTelefonoTrabajo() {
		return this.extensionTelefonoTrabajo;
	}

	public void setExtensionTelefonoTrabajo(String extensionTelefonoTrabajo) {
		this.extensionTelefonoTrabajo = extensionTelefonoTrabajo;
	}

	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getHorarioAtencion() {
		return this.horarioAtencion;
	}

	public void setHorarioAtencion(String horarioAtencion) {
		this.horarioAtencion = horarioAtencion;
	}

	public String getIdentificacion() {
		return this.identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getLugarTrabajo() {
		return this.lugarTrabajo;
	}

	public String getLogo_data() {
		return logo_data;
	}

	public void setLogo_data(String logo_data) {
		this.logo_data = logo_data;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public void setLugarTrabajo(String lugarTrabajo) {
		this.lugarTrabajo = lugarTrabajo;
	}

	public String getLogoTmp() {
		return logoTmp;
	}

	public void setLogoTmp(String logoTmp) {
		this.logoTmp = logoTmp;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getTelefono1() {
		return this.telefono1;
	}

	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}

	public String getTelefono2() {
		return this.telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	public String getTelefonoTrabajo() {
		return this.telefonoTrabajo;
	}

	public void setTelefonoTrabajo(String telefonoTrabajo) {
		this.telefonoTrabajo = telefonoTrabajo;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getNombresCompletos() {
		if (this.getNombres() != null && this.getApellidos() != null) {
			setNombresCompletos(this.getNombres().concat(" ").concat(this.getApellidos()));
		}
		return nombresCompletos;
	}

	public void setNombresCompletos(String nombresCompletos) {
		this.nombresCompletos = nombresCompletos;
	}

	public Catalogo getCatalogo() {
		return this.catalogo;
	}

	public void setCatalogo(Catalogo catalogo) {
		this.catalogo = catalogo;
	}

	public Ciudad getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public Pais getPais() {
		return this.pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Pais getPaisNacionalidad() {
		return this.paisNacionalidad;
	}

	public void setPaisNacionalidad(Pais paisNacionalidad) {
		this.paisNacionalidad = paisNacionalidad;
	}

	public Profesion getProfesion() {
		return this.profesion;
	}

	public void setProfesion(Profesion profesion) {
		this.profesion = profesion;
	}

	public Provincia getProvincia() {
		return this.provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public Region getRegion() {
		return this.region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public Sucursal getSucursal() {
		return this.sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public TipoCliente getTipoCliente() {
		return this.tipoCliente;
	}

	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public List<Enlace> getEnlaces() {
		return this.enlaces;
	}

	public void setEnlaces(List<Enlace> enlaces) {
		this.enlaces = enlaces;
	}

	public Enlace addEnlace(Enlace enlace) {
		getEnlaces().add(enlace);
		enlace.setCliente(this);

		return enlace;
	}

	public Enlace removeEnlace(Enlace enlace) {
		getEnlaces().remove(enlace);
		enlace.setCliente(null);

		return enlace;
	}

	public Regional getRegional() {
		return regional;
	}

	public void setRegional(Regional regional) {
		this.regional = regional;
	}

	public String getDireccionEntrega() {
		return direccionEntrega;
	}

	public void setDireccionEntrega(String direccionEntrega) {
		this.direccionEntrega = direccionEntrega;
	}

}