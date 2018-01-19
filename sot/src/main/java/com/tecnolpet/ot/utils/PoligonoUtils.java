package com.tecnolpet.ot.utils;

import java.util.ArrayList;
import java.util.List;

import com.tecnolpet.ot.model.Localizacion;
import com.tecnolpet.ot.model.Punto;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;

public class PoligonoUtils {

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
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		return buscador;

	}
}
