package com.tecnolpet.ot.model;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;

import java.util.List;


/**
 * The persistent class for the bodega database table.
 * 
 */
@Entity
@NamedQuery(name="Bodega.findAll", query="SELECT b FROM Bodega b")
public class Bodega implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codigo_bodega")
	@JsonView(ViewOT.PublicView.class)
	private Integer id;

	@Column(name="codigo_kohinor")
	@JsonView(ViewOT.PublicView.class)
	private String codigoKohinor;
	@JsonView(ViewOT.PublicView.class)
	private String nombre;

	@ManyToOne
	@JoinColumn(name="estado")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)		
	@JsonView(ViewOT.PublicView.class)
	private Catalogo estado;
	
	//bi-directional many-to-one association to SucursalBodega
	@OneToMany(mappedBy="bodega")
	@JsonIgnore
	private List<SucursalBodega> sucursalBodegas;

	public Bodega() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setCodigoId(Integer id) {
		this.id = id;
	}

	public String getCodigoKohinor() {
		return this.codigoKohinor;
	}

	public void setCodigoKohinor(String codigoKohinor) {
		this.codigoKohinor = codigoKohinor;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Catalogo getEstado() {
		return this.estado;
	}

	public void setEstado(Catalogo estado) {
		this.estado = estado;
	}

	public List<SucursalBodega> getSucursalBodegas() {
		return this.sucursalBodegas;
	}

	public void setSucursalBodegas(List<SucursalBodega> sucursalBodegas) {
		this.sucursalBodegas = sucursalBodegas;
	}

	public SucursalBodega addSucursalBodega(SucursalBodega sucursalBodega) {
		getSucursalBodegas().add(sucursalBodega);
		sucursalBodega.setBodega(this);

		return sucursalBodega;
	}

	public SucursalBodega removeSucursalBodega(SucursalBodega sucursalBodega) {
		getSucursalBodegas().remove(sucursalBodega);
		sucursalBodega.setBodega(null);

		return sucursalBodega;
	}

}