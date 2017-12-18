/**
 * 
 */
package com.tecnolpet.ot.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecnolpet.ot.constant.SotApp;
import com.tecnolpet.ot.model.Catalogo;
import com.tecnolpet.ot.model.Categoria;
import com.tecnolpet.ot.model.CostoServicio;
import com.tecnolpet.ot.model.PerfilEmpresa;
import com.tecnolpet.ot.model.Tarea;
import com.tecnolpet.ot.repository.CatalogoRepository;
import com.tecnolpet.ot.repository.CategoriaRepository;
import com.tecnolpet.ot.repository.CostoServicioRepository;
import com.tecnolpet.ot.repository.PerfilEmpresaRepository;
import com.tecnolpet.ot.repository.TareaRepository;
import com.tecnolpet.ot.seguridad.UsuarioAuthenticate;

/**
 * @author administrador
 *
 */
@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private PerfilEmpresaRepository perfilEmpresaRepository;

	@Autowired
	private CostoServicioRepository costoServicioRepository;

	@Autowired
	private TareaRepository tareaRepository;

	@Autowired
	private CatalogoRepository catalogoRepository;

	public List<Categoria> traerCategorias(UsuarioAuthenticate usuario) {
		PerfilEmpresa perfilEmpresa = perfilEmpresaRepository.findOne(usuario
				.getPerfil_empresa());
		return categoriaRepository.findCategoriaByEstadoAndEmpresa(true,
				perfilEmpresa.getEmpresa());
	}

	public List<CostoServicio> traerServicioPorTipoProducto(Integer idTipo) {

		Categoria categoria = categoriaRepository.findOne(idTipo);
		Catalogo estado = catalogoRepository.findCatalogoBySigla(
				SotApp.EstadosGenerales.ACTIVO).get(0);
		List<Tarea> listatareas = tareaRepository.findByCatalogoAndEmpresa(
				estado, categoria.getEmpresa());
		CostoServicio costoServicio = null;
		List<CostoServicio> listaServicios = new ArrayList<CostoServicio>();
		for (Tarea t : listatareas) {
			costoServicio = costoServicioRepository
					.findCostosByTipoCategoriaAndTarea(estado, categoria, t);

			if (null == costoServicio) {
				costoServicio = new CostoServicio();
				costoServicio.setTarea(t);
				costoServicio.setCosto(BigDecimal.ZERO);
				costoServicio.setCategoria(categoria);
			}

			listaServicios.add(costoServicio);

		}
		return listaServicios;
	}

	public void guardar(UsuarioAuthenticate usuario, Categoria categoria,
			String op) throws Exception {
		PerfilEmpresa perfilEmpresa = perfilEmpresaRepository.findOne(usuario
				.getPerfil_empresa());

		if (!op.equals("update")) {

			for (Categoria c : traerCategorias(usuario)) {

				if (c.getNombre().equalsIgnoreCase(categoria.getNombre())) {
					throw new Exception(
							"Ya existe una categoría con ese nombre.");
				}

			}
		}

		categoria.setEmpresa(perfilEmpresa.getEmpresa());
		categoria.setEstado(Boolean.TRUE);
		categoriaRepository.save(categoria);
	}

	public void eliminar(Categoria categoria) {
		categoria.setEstado(Boolean.FALSE);
		categoriaRepository.save(categoria);
	}
	
	public void guardarServicios(List<CostoServicio> listaServicios){
		
		Catalogo activo=catalogoRepository.findCatalogoBySigla(SotApp.EstadosGenerales.ACTIVO).get(0);
		for (CostoServicio costo:listaServicios){
			costo.setCatalogo(activo);
			costoServicioRepository.save(costo);
			
		}
		
		
	}

}
