package com.tecnolpet.ot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecnolpet.ot.constant.SotApp;
import com.tecnolpet.ot.dto.OrdenOTDto;
import com.tecnolpet.ot.dto.OrdenServiciosDto;
import com.tecnolpet.ot.model.Catalogo;
import com.tecnolpet.ot.model.DetalleNotaPedido;
import com.tecnolpet.ot.model.NotaPedido;
import com.tecnolpet.ot.model.PerfilEmpresa;
import com.tecnolpet.ot.model.Tarea;
import com.tecnolpet.ot.model.TareaDetalleNotaPedido;
import com.tecnolpet.ot.repository.CatalogoRepository;
import com.tecnolpet.ot.repository.DetalleNotaPedidoRepository;
import com.tecnolpet.ot.repository.NotaPedidoRepository;
import com.tecnolpet.ot.repository.PerfilEmpresaRepository;
import com.tecnolpet.ot.repository.ProductoRepository;
import com.tecnolpet.ot.repository.TareaDetalleNotaPedidoRepository;
import com.tecnolpet.ot.repository.TareaRepository;
import com.tecnolpet.ot.seguridad.UsuarioAuthenticate;

@Service
public class TareaService {

	@Autowired
	private TareaRepository tareaRepository;

	@Autowired
	private ProductoRepository productoRepository;

	@Autowired
	private CatalogoRepository catalogoRepository;

	@Autowired
	private PerfilEmpresaRepository perfilEmpresaRepository;

	@Autowired
	private TareaDetalleNotaPedidoRepository tareaDetalleNotaPedidoRepository;

	@Autowired
	private NotaPedidoRepository notaPedidoRepository;

	@Autowired
	private DetalleNotaPedidoRepository detalleNotaPedidoRepository;

	public List<Tarea> traerTareas(UsuarioAuthenticate usuario) {

		PerfilEmpresa perfilempresa = perfilEmpresaRepository.findOne(usuario
				.getPerfil_empresa());
		Catalogo catalogo = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosGenerales.ACTIVO).get(0);
		List<Tarea> lista = tareaRepository.findByCatalogoAndEmpresa(catalogo,
				perfilempresa.getEmpresa());

		return lista;

	}

	public OrdenOTDto traerServicio(Integer qr) {
		OrdenOTDto orden = new OrdenOTDto();

		TareaDetalleNotaPedido servicio = tareaDetalleNotaPedidoRepository
				.findOne(qr);

		NotaPedido ordenCompleta = notaPedidoRepository.findOne(servicio
				.getDetalleNotaPedido().getNotaPedido().getId());

		orden.setOrdenCompleta(ordenCompleta);
		orden.setServicio(servicio);

		return orden;
	}

	public OrdenServiciosDto traerServiciosOT(Integer qr) {
		OrdenServiciosDto orden = new OrdenServiciosDto();
		Catalogo catalogoRegistrado = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosPedido.REGISTRADO).get(0);

		TareaDetalleNotaPedido servicio = tareaDetalleNotaPedidoRepository
				.findOne(qr);

		NotaPedido ordenCompleta = notaPedidoRepository.findOne(servicio
				.getDetalleNotaPedido().getNotaPedido().getId());

		List<DetalleNotaPedido> detalle = detalleNotaPedidoRepository
				.findDetalleNotaPedido(ordenCompleta, catalogoRegistrado);

		for (DetalleNotaPedido dnp : detalle) {
			orden.getListaServicios().addAll(
					tareaDetalleNotaPedidoRepository.findTareas(dnp));

		}

		return orden;
	}

}
