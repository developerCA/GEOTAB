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

import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;


/**
 * The persistent class for the producto database table.
 * 
 */
@Entity
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codigo")
	@JsonView(ViewOT.PublicView.class)
	private Integer id;
	@JsonView(ViewOT.PublicView.class)
	private Boolean actualizable;
	@JsonView(ViewOT.PublicView.class)
	private Boolean renovable;

	@Column(name="aplica_impuesto")
	@JsonView(ViewOT.PublicView.class)
	private boolean aplicaImpuesto;
	
	@Column(name="aplica_suscripcion")
	@JsonView(ViewOT.PublicView.class)
	private boolean aplicaSuscripcion;
	@JsonView(ViewOT.PublicView.class)
	private double costo;
	@JsonView(ViewOT.PublicView.class)
	private String descripcion;

	@Column(name="maximo_accesos")
	@JsonView(ViewOT.PublicView.class)
	private Integer maximoAccesos;

	@Column(name="meses_vigencia")
	@JsonView(ViewOT.PublicView.class)
	private Integer mesesVigencia;

	@Column(name="minimo_accesos")
	@JsonView(ViewOT.PublicView.class)
	private Integer minimoAccesos;
	@JsonView(ViewOT.PublicView.class)
	private String nombre;

	@Column(name="numero_actualizacion")
	@JsonView(ViewOT.PublicView.class)
	private Integer numeroActualizacion;
	

	@ManyToOne
	@JoinColumn(name="codigo_producto_renovacion")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)
	@JsonProperty(value="productoRenovacion")
	@JsonBackReference
	@JsonView(ViewOT.PublicView.class)
	private Producto producto;
		
	@JsonIgnore
	@OneToMany(mappedBy="producto")
	private List<Producto> productos;

	
	@ManyToOne
	@JoinColumn(name="estado_catalogo")
	@JsonView(ViewOT.PublicView.class)
	private Catalogo catalogo;

	
	@ManyToOne
	@JoinColumn(name="codigo_categoria")
	@JsonView(ViewOT.PublicView.class)
	private Categoria categoria;

	
	@ManyToOne
	@JoinColumn(name="codigo_tipo_producto")
	@JsonView(ViewOT.PublicView.class)
	private TipoProducto tipoProducto;
	
	@Column(name="aplica_accesos")
	@JsonView(ViewOT.PublicView.class)
	private boolean aplicaAccesos;
	
	@Column(name="numero_serie")
	@JsonView(ViewOT.PublicView.class)
	private String serie;
	
	@Column(name="codigo_equipo")
	@JsonView(ViewOT.PublicView.class)
	private String codigoEquipo;
	@JsonView(ViewOT.PublicView.class)
	private String nemonico;
	
	@ManyToOne
	@JoinColumn(name="codigo_cliente")
	@JsonView(ViewOT.PublicView.class)
	private Cliente cliente;

	public Producto() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getActualizable() {
		return this.actualizable;
	}

	public void setActualizable(Boolean actualizable) {
		this.actualizable = actualizable;
	}

	public Boolean getRenovable() {
		return renovable;
	}

	public void setRenovable(Boolean renovable) {
		this.renovable = renovable;
	}

	public boolean isAplicaImpuesto() {
		return aplicaImpuesto;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setAplicaImpuesto(boolean aplicaImpuesto) {
		this.aplicaImpuesto = aplicaImpuesto;
	}

	public double getCosto() {
		return this.costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getMaximoAccesos() {
		return this.maximoAccesos;
	}

	public void setMaximoAccesos(Integer maximoAccesos) {
		this.maximoAccesos = maximoAccesos;
	}

	public Integer getMesesVigencia() {
		return this.mesesVigencia;
	}

	public void setMesesVigencia(Integer mesesVigencia) {
		this.mesesVigencia = mesesVigencia;
	}

	public Integer getMinimoAccesos() {
		return this.minimoAccesos;
	}

	public void setMinimoAccesos(Integer minimoAccesos) {
		this.minimoAccesos = minimoAccesos;
	}

	public boolean isAplicaSuscripcion() {
		return aplicaSuscripcion;
	}

	public void setAplicaSuscripcion(boolean aplicaSuscripcion) {
		this.aplicaSuscripcion = aplicaSuscripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getNumeroActualizacion() {
		return this.numeroActualizacion;
	}

	public void setNumeroActualizacion(Integer numeroActualizacion) {
		this.numeroActualizacion = numeroActualizacion;
	}

	

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getCodigoEquipo() {
		return codigoEquipo;
	}

	public void setCodigoEquipo(String codigoEquipo) {
		this.codigoEquipo = codigoEquipo;
	}

	public Catalogo getCatalogo() {
		return this.catalogo;
	}

	public void setCatalogo(Catalogo catalogo) {
		this.catalogo = catalogo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public TipoProducto getTipoProducto() {
		return this.tipoProducto;
	}

	public void setTipoProducto(TipoProducto tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public boolean isAplicaAccesos() {
		return aplicaAccesos;
	}

	public void setAplicaAccesos(boolean aplicaAccesos) {
		this.aplicaAccesos = aplicaAccesos;
	}

	public String getNemonico() {
		return nemonico;
	}

	public void setNemonico(String nemonico) {
		this.nemonico = nemonico;
	}

}
