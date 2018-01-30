package demo;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.junit.Ignore;
import org.junit.Test;

import com.tecnolpet.ot.model.Localizacion;
import com.tecnolpet.ot.model.Punto;
import com.tecnolpet.ot.model.TipoHorario;
import com.tecnolpet.ot.utils.FechasUtils;
import com.tecnolpet.ot.utils.PoligonoUtils;

public class TestGeotab {

	@Test
	@Ignore
	public void getPoints() {

		List<Punto> listaPuntos = new ArrayList<>();

		Punto punto1 = new Punto();
		punto1.setPosx(-78.49515533447266);
		punto1.setPosy(-0.11318914592266083);

		listaPuntos.add(punto1);

		Punto punto2 = new Punto();
		punto2.setPosx(-78.49530792236328);
		punto2.setPosy(-0.11324815452098846);

		listaPuntos.add(punto2);

		Punto punto3 = new Punto();
		punto3.setPosx(-78.49530792236328);
		punto3.setPosy(-0.11324815452098846);

		listaPuntos.add(punto3);

		Localizacion localizazion = new Localizacion();
		localizazion.setLatitude(-0.11725627040521738);
		localizazion.setLongitud(-78.49414576208696);

		PoligonoUtils.buscarZonaPoligono(listaPuntos, localizazion);

	}

	@Test
	@Ignore
	public void Fechas() {
		String fechaUtc = "2018-01-15T23:26:09.655Z";

		SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'");
		isoFormat.setTimeZone(TimeZone.getDefault());
		try {
			Date date = isoFormat.parse(fechaUtc);

			long ms = date.getTime();
			Time t = new Time(ms);

			System.err.println(t);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	@Ignore
	public void devolverTipoHora() throws ParseException {

		Time hora = FechasUtils.convertirStringTimeZoneToTime("2018-01-15T23:28:00.655Z");
		List<TipoHorario> tipoHorarios = new ArrayList<>();

		TipoHorario t1 = new TipoHorario();

		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		String dateInString = "23:27";
		String date2InString = "23:29";

		Date date = formatter.parse(dateInString);
		Date date2 = formatter.parse(date2InString);
		long ms = date.getTime();
		long ms2 = date2.getTime();

		t1.setHoraInicio(new Time(ms));
		t1.setHoraFin(new Time(ms2));
		tipoHorarios.add(t1);

		TipoHorario tipoHorario = null;

		for (TipoHorario th : tipoHorarios) {

			System.err.println(hora);
			System.out.println(th.getHoraInicio());
			System.out.println(th.getHoraFin());
			System.err.println("Despues:");
			if (hora.getTime() >= th.getHoraInicio().getTime()) {
				System.err.println(true);
			}
			System.err.println("Antes:");
			if (hora.getTime() <= th.getHoraFin().getTime()) {
				System.err.println(true);
			}

			if (th.getHoraInicio().before(hora) && th.getHoraFin().after(hora)) {
				tipoHorario = th;
				break;
			}
		}

		if (null != tipoHorario) {
			System.out.println(tipoHorario.getNombreTipoHora());
		}

	}

	@Test
	public void horas() throws InterruptedException {

		Date dateIni = new Date();
		Time t1=new Time(dateIni.getTime());
		Calendar cal = Calendar.getInstance();
		cal.setTime(t1);

		System.out.println(cal);
		System.out.println(cal.getTime());
		Thread.sleep(3000);

		Date dateFin = new Date();
		Time t2=new Time(dateFin.getTime());
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(t2);
		System.out.println(cal2);
		System.out.println(cal2.getTime());

		long milInicio = cal.getTimeInMillis();
		long milfin = cal2.getTimeInMillis();

		long diff = milfin - milInicio;

		long diffSegundos = Math.abs(diff / 1000);
		long diffMinutos = Math.abs(diff / (60 * 1000));
		long restominutos = diffMinutos % 60;
		long diffHoras = (diff / (60 * 60 * 1000));

		Calendar calendarDiff=Calendar.getInstance();
		calendarDiff.set(0, 0, 0, (int)diffHoras, (int)restominutos, (int)diffSegundos);
		System.out.println(diffSegundos);
		System.out.println(restominutos);
		System.out.println(diffHoras);

		System.out.println(calendarDiff.getTime());
		Time timeDiff=new Time(calendarDiff.getTime().getTime());
		System.out.println(timeDiff.toString());

	}

}
