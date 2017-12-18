package com.tecnolpet.ot.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.tecnolpet.ot.model.ProductoActualizacion;
import com.tecnolpet.ot.model.ProductoActualizacionDetalle;

public class ActualizacionProductoDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ProductoActualizacion productoActualizacion;
	private List<ProductoActualizacionDetalle> listaProductoActualizacionDetalle = new ArrayList<ProductoActualizacionDetalle>();
	
	public ProductoActualizacion getProductoActualizacion() {
		return productoActualizacion;
	}
	public void setProductoActualizacion(ProductoActualizacion productoActualizacion) {
		this.productoActualizacion = productoActualizacion;
	}
	public List<ProductoActualizacionDetalle> getListaProductoActualizacionDetalle() {
		return listaProductoActualizacionDetalle;
	}
	public void setListaProductoActualizacionDetalle(
			List<ProductoActualizacionDetalle> listaProductoActualizacionDetalle) {
		this.listaProductoActualizacionDetalle = listaProductoActualizacionDetalle;
	}		
	
}
