package com.tecnolpet.ot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecnolpet.ot.model.Enlace;
import com.tecnolpet.ot.model.NotaPedido;
import com.tecnolpet.ot.model.Producto;
import com.tecnolpet.ot.model.Sucursal;
import com.tecnolpet.ot.model.Suscripcion;
import com.tecnolpet.ot.model.Telerenovador;
import com.tecnolpet.ot.model.Vendedor;
import com.tecnolpet.ot.repository.EnlaceRepository;
import com.tecnolpet.ot.repository.NotaPedidoRepository;
import com.tecnolpet.ot.repository.ProductoRepository;
import com.tecnolpet.ot.repository.SucursalRepository;
import com.tecnolpet.ot.repository.SuscripcionRepository;
import com.tecnolpet.ot.repository.TelerenovadorRepository;
import com.tecnolpet.ot.repository.VendedorRepository;
import com.tecnolpet.ot.seguridad.UsuarioAuthenticate;

@Service
public class ConsultaService {
	
	@Autowired
	private SuscripcionRepository suscripcionRepository;
	
	@Autowired
	private SucursalRepository sucursalRepository; 
	
	@Autowired
	private NotaPedidoRepository notaPedidoRepository;
	
	@Autowired
	private VendedorRepository vendedorRepository;
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private TelerenovadorRepository telerenovadorRepository;
	
	@Autowired
	private EnlaceRepository enlaceRepository;
	
	public List<Suscripcion> getSuscripcionesBySuscripcionInicialDescendente(final Integer suscripcionInicial, final UsuarioAuthenticate usuario){
		Sucursal sucursal = sucursalRepository.findOne(usuario.getSucursal()); 
		return suscripcionRepository.findBySuscripcionInicialAndSucursalOrderByFechaVencimientoDesc(suscripcionInicial, sucursal);
	}
	
	public NotaPedido getNotaPedidoById(Integer id){
		return notaPedidoRepository.findOne(id);
	}
	
	public Vendedor getVendedorById(Integer id){
		return vendedorRepository.findOne(id);
	}
	
	public Sucursal getSucursalById(Integer id){
		return sucursalRepository.findOne(id);
	}
	
	public Telerenovador getTelerenovadorById(Integer id){
		return telerenovadorRepository.findOne(id);
	}
	
	public Producto getProductoById(Integer id){
		return productoRepository.findOne(id);
	}
	
	public Enlace getContactoById(Integer id){
		return enlaceRepository.findOne(id);
	}

}
