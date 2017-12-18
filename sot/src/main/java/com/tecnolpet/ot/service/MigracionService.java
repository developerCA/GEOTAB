package com.tecnolpet.ot.service;

import java.io.File;
import java.util.List;
import java.util.Locale;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.biff.CountryCode;
import jxl.read.biff.BiffException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tecnolpet.ot.constant.SotApp;
import com.tecnolpet.ot.model.Catalogo;
import com.tecnolpet.ot.model.Categoria;
import com.tecnolpet.ot.model.Ciudad;
import com.tecnolpet.ot.model.Cliente;
import com.tecnolpet.ot.model.Enlace;
import com.tecnolpet.ot.model.Pais;
import com.tecnolpet.ot.model.Producto;
import com.tecnolpet.ot.model.Profesion;
import com.tecnolpet.ot.model.Regional;
import com.tecnolpet.ot.model.Sucursal;
import com.tecnolpet.ot.model.TipoCliente;
import com.tecnolpet.ot.model.TipoProducto;
import com.tecnolpet.ot.repository.CatalogoRepository;
import com.tecnolpet.ot.repository.CategoriaRepository;
import com.tecnolpet.ot.repository.CiudadRepository;
import com.tecnolpet.ot.repository.ClienteRepository;
import com.tecnolpet.ot.repository.EnlaceRepository;
import com.tecnolpet.ot.repository.PaisRepository;
import com.tecnolpet.ot.repository.ProductoRepository;
import com.tecnolpet.ot.repository.ProfesionRepository;
import com.tecnolpet.ot.repository.RegionalRepository;
import com.tecnolpet.ot.repository.SucursalRepository;
import com.tecnolpet.ot.repository.TipoClienteRepository;
import com.tecnolpet.ot.repository.TipoProductoRepository;

@Service
@PropertySources(value = { @PropertySource("classpath:/application.properties") })
public class MigracionService {

	@Autowired
	private Environment env;

	@Autowired
	private TipoClienteRepository tipoClienteRepository;

	@Autowired
	private SucursalRepository sucursalRepository;

	@Autowired
	private CiudadRepository ciudadRepository;

	@Autowired
	private PaisRepository paisRepository;

	@Autowired
	private ProfesionRepository profesionRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private CatalogoRepository catalogoRepository;

	@Autowired
	private EnlaceRepository enlaceRepository;

	@Autowired
	private RegionalRepository regionalRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private TipoProductoRepository tipoProductoRepository;

	@Autowired
	private ProductoRepository productoRepository;

	@Transactional
	public void migrarClientes() throws Exception {

		String inputFile = env.getProperty("migracion.ruta.clientes");
		File inputWorkbook = new File(inputFile);
		Catalogo estado = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosGenerales.ACTIVO).get(0);

