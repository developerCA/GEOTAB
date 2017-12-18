package com.tecnolpet.ot.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.tecnolpet.ot.model.Cliente;
import com.tecnolpet.ot.model.DetalleNotaPedido;
import com.tecnolpet.ot.model.Telerenovador;

public class GestionRenovacionDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Cliente Cliente;
	
	private List<DistribucionRenovacionDto> listaDistribuciones=new ArrayList<DistribucionRenovacionDto>();

	private String motivo;
	
	private Telerenovador telerenovador;
	
	private Boolean isRenovar;
	
	private Boolean pasoRenovacion;
	
	private Boolean pasoNP;
	
	private PedidoDto pedidoDto;
	
	private DetalleNotaPedido detallePedido;
	
	
	public Cliente getCliente() {
		return Cliente;
	}

	public void setCliente(Cliente cliente) {
		Cliente = cliente;
	}

	
	

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public List<DistribucionRenovacionDto> getListaDistribuciones() {
		return listaDistribuciones;
	}

	public void setListaDistribuciones(
			List<DistribucionRenovacionDto> listaDistribuciones) {
		this.listaDistribuciones = listaDistribuciones;
	}

	public Telerenovador getTelerenovador() {
		return telerenovador;
	}

	public void setTelerenovador(Telerenovador telerenovador) {
		this.telerenovador = telerenovador;
	}

	public Boolean getIsRenovar() {
		return isRenovar;
	}

	public void setIsRenovar(Boolean isRenovar) {
		this.isRenovar = isRenovar;
	}

	public Boolean getPasoRenovacion() {
		return pasoRenovacion;
	}

	public Boolean getPasoNP() {
		return pasoNP;
	}

	public void setPasoRenovacion(Boolean pasoRenovacion) {
		this.pasoRenovacion = pasoRenovacion;
	}

	public void setPasoNP(Boolean pasoNP) {
		this.pasoNP = pasoNP;
	}

	public PedidoDto getPedidoDto() {
		return pedidoDto;
	}

	public void setPedidoDto(PedidoDto pedidoDto) {
		this.pedidoDto = pedidoDto;
	}

	public DetalleNotaPedido getDetallePedido() {
		return detallePedido;
	}

	public void setDetallePedido(DetalleNotaPedido detallePedido) {
		this.detallePedido = detallePedido;
	}

}
