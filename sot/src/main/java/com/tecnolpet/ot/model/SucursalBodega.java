package com.tecnolpet.ot.model;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;


/**
 * The persistent class for the sucursal_bodega database table.
 * 
 */
@Entity
@Table(name="sucursal_bodega")
@NamedQuery(name="SucursalBodega.findAll", query="SELECT s FROM SucursalBodega s")
public class SucursalBodega implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codigo")
	@JsonView(ViewOT.PublicView.class)
	private Integer id;

	@ManyToOne
	@JoinColumn(name="codigo_sucursal")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)	
	@JsonView(ViewOT.PublicView.class)
	private Sucursal codigoSucursal;

	//bi-directional many-to-one association to Bodega
	@ManyToOne
	@JoinColumn(name="codigo_bodega")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)		
	@JsonView(ViewOT.PublicView.class)
	private Bodega bodega;

	public SucursalBodega() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Sucursal getCodigoSucursal() {
		return this.codigoSucursal;
	}

	public void setCodigoSucursal(Sucursal codigoSucursal) {
		this.codigoSucursal = codigoSucursal;
	}

	public Bodega getBodega() {
		return this.bodega;
	}

	public void setBodega(Bodega bodega) {
		this.bodega = bodega;
	}

}