		Workbook w;
		try {

			WorkbookSettings wbSettings = new WorkbookSettings();

			wbSettings.setEncoding("ISO-8859-1");
			wbSettings.setLocale(new Locale("es", "ES"));
			wbSettings.setExcelDisplayLanguage("ES");
			wbSettings.setExcelRegionalSettings("ES");
			wbSettings.setCharacterSet(CountryCode.SPAIN.getValue());

			w = Workbook.getWorkbook(inputWorkbook, wbSettings);

			// Get the first sheet
			Sheet sheet = w.getSheet(0);
			// Loop over first 10 column and lines
			for (int j = 0; j < sheet.getRows(); j++) {
				if (j != 0) {
					Cliente cliente = new Cliente();

					Cell cellTipoCliente = sheet.getCell(0, j);
					TipoCliente tipoCliente = tipoClienteRepository
							.findByNombre(cellTipoCliente.getContents()
									.toUpperCase());

					if (tipoCliente == null) {

						throw new Exception("No existe el tipo de cliente " + j);
					}

					Cell cellCodigoSucursal = sheet.getCell(1, j);

					Sucursal sucursalCliente = sucursalRepository
							.findOne(Integer.parseInt(cellCodigoSucursal
									.getContents()));

					if (sucursalCliente == null) {
						throw new Exception(
								"No existe la sucursal del cliente "
										+ cellCodigoSucursal.getContents());

					}

					Cell cellTitulo = sheet.getCell(2, j);

					Cell cellCodigoCiudad = sheet.getCell(3, j);

					Ciudad ciudadCliente = ciudadRepository.findOne(Integer
							.parseInt(cellCodigoCiudad.getContents()));

					if (ciudadCliente == null) {
						throw new Exception("No existe la ciudad del cliente "
								+ cellCodigoCiudad.getContents());

					}

					Cell cellCodigoNacionalidad = sheet.getCell(4, j);

					Pais paisCliente = paisRepository.findOne(Integer
							.parseInt(cellCodigoNacionalidad.getContents()));

					if (paisCliente == null) {
						throw new Exception(
								"No existe la nacionalidad del cliente "
										+ cellCodigoNacionalidad.getContents());

					}

					Cell cellCodigoprofesion = sheet.getCell(5, j);
					Profesion profesionCliente = null;

					if (!cellCodigoprofesion.getContents().equals("")) {
						profesionCliente = profesionRepository.findOne(Integer
								.parseInt(cellCodigoprofesion.getContents()));
					}

					Cell cellNombres = sheet.getCell(6, j);
					Cell cellApellidos = sheet.getCell(7, j);
					Cell cellRUC = sheet.getCell(8, j);
					Cell cellTelefono1 = sheet.getCell(9, j);
					Cell cellTelefono2 = sheet.getCell(10, j);
					Cell cellCelular = sheet.getCell(11, j);
					Cell cellEmail = sheet.getCell(12, j);
					Cell cellDireccion = sheet.getCell(13, j);
					Cell cellActividad = sheet.getCell(14, j);
					Cell cellDirTrabajo = sheet.getCell(15, j);
					Cell cellTelTrabajo = sheet.getCell(16, j);
					Cell cellExtTrabajo = sheet.getCell(17, j);
					Cell cellObservacion = sheet.getCell(18, j);
					Cell cellRegional = sheet.getCell(19, j);

					Regional regionalCliente = regionalRepository
							.findOne(Integer.parseInt(cellRegional
									.getContents()));

					if (regionalCliente == null) {

						throw new Exception(
								"No existe la regional del cliente " + j);

					}

					cliente.setTipoCliente(tipoCliente);
					cliente.setSucursal(sucursalCliente);
					cliente.setTitulo(cellTitulo.getContents().equals("") ? null
							: cellTitulo.getContents());
					cliente.setCiudad(ciudadCliente);
					cliente.setProvincia(ciudadCliente.getProvincia());
					cliente.setPais(ciudadCliente.getProvincia().getPais());
					cliente.setPaisNacionalidad(paisCliente);
					cliente.setProfesion(profesionCliente);
					cliente.setNombres(cellNombres.getContents().equals("") ? null
							: cellNombres.getContents());
					cliente.setApellidos(cellApellidos.getContents().equals("") ? null
							: cellApellidos.getContents());
					cliente.setIdentificacion(cellRUC.getContents().equals("") ? null
							: cellRUC.getContents());
					cliente.setTelefono1(cellTelefono1.getContents().equals("") ? null
							: cellTelefono1.getContents());
					cliente.setTelefono2(cellTelefono2.getContents().equals("") ? null
							: cellTelefono2.getContents());
					cliente.setCelular1(cellCelular.getContents().equals("") ? null
							: cellCelular.getContents());
					cliente.setEmail(cellEmail.getContents().equals("") ? null
							: cellEmail.getContents());
					cliente.setDireccion(cellDireccion.getContents().equals("") ? null
							: cellDireccion.getContents());
					cliente.setActividadEconomica(cellActividad.getContents()
							.equals("") ? null : cellActividad.getContents());
					cliente.setDireccionTrabajo(cellDirTrabajo.getContents()
							.equals("") ? null : cellDirTrabajo.getContents());
					cliente.setTelefonoTrabajo(cellTelTrabajo.getContents()
							.equals("") ? null : cellTelTrabajo.getContents());
					cliente.setExtensionTelefonoTrabajo(cellExtTrabajo
							.getContents().equals("") ? null : cellExtTrabajo
							.getContents());
					cliente.setObservaciones(cellObservacion.getContents()
							.equals("") ? null : cellObservacion.getContents());
					cliente.setRegional(regionalCliente);
					cliente.setCatalogo(estado);
					clienteRepository.save(cliente);

				}

			}

		} catch (BiffException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void migrarContactos() throws Exception {

		String inputFile = env.getProperty("migracion.ruta.contactos");
		File inputWorkbook = new File(inputFile);

		Workbook w;
		try {

			WorkbookSettings wbSettings = new WorkbookSettings();

			wbSettings.setEncoding("ISO-8859-1");
			wbSettings.setLocale(new Locale("es", "ES"));
			wbSettings.setExcelDisplayLanguage("ES");
			wbSettings.setExcelRegionalSettings("ES");
			wbSettings.setCharacterSet(CountryCode.SPAIN.getValue());

			w = Workbook.getWorkbook(inputWorkbook, wbSettings);

			// Get the first sheet
			Sheet sheet = w.getSheet(0);
			// Loop over first 10 column and lines
			for (int j = 0; j < sheet.getRows(); j++) {
				if (j != 0) {
					try {

						Enlace enlace = new Enlace();

						Cell cellCodigoSucursal = sheet.getCell(0, j);

						Sucursal sucursalContacto = sucursalRepository
								.findOne(Integer.parseInt(cellCodigoSucursal
										.getContents()));

						if (sucursalContacto == null) {
							throw new Exception(
									"No existe la sucursal del contacto "
											+ cellCodigoSucursal.getContents());

						}

						Cell cellRegional = sheet.getCell(15, j);

						Regional regionalContacto = regionalRepository
								.findOne(Integer.parseInt(cellRegional
										.getContents()));

						if (regionalContacto == null) {

							throw new Exception(
									"No existe la regional del contacto " + j);

						}

						Cell cellIdentificador = sheet.getCell(1, j);

						List<Cliente> clientes = clienteRepository
								.findBySucursalAndIdentificacionAndRegional(
										sucursalContacto,
										cellIdentificador.getContents(),
										regionalContacto);

						if (clientes == null) {

							throw new Exception("No existe el cliente  "
									+ cellIdentificador.getContents());

						}

						if (clientes.size() == 0) {
							throw new Exception(
									"No encontro el cliente para el contacto "
											+ cellIdentificador.getContents());

						}
						if (clientes.size() > 1) {
							
						}

						Cliente cliente = clientes.get(0);
						Cell cellCodigoCiudad = sheet.getCell(2, j);

						Ciudad ciudadContacto = ciudadRepository
								.findOne(Integer.parseInt(cellCodigoCiudad
										.getContents()));

						if (ciudadContacto == null) {
							throw new Exception(
									"No existe la ciudad del contacto "
											+ cellCodigoCiudad.getContents());

						}

						Cell cellCodigoprofesion = sheet.getCell(3, j);
						Profesion profesionContacto = null;

						if (!cellCodigoprofesion.getContents().equals("")) {
							profesionContacto = profesionRepository
									.findOne(Integer
											.parseInt(cellCodigoprofesion
													.getContents()));
						}

						Cell cellNombres = sheet.getCell(4, j);
						Cell cellApellidos = sheet.getCell(5, j);
						Cell cellRUC = sheet.getCell(6, j);
						Cell cellTelefono1 = sheet.getCell(7, j);
						Cell cellTelefono2 = sheet.getCell(8, j);
						Cell cellExtension = sheet.getCell(9, j);
						Cell cellCelular = sheet.getCell(10, j);
						Cell cellEmail = sheet.getCell(11, j);
						Cell cellDireccion = sheet.getCell(12, j);
						Cell cellDepartamento = sheet.getCell(13, j);
						Cell cellObservacion = sheet.getCell(14, j);

						enlace.setCliente(cliente);
						enlace.setCiudad(ciudadContacto);
						enlace.setProvincia(ciudadContacto.getProvincia());
						enlace.setPais(ciudadContacto.getProvincia().getPais());
						enlace.setProfesion(profesionContacto);
						enlace.setNombres(cellNombres.getContents());
						enlace.setApellidos(cellApellidos.getContents());
						enlace.setIdentificacion(cellRUC.getContents());
						enlace.setTelefono1(cellTelefono1.getContents().equals(
								"") ? null : cellTelefono1.getContents());
						enlace.setTelefono2(cellTelefono2.getContents().equals(
								"") ? null : cellTelefono2.getContents());
						enlace.setExtension(cellExtension.getContents().equals(
								"") ? null : cellExtension.getContents());
						enlace.setCelular(cellCelular.getContents().equals("") ? null
								: cellCelular.getContents());
						enlace.setEmail(cellEmail.getContents().equals("") ? null
								: cellEmail.getContents());
						enlace.setDepartamentoTrabajo(cellDepartamento
								.getContents().equals("") ? null
								: cellDepartamento.getContents());
						enlace.setDireccion(cellDireccion.getContents().equals(
								"") ? null : cellDireccion.getContents());
						enlace.setObservaciones(cellObservacion.getContents()
								.equals("") ? null : cellObservacion
								.getContents());

						enlaceRepository.save(enlace);

					} catch (Exception ex) {

						
						
						throw new Exception(ex.getMessage());

					}

				}

			}

		} catch (BiffException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void migrarProductos() throws Exception {

		String inputFile = env.getProperty("migracion.ruta.productos");
		File inputWorkbook = new File(inputFile);

		Workbook w;
		try {

			WorkbookSettings wbSettings = new WorkbookSettings();

			wbSettings.setEncoding("ISO-8859-1");
			wbSettings.setLocale(new Locale("es", "ES"));
			wbSettings.setExcelDisplayLanguage("ES");
			wbSettings.setExcelRegionalSettings("ES");
			wbSettings.setCharacterSet(CountryCode.SPAIN.getValue());

			w = Workbook.getWorkbook(inputWorkbook, wbSettings);

			// Get the first sheet
			Sheet sheet = w.getSheet(0);
			// Loop over first 10 column and lines
			for (int j = 0; j < sheet.getRows(); j++) {
				if (j != 0) {
					try {

						Producto producto = new Producto();
						Catalogo activo = catalogoRepository
								.findCatalogoBySigla(
										SotApp.EstadosGenerales.ACTIVO).get(
										0);

						Cell cellCodigoProducto = sheet.getCell(0, j);

						Cell cellCategoria = sheet.getCell(1, j);

						Categoria categoriaProducto = categoriaRepository
								.findOne(Integer.parseInt(cellCategoria
										.getContents()));

						if (categoriaProducto == null) {
							throw new Exception(
									"No existe la categoria del producto " + j);

						}

						Cell cellTipoProducto = sheet.getCell(2, j);

						TipoProducto tipoProducto = tipoProductoRepository
								.findOne(Integer.parseInt(cellTipoProducto
										.getContents()));

						if (tipoProducto == null) {

							throw new Exception(
									"No existe el tipo de producto " + j);

						}

						Cell cellNombre = sheet.getCell(3, j);
						Cell cellDescripcion = sheet.getCell(4, j);

						Cell cellCosto = sheet.getCell(5, j);
						Double costo = Double.valueOf(cellCosto.getContents());

						Cell cellAplicaAcceso = sheet.getCell(6, j);
						Boolean aplicaAcceso = cellAplicaAcceso.getContents()
								.equals("SI") ? true : false;
						Cell cellMinimoAcceso = sheet.getCell(7, j);
						Cell cellMaximoAcceso = sheet.getCell(8, j);
						Cell cellAplicaActualizacion = sheet.getCell(9, j);
						Cell cellNumeroActualizacion = sheet.getCell(10, j);
						Cell cellMesesVigencia = sheet.getCell(11, j);
						Cell cellAplicaImpuesto = sheet.getCell(12, j);
						Cell cellAplicaSuscripcion = sheet.getCell(13, j);
						Cell cellAplicaRenovacion = sheet.getCell(14, j);
					
						Boolean aplicaActualizacion = cellAplicaActualizacion
								.getContents().equals("SI") ? true : false;
						Boolean aplicaImpuesto = cellAplicaImpuesto
								.getContents().equals("SI") ? true : false;
						Boolean aplicaSuscripcion = cellAplicaSuscripcion
								.getContents().equals("SI") ? true : false;

						Boolean aplicaRenovacion = cellAplicaRenovacion
								.getContents().equals("SI") ? true : false;

						producto.setId(Integer.parseInt(cellCodigoProducto
								.getContents()));
						producto.setCategoria(categoriaProducto);
						producto.setTipoProducto(tipoProducto);
						producto.setNombre(cellNombre.getContents());
						producto.setDescripcion(cellDescripcion.getContents());
						producto.setCosto(costo);
						producto.setAplicaAccesos(aplicaAcceso);
						producto.setMinimoAccesos(Integer
								.parseInt(cellMinimoAcceso.getContents()));
						producto.setMaximoAccesos(Integer
								.parseInt(cellMaximoAcceso.getContents()));
						producto.setActualizable(aplicaActualizacion);
						producto.setNumeroActualizacion(Integer
								.parseInt(cellNumeroActualizacion.getContents()));
						producto.setMesesVigencia(Integer
								.parseInt(cellMesesVigencia.getContents()));
						producto.setAplicaImpuesto(aplicaImpuesto);
						producto.setAplicaSuscripcion(aplicaSuscripcion);
						producto.setRenovable(aplicaRenovacion);
						producto.setCatalogo(activo);

						productoRepository.save(producto);

					} catch (Exception ex) {

						
						throw new Exception(ex.getMessage());

					}

				}

			}

		} catch (BiffException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void migrarProductosRenovacion() throws Exception {

		String inputFile = env.getProperty("migracion.ruta.productos");
		File inputWorkbook = new File(inputFile);

		Workbook w;
		try {

			WorkbookSettings wbSettings = new WorkbookSettings();

			wbSettings.setEncoding("ISO-8859-1");
			wbSettings.setLocale(new Locale("es", "ES"));
			wbSettings.setExcelDisplayLanguage("ES");
			wbSettings.setExcelRegionalSettings("ES");
			wbSettings.setCharacterSet(CountryCode.SPAIN.getValue());

			w = Workbook.getWorkbook(inputWorkbook, wbSettings);

			// Get the first sheet
			Sheet sheet = w.getSheet(0);
			// Loop over first 10 column and lines
			for (int j = 0; j < sheet.getRows(); j++) {
				if (j != 0) {
					try {

						Producto producto; 
						Cell cellCodigo = sheet.getCell(0, j);
						Integer codigo=Integer.parseInt(cellCodigo.getContents());
						
						Cell cellAplicaRenovacion = sheet.getCell(14, j);
						Cell cellCodigoRenovacion = sheet.getCell(15, j);
						Integer codigoRenovacion = Integer
								.parseInt(cellCodigoRenovacion.getContents());

						
						Boolean aplicaRenovacion = cellAplicaRenovacion
								.getContents().equals("SI") ? true : false;

						if (aplicaRenovacion){
							producto=productoRepository.findOne(codigo);
							Producto productoRelacion=productoRepository.findOne(codigoRenovacion);
							producto.setProducto(productoRelacion);
							productoRepository.save(producto);
						}
					
					

					} catch (Exception ex) {

						
						throw new Exception(ex.getMessage());

					}

				}

			}

		} catch (BiffException e) {
			e.printStackTrace();
		}
	}

}
