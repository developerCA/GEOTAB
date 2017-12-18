package com.tecnolpet.ot.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tecnolpet.ot.constant.SotApp;
import com.tecnolpet.ot.constant.SotApp.EstadosFechasRenovacion;
import com.tecnolpet.ot.dto.AvisoLineaProductoDto;
import com.tecnolpet.ot.dto.AvisoRecibidoDto;
import com.tecnolpet.ot.dto.ClienteAvisoDto;
import com.tecnolpet.ot.dto.ContactoAvisoDto;
import com.tecnolpet.ot.dto.LineaProductoDto;
import com.tecnolpet.ot.model.Aviso;
import com.tecnolpet.ot.model.AvisoDetalle;
import com.tecnolpet.ot.model.Catalogo;
import com.tecnolpet.ot.model.Cliente;
import com.tecnolpet.ot.model.Distribucion;
import com.tecnolpet.ot.model.Enlace;
import com.tecnolpet.ot.model.FechasRenovacion;
import com.tecnolpet.ot.model.SubCategoria;
import com.tecnolpet.ot.model.Sucursal;
import com.tecnolpet.ot.model.Suscripcion;
import com.tecnolpet.ot.repository.AvisoDetalleRepository;
import com.tecnolpet.ot.repository.AvisoRepository;
import com.tecnolpet.ot.repository.CatalogoRepository;
import com.tecnolpet.ot.repository.DistribucionRepository;
import com.tecnolpet.ot.repository.FechasRenovacionRepository;
import com.tecnolpet.ot.repository.SubcategoriaRepository;
import com.tecnolpet.ot.repository.SucursalRepository;
import com.tecnolpet.ot.repository.SuscripcionRepository;
import com.tecnolpet.ot.repository.TelerenovadorRepository;
import com.tecnolpet.ot.seguridad.UsuarioAuthenticate;

/**
 * 
 * @author Armando Ariel Suárez Pons
 *
 */

@Service
public class AvisoService {

	@Autowired
	private AvisoRepository avisoRepository;

	@Autowired
	private AvisoDetalleRepository avisoDetalleRepository;

	@Autowired
	private SuscripcionRepository suscripcionRepository;

	@Autowired
	private FechasRenovacionRepository fechasRenovacionRepository;

	@Autowired
	private CatalogoRepository catalogoRepository;

	@Autowired
	private TelerenovadorRepository telerenovadorRepository;

	@Autowired
	private SubcategoriaRepository subcategoriaRepository;
	
	@Autowired
	private SucursalRepository sucursalRepository;
	
	@Autowired
	private DistribucionRepository distribucionRepository;
	
	
	public AvisoLineaProductoDto traerSuscripcionesPorFechas(Integer idFecha,
			UsuarioAuthenticate usuario) {

		Catalogo activo = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosSuscripciones.ACTIVA).get(0);
		
		
		Sucursal sucursal=sucursalRepository.findOne(usuario.getSucursal());

		FechasRenovacion fechaRenovacion = fechasRenovacionRepository
				.findOne(idFecha);
		
		
		List<Aviso> avisosRegitros=avisoRepository.findAvisoFechaAndSucursal(fechaRenovacion, sucursal);
		
		
		AvisoLineaProductoDto avisoLineaProducto = new AvisoLineaProductoDto();
		
		if (avisosRegitros.size()>0){
			avisoLineaProducto.setEstado(Boolean.FALSE);
			avisoLineaProducto.setMensaje("Ya existe el registro  de avisos de renovación  para el rango de fechas");
			return avisoLineaProducto;
		}

		avisoLineaProducto.setFechaRenovacion(fechaRenovacion);
		
		List<SubCategoria> subCategorias = subcategoriaRepository.findAll();
		List<LineaProductoDto> lineasProductos = new ArrayList<LineaProductoDto>();

		for (SubCategoria subCategoria : subCategorias) {
			LineaProductoDto lineaProductoDto = new LineaProductoDto();
			lineaProductoDto.setSubcategoria(subCategoria);
			List<Cliente> clientes=suscripcionRepository.findClientesByfechaVencimientoBetweenLinea(activo,fechaRenovacion.getFechaInicio(),fechaRenovacion.getFechaFin(),sucursal,subCategoria);
		
			for(Cliente cliente:clientes){
				ClienteAvisoDto clienteAvisoDto=new ClienteAvisoDto();
				
				List<Enlace> enlaces=suscripcionRepository.findEnlacesByfechaVencimientoBetweenLinea(activo, fechaRenovacion.getFechaInicio(),fechaRenovacion.getFechaFin(),sucursal,subCategoria, cliente);
				
				
				List<ContactoAvisoDto> contactosAvisoDto=new ArrayList<ContactoAvisoDto>();
				
				for (Enlace enlace:enlaces){
					ContactoAvisoDto contactoAvisoDto=new ContactoAvisoDto();
					contactoAvisoDto.setEnlace(enlace);
					contactosAvisoDto.add(contactoAvisoDto);
					
					
					
					List<Suscripcion> suscripciones=suscripcionRepository.findSuscripcionesByfechaVencimientoBetweenLinea(activo,fechaRenovacion.getFechaInicio(),fechaRenovacion.getFechaFin(),sucursal,subCategoria,cliente,enlace);
					
					for (Suscripcion suscripcion:suscripciones){
						AvisoDetalle avisoDetalle=new AvisoDetalle();
						avisoDetalle.setSuscripcion(suscripcion);
						contactoAvisoDto.getListaDetalle().add(avisoDetalle);
					}
				
					
				}
				clienteAvisoDto.setListaContactos(contactosAvisoDto);
				clienteAvisoDto.setCliente(cliente);
				
				lineaProductoDto.getClientes().add(clienteAvisoDto);
				
			}

			lineasProductos.add(lineaProductoDto);
		
		}

