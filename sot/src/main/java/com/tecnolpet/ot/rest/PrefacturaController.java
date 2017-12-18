package com.tecnolpet.ot.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tecnolpet.ot.dto.TotalesDto;
import com.tecnolpet.ot.model.Bodega;
import com.tecnolpet.ot.model.DetalleNotaPedido;
import com.tecnolpet.ot.model.NotaPedido;
import com.tecnolpet.ot.model.SucursalBodega;
import com.tecnolpet.ot.seguridad.UsuarioAuthenticate;
import com.tecnolpet.ot.service.PrefacturaService;

@RestController
@RequestMapping("/api/prefactura")
public class PrefacturaController {

	private final PrefacturaService prefacturaService;
	
	@Autowired
	public PrefacturaController(PrefacturaService prefacturaService) {
		this.prefacturaService = prefacturaService;
	}
	
	@RequestMapping(value="/noPrefacturados", method = RequestMethod.GET)
	public List<NotaPedido> traerPedidosProcesadosYNoPrefacturados(@AuthenticationPrincipal UsuarioAuthenticate usuario){
		return prefacturaService.getNotasPedidoPorPrefacturaFalseYEstadoProcesado(usuario);
	}
	
	@RequestMapping(value="/detallesPorPedido", method = RequestMethod.GET)
	public List<DetalleNotaPedido> traerDetallePorNotaPedido(@RequestParam Integer id){
		return prefacturaService.getDetallesPorNotaPedido(id);
	}
	
	@RequestMapping(value="/totalesPorPedido", method = RequestMethod.PUT)
	public TotalesDto traerTotalesPorNotaPedido(@RequestBody Integer id){
		return prefacturaService.getTotalesPorIdNotaPedido(id);
	}
	
	
	
	@RequestMapping(value="/bodegas", method = RequestMethod.GET)
	public List<Bodega> obtenerBodegaList(@AuthenticationPrincipal UsuarioAuthenticate usuario){
		return prefacturaService.getSucursalesYBodegas(usuario.getSucursal());
	}
	
	@RequestMapping(value="/sucursalBodega", method = RequestMethod.GET)
	public List<SucursalBodega> obtenerSucursalBodegaList(@RequestParam Integer codigoBodega, @AuthenticationPrincipal UsuarioAuthenticate usuario){
		return prefacturaService.getSucursalBodegaPorCodigoBodegaYCodigoSucursal(usuario.getSucursal(), codigoBodega);
	}
	
	
	
	
}
