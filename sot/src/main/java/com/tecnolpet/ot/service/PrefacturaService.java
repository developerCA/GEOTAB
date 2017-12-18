package com.tecnolpet.ot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tecnolpet.ot.constant.SotApp;
import com.tecnolpet.ot.dto.TotalesDto;
import com.tecnolpet.ot.model.Bodega;
import com.tecnolpet.ot.model.Catalogo;
import com.tecnolpet.ot.model.DetalleNotaPedido;
import com.tecnolpet.ot.model.NotaPedido;
import com.tecnolpet.ot.model.PerfilEmpresa;
import com.tecnolpet.ot.model.Sucursal;
import com.tecnolpet.ot.model.SucursalBodega;
import com.tecnolpet.ot.repository.BodegaRepository;
import com.tecnolpet.ot.repository.CatalogoRepository;
import com.tecnolpet.ot.repository.ClienteRepository;
import com.tecnolpet.ot.repository.NotaPedidoDetalleRepository;
import com.tecnolpet.ot.repository.NotaPedidoRepository;
import com.tecnolpet.ot.repository.PerfilEmpresaRepository;
import com.tecnolpet.ot.repository.ProductoRepository;
import com.tecnolpet.ot.repository.SucursalBodegaRepository;
import com.tecnolpet.ot.repository.SucursalRepository;
import com.tecnolpet.ot.repository.VendedorRepository;
import com.tecnolpet.ot.seguridad.UsuarioAuthenticate;

@Service
@PropertySources(value = { @PropertySource("classpath:/application.properties") })
public class PrefacturaService {

	@Autowired
	private Environment env;

	@Autowired
	private NotaPedidoRepository notaPedidoRepository;

	@Autowired
	private NotaPedidoDetalleRepository notaPedidoDetalleRepository;

	@Autowired
	private CatalogoRepository catalogoRepository;

	@Autowired
	private BodegaRepository bodegaRepository;

	@Autowired
	private SucursalBodegaRepository sucursalBodegaRepository;

	@Autowired
	private SucursalRepository sucursalRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ProductoRepository productoRepository;

	@Autowired
	private VendedorRepository vendedorRepository;
	
	@Autowired
	private PerfilEmpresaRepository perfilEmpresaRepository;

	public List<NotaPedido> getNotasPedidoPorPrefacturaFalseYEstadoProcesado(
			UsuarioAuthenticate usuario) {
		Catalogo procesadas = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosSuscripciones.GENERADA).get(0);

		PerfilEmpresa perfilEmpresa = perfilEmpresaRepository.findOne(usuario
				.getPerfil_empresa());
		
		return notaPedidoRepository.findByIdCatalogoAndPrefacturaFalse(
				perfilEmpresa.getEmpresa(), procesadas);
	}

	public List<DetalleNotaPedido> getDetallesPorNotaPedido(Integer id) {
		NotaPedido notaPedido = notaPedidoRepository.findOne(id);

		return notaPedidoDetalleRepository
				.findDetalleNotaPedidoByNotaPedidoAndCatalogo(notaPedido);
	}

	@Transactional
	public TotalesDto getTotalesPorIdNotaPedido(Integer id) {

		TotalesDto totalesDto = new TotalesDto();

		double subtotal = 0;
		double descuento = 0;
		double impuesto_0 = 0;
		double impuesto = 0;
		double total = 0;

		NotaPedido notaPedido = notaPedidoRepository.findOne(id);

		List<DetalleNotaPedido> detalleNotaPedido = notaPedidoDetalleRepository
				.findDetalleNotaPedidoByNotaPedidoAndCatalogo(notaPedido);

		for (DetalleNotaPedido detalle : detalleNotaPedido) {
			subtotal += detalle.getSubtotal();
			descuento += detalle.getDescuento();
			impuesto_0 += detalle.getValorImpuesto0();
			impuesto += detalle.getValorImpuesto();
			total += detalle.getTotal();
		}

		totalesDto.setDescuento(descuento);
		totalesDto.setTotal(total);
		totalesDto.setImpuesto_0(impuesto_0);
		totalesDto.setImpuesto(impuesto);
		totalesDto.setSubtotal(subtotal);

		return totalesDto;
	}

	public List<Bodega> getBodegas() {
		return bodegaRepository.findAll();
	}

	public List<Bodega> getSucursalesYBodegas(Integer codigoSucursal) {
		List<Bodega> res = new ArrayList<Bodega>();

		Sucursal sucursal = sucursalRepository.findOne(codigoSucursal);
		List<SucursalBodega> lista = sucursalBodegaRepository
				.findByCodigoSucursal(sucursal);
		for (SucursalBodega sucursalBodega : lista) {
			res.add(sucursalBodega.getBodega());
		}
		return res;
	}

	public List<SucursalBodega> getSucursalBodegaPorCodigoBodegaYCodigoSucursal(
			Integer codigoSucursal, Integer codigoBodega) {
		Sucursal sucursal = sucursalRepository.findOne(codigoSucursal);
		Bodega bodega = bodegaRepository.findOne(codigoBodega);

		List<SucursalBodega> sucursalBodega = sucursalBodegaRepository
				.findByCodigoSucursalAndBodega(sucursal, bodega);

		return sucursalBodega;
	}

	

	

	

}
