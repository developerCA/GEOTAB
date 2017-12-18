/**
 * 
 */
package com.tecnolpet.ot.utils;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * @author administrador
 *
 */
public class FechasUtils {
	public static Timestamp alteraFechas(Date FechaAModificar,
			Integer cantidadMeses) {
		Calendar fecha = Calendar.getInstance();

		fecha.setTimeInMillis(FechaAModificar.getTime());
		fecha.add(Calendar.MONTH, cantidadMeses);
		Timestamp timestamp = new Timestamp(fecha.getTimeInMillis());
		return timestamp;
	}

	public static Timestamp alteraDias(Date FechaAModificar,
			Integer cantidadDias) {
		Calendar fecha = Calendar.getInstance();

		fecha.setTimeInMillis(FechaAModificar.getTime());
		fecha.add(Calendar.DAY_OF_MONTH, cantidadDias);
		Timestamp timestamp = new Timestamp(fecha.getTimeInMillis());
		return timestamp;
	}

	public static boolean validaFechas(final Date fechaInicio,
			final Date fechaFin) {
		return fechaInicio.compareTo(fechaFin) < 0 ? Boolean.TRUE
				: Boolean.FALSE;
	}

	public static Date alteraFechasDate(Date FechaAModificar,
			Integer cantidadMeses) {
		Calendar fecha = Calendar.getInstance();

		fecha.setTimeInMillis(FechaAModificar.getTime());
		fecha.add(Calendar.MONTH, cantidadMeses);
		// Timestamp timestamp = new Timestamp(fecha.getTimeInMillis());
		return fecha.getTime();
	}

	public static Date alteraDiasDate(Date FechaAModificar, Integer cantidadDias) {
		Calendar fecha = Calendar.getInstance();

		fecha.setTimeInMillis(FechaAModificar.getTime());
		fecha.add(Calendar.DAY_OF_MONTH, cantidadDias);
		// Timestamp timestamp = new Timestamp(fecha.getTimeInMillis());
		return fecha.getTime();
	}

	public static int diferenciaEnDias(Date fechaMayor, Date fechaMenor) {
		
		if (null==fechaMayor || null==fechaMenor){
			return 0;
		}
		
		long diferenciaEn_ms = fechaMayor.getTime() - fechaMenor.getTime();
		long dias = diferenciaEn_ms / (1000 * 60 * 60 * 24);
		return (int) dias;
	}
}
