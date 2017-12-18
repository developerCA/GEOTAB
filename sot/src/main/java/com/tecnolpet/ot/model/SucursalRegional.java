package com.tecnolpet.ot.model;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;


/**
 * The persistent class for the sucursal_regional database table.
 * 
 */
@Entity
@Table(name="sucursal_regional")
@NamedQuery(name="SucursalRegional.findAll", query="SELECT s FROM SucursalRegional s")
public class SucursalRegional implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codigo")
	@JsonView(ViewOT.PublicView.class)
	private Integer id;

	//bi-directional many-to-one association to Regional
	@ManyToOne
	@JoinColumn(name="codigo_regional")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)	
	@JsonView(ViewOT.PublicView.class)
	private Regional regional;

	//bi-directional many-to-one association to Sucursal
	@ManyToOne
	@JoinColumn(name="codigo_sucursal")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)	
	@JsonView(ViewOT.PublicView.class)
	private Sucursal sucursal;

	public SucursalRegional() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Regional getRegional() {
		return this.regional;
	}

	public void setRegional(Regional regional) {
		this.regional = regional;
	}

	public Sucursal getSucursal() {
		return this.sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

}