		avisoLineaProducto.setLineasProductos(lineasProductos);
		avisoLineaProducto.setEstado(Boolean.TRUE);
		return avisoLineaProducto;
	}
	

	public List<Aviso> getAvisos() {
		return avisoRepository.findAll();
	}

	public List<Aviso> getAvisoPorId(Integer id) {
		return avisoRepository.findById(id);
		
	}

	public FechasRenovacion traerFechasRenovacionRegistradas(
			UsuarioAuthenticate usuario) throws Exception {
		Catalogo registrada = catalogoRepository.findCatalogoBySigla(
				EstadosFechasRenovacion.ACTIVADA).get(0);

		FechasRenovacion fechaRenovacion=null;
		List<FechasRenovacion> fechasRenovacion = fechasRenovacionRepository
				.findByEstado(registrada);

		if (fechasRenovacion.size() > 1) {
			throw new Exception(
					"Existe mas de un fecha de renovación registradas");
		}
		if (fechasRenovacion.isEmpty()){
			fechaRenovacion=null;
		}else{
			fechaRenovacion=fechasRenovacion.get(0);
		}
		
		

		return fechaRenovacion;
	}

	@Transactional
	public void procesarAviso(AvisoLineaProductoDto avisoDto,
			final UsuarioAuthenticate usuario) {
	
		Catalogo procesado = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosAviso.PROCESADO).get(0);
		Sucursal sucursal=sucursalRepository.findOne(usuario.getSucursal());


		for (LineaProductoDto lineasProductoDto:avisoDto.getLineasProductos()){
			for (ClienteAvisoDto cliente:lineasProductoDto.getClientes()){
				for (ContactoAvisoDto contactoAvisoDto : cliente.getListaContactos()){
					Aviso aviso=new Aviso();
					aviso.setCliente(cliente.getCliente());
					aviso.setCodigoFechasRenovacion(avisoDto.getFechaRenovacion());
					aviso.setEstado(procesado);
					aviso.setUsuarioRegistro(usuario.getId());
					
					aviso.setFechaRegistro(new Timestamp(Calendar.getInstance()
							.getTime().getTime()));
					aviso.setProcesado(Boolean.FALSE);
					aviso.setSubcategoria(lineasProductoDto.getSubcategoria());
					aviso.setSucursal(sucursal);
					aviso.setEnlace(contactoAvisoDto.getEnlace());
					avisoRepository.save(aviso);
					
					for (AvisoDetalle avisoDetalle:contactoAvisoDto.getListaDetalle()){
						avisoDetalle.setAviso(aviso);
						avisoDetalleRepository.save(avisoDetalle);
					}
				}
				
				
				
			}
		}

//		fechaRenovacion.setEstado(procesada);
//		fechaRenovacion.setFechaProceso(new Timestamp(Calendar.getInstance()
//				.getTime().getTime()));
//		fechaRenovacion.setUsuarioProcesa(usuario.getId());
//		fechasRenovacionRepository.save(fechaRenovacion);
		
		
		registrarDistribucionInicial(avisoDto,sucursal,usuario);
		
		
	}

	private void registrarDistribucionInicial(AvisoLineaProductoDto avisoLineaProducto,Sucursal sucursal,UsuarioAuthenticate usuario){
		Distribucion distribucion=null;
		Catalogo registrado = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosDistribucion.REGISTRADA).get(0);
		
		for (LineaProductoDto lineasProductoDto:avisoLineaProducto.getLineasProductos()){
			for (ClienteAvisoDto cliente:lineasProductoDto.getClientes()){
				for (ContactoAvisoDto contactoAvisoDto:cliente.getListaContactos()){
					for (AvisoDetalle avisoDetalle:contactoAvisoDto.getListaDetalle()){
						distribucion=new Distribucion();
						distribucion.setCliente(cliente.getCliente());
						distribucion.setEstado(registrado);
						distribucion.setFechaRegistro(new Timestamp(Calendar.getInstance()
								.getTime().getTime()));
						
						distribucion.setSubCategoria(lineasProductoDto.getSubcategoria());
						distribucion.setSucursal(sucursal);
						distribucion.setSuscripcion(avisoDetalle.getSuscripcion());
						distribucion.setUsuarioRegistro(usuario.getId());
						distribucion.setFechaRenovacion(avisoLineaProducto.getFechaRenovacion());
						distribucionRepository.save(distribucion);
						
					}
				}
				
				
			}
		}

		
	}

	public void actualizarAvisoProcesado(AvisoRecibidoDto avisoRecibido, Integer usuario) throws ParseException {
		Catalogo catalogo = catalogoRepository.findCatalogoBySigla("AVIREC")
				.get(0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date date = sdf.parse(avisoRecibido.getFechaEntrega());

		avisoRecibido.getAviso().setFechaEntrega(date);
		avisoRecibido.getAviso().setCodigoUsuarioEntrega(avisoRecibido.getAviso().getCodigoUsuarioEntrega());
		avisoRecibido.getAviso().setFechaEntregaUsuario(new Timestamp(Calendar.getInstance()
				.getTime().getTime()));
		avisoRecibido.getAviso().setFechaRecepcionUsuario(new Timestamp(Calendar.getInstance()
				.getTime().getTime()));
		avisoRecibido.getAviso().setCodigoUsuarioRecibe(usuario);
		avisoRecibido.getAviso().setEstado(catalogo);
		avisoRepository.save(avisoRecibido.getAviso());
	}

}
