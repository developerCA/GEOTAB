package com.tecnolpet.ot.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.tecnolpet.ot.constant.SotApp;
import com.tecnolpet.ot.dto.VendedorDto;
import com.tecnolpet.ot.model.Catalogo;
import com.tecnolpet.ot.model.Sucursal;
import com.tecnolpet.ot.model.Vendedor;
import com.tecnolpet.ot.repository.CatalogoRepository;
import com.tecnolpet.ot.repository.SucursalRepository;
import com.tecnolpet.ot.repository.VendedorRepository;

@Service
public class VendedorService {

	@Autowired
	private VendedorRepository vendedorRepository;

	@Autowired
	private Environment env;

	@Autowired
	private CatalogoRepository catalogoRepository;

	@Autowired
	private SucursalRepository sucursalRepository;

	public List<Vendedor> traeVendedores() {

		Catalogo catalogo=catalogoRepository.findCatalogoBySigla(SotApp.EstadosGenerales.ACTIVO).get(0);
		return vendedorRepository.findByEstado(catalogo);

	}

	public List<Sucursal> traeSucursales() {

		return sucursalRepository.findAll();

	}

	public void guardar(VendedorDto vendedorDto, String accion) {
		Catalogo catalogo = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosGenerales.ACTIVO).get(0);

		String nombres = vendedorDto.getNombres();
		String apellidos = vendedorDto.getApellidos();
		Integer id = vendedorDto.getId();
		String cedula = vendedorDto.getCedula();
		Date fecha_nacimiento = vendedorDto.getFecha_nacimiento();
		String telefono = vendedorDto.getTelefono();
		String celular = vendedorDto.getCelular();
		String email = vendedorDto.getEmail();

		Vendedor vendedor = new Vendedor();

		vendedor.setNombres(nombres);
		vendedor.setApellidos(apellidos);
		vendedor.setCedula(cedula);
		vendedor.setTelefono(telefono);
		vendedor.setCelular(celular);
		vendedor.setEmail(email);
		vendedor.setFechaNacimiento(fecha_nacimiento);
		vendedor.setEstado(catalogo);
		vendedor.setCargo(vendedorDto.getCargo());
		if (accion == "actualizar") {
			vendedor.setId(id);
		}
		vendedorRepository.save(vendedor);

	}

	public void eliminar(VendedorDto vendedorDto) {

		Catalogo catalogo = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosGenerales.ELIMINADO).get(0);
		Vendedor vendedor = vendedorRepository.findOne(vendedorDto.getId());
		vendedor.setEstado(catalogo);
		vendedorRepository.save(vendedor);

	}

}
