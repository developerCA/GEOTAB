package com.tecnolpet.ot.model;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;


/**
 * The persistent class for the distribuidores database table.
 * 
 */
@Entity
@Table(name="distribuidores")
@NamedQuery(name="Distribuidores.findAll", query="SELECT d FROM Distribuidores d")
public class Distribuidores implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codigo")
	@JsonView(ViewOT.PublicView.class)
	private Integer id;
	@JsonView(ViewOT.PublicView.class)
	private String apellidos;
	@JsonView(ViewOT.PublicView.class)
	private String nombres;

	//bi-directional many-to-one association to Regional
	@ManyToOne
	@JoinColumn(name="regional")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@RestResource(exported=false)		
	@JsonView(ViewOT.PublicView.class)
	private Regional regionalBean;

	public Distribuidores() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public Regional getRegionalBean() {
		return this.regionalBean;
	}

	public void setRegionalBean(Regional regionalBean) {
		this.regionalBean = regionalBean;
	}

}