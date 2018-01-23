/**
 * 
 */
package com.tecnolpet.ot.utils;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author administrador
 *
 */
public class FechasUtils {
	public static Timestamp alteraFechas(Date FechaAModificar, Integer cantidadMeses) {
		Calendar fecha = Calendar.getInstance();

		fecha.setTimeInMillis(FechaAModificar.getTime());
		fecha.add(Calendar.MONTH, cantidadMeses);
		Timestamp timestamp = new Timestamp(fecha.getTimeInMillis());
		return timestamp;
	}

	public static Timestamp alteraDias(Date FechaAModificar, Integer cantidadDias) {
		Calendar fecha = Calendar.getInstance();

		fecha.setTimeInMillis(FechaAModificar.getTime());
		fecha.add(Calendar.DAY_OF_MONTH, cantidadDias);
		Timestamp timestamp = new Timestamp(fecha.getTimeInMillis());
		return timestamp;
	}

	public static boolean validaFechas(final Date fechaInicio, final Date fechaFin) {
		return fechaInicio.compareTo(fechaFin) < 0 ? Boolean.TRUE : Boolean.FALSE;
	}

	public static Date alteraFechasDate(Date FechaAModificar, Integer cantidadMeses) {
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

		if (null == fechaMayor || null == fechaMenor) {
			return 0;
		}

		long diferenciaEn_ms = fechaMayor.getTime() - fechaMenor.getTime();
		long dias = diferenciaEn_ms / (1000 * 60 * 60 * 24);
		return (int) dias;
	}
	
	// Suma los días recibidos a la fecha  
	 public static Date sumarRestarDiasFecha(Date fecha, int dias){
	
	      Calendar calendar = Calendar.getInstance();
	      calendar.setTime(fecha); // Configuramos la fecha que se recibe
	      calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0
	
	      return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
	
	 }
	// Suma o resta las horas recibidos a la fecha  
	 public static Date sumarRestarHorasFecha(Date fecha, int horas){
	
	      Calendar calendar = Calendar.getInstance();
	      calendar.setTime(fecha); // Configuramos la fecha que se recibe
	      calendar.add(Calendar.HOUR, horas);  // numero de horas a añadir, o restar en caso de horas<0
	
	      return calendar.getTime(); // Devuelve el objeto Date con las nuevas horas añadidas
	
	 }

	public static Date convertirStringTimeZoneToDate(String fechaUTC) {
		Date date = null;
		SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'");
		isoFormat.setTimeZone(TimeZone.getDefault());
		try {
			date = isoFormat.parse(fechaUTC);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}

	public static Time convertirStringTimeZoneToTime(String fechaUTC) {
		Date date = null;
		Date fecha = null;
		Time time = null;
		SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'");
		isoFormat.setTimeZone(TimeZone.getDefault());
		try {
			date = isoFormat.parse(fechaUTC);
			DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss");
			String hora=hourdateFormat.format(date);
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			fecha = sdf.parse(hora);
			long ms = fecha.getTime();
			time = new Time(ms);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return time;
	}
}
