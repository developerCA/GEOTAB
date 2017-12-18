package com.tecnolpet.ot.model;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.tecnolpet.ot.jview.ViewOT;

import java.util.List;


/**
 * The persistent class for the tipo_cliente database table.
 * 
 */
@Entity
@Table(name="tipo_cliente")
@NamedQuery(name="TipoCliente.findAll", query="SELECT t FROM TipoCliente t")
public class TipoCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codigo")
	@JsonView(ViewOT.PublicView.class)
	private Integer id;
	@JsonView(ViewOT.PublicView.class)
	private String nombre;

	//bi-directional many-to-one association to Cliente
	@OneToMany(mappedBy="tipoCliente")
	@JsonIgnore
	private List<Cliente> clientes;

	public TipoCliente() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Cliente> getClientes() {
		return this.clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Cliente addCliente(Cliente cliente) {
		getClientes().add(cliente);
		cliente.setTipoCliente(this);

		return cliente;
	}

	public Cliente removeCliente(Cliente cliente) {
		getClientes().remove(cliente);
		cliente.setTipoCliente(null);

		return cliente;
	}

}