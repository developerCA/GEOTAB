package com.tecnolpet.ot.utils;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tecnolpet.ot.model.Localizacion;
import com.tecnolpet.ot.model.Punto;
import com.tecnolpet.ot.model.Zona;
import com.tecnolpet.ot.repository.LocalizacionRepository;
import com.tecnolpet.ot.repository.PuntoRepository;
import com.tecnolpet.ot.repository.ZonaRepository;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;

public class PoligonoUtils {

	LocalizacionRepository localizacionRepository;
	
	@Autowired
	private ZonaRepository zonaRepository;
	
	@Autowired
	private PuntoRepository puntoRepository;
	
	@Test
	public void getPoints() {

	List<Punto> listaPuntos=new ArrayList<>();
	
	Punto punto1=new Punto();
	punto1.setPosx(-78.49515533447266);
	punto1.setPosy(-0.11318914592266083);
	
	listaPuntos.add(punto1);
	
	
	Punto punto2=new Punto();
	punto2.setPosx(-78.49530792236328);
	punto2.setPosy(-0.11324815452098846);
	
	
	listaPuntos.add(punto2);
	
	Punto punto3=new Punto();
	punto3.setPosx(-78.49530792236328);
	punto3.setPosy(-0.11324815452098846);
	
	listaPuntos.add(punto3);
	
	Localizacion localizazion=new Localizacion();
	localizazion.setLatitude(-0.11725627040521738);
	localizazion.setLongitud(-78.49414576208696);
	
	buscarZonaPoligono(listaPuntos, localizazion);
	
	}

	public static boolean buscarZonaPoligono(List<Punto> listaPuntos, Localizacion localizacion) {
	
		
	
		GeometryFactory gf = new GeometryFactory();
		Coordinate coordenadaFinal;
		Polygon poly = null;
		Boolean buscador = false;

		if (listaPuntos.isEmpty()) {
			return false;
		}

		if (listaPuntos.size() <= 2) {
			return false;
		}

		coordenadaFinal = new Coordinate(listaPuntos.get(0).getPosx(), listaPuntos.get(0).getPosy());

		List<Coordinate> listaCoordenas = new ArrayList<Coordinate>();
		for (Punto punto : listaPuntos) {
			Coordinate coordinada = new Coordinate(punto.getPosx(), punto.getPosy());
			listaCoordenas.add(coordinada);
		}
		listaCoordenas.add(coordenadaFinal);

		try {

			Coordinate[] arr = new Coordinate[listaCoordenas.size()];
			arr = listaCoordenas.toArray(arr);

			LinearRing jtsRing = gf.createLinearRing(arr);
			poly = gf.createPolygon(jtsRing, null);
			

			Coordinate coord = new Coordinate(localizacion.getLongitud(), localizacion.getLatitude());
			

			Point pt = gf.createPoint(coord);

			if (poly.contains(pt)) {
				buscador = true;
			} else {
				System.out.println(coord.toString());
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		return buscador;

	}
}
