package com.tecnolpet.ot.geotab.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tecnolpet.ot.geotab.dto.LocalizazionesGeotabDto;
import com.tecnolpet.ot.geotab.dto.LogCredentialsDto;
import com.tecnolpet.ot.geotab.dto.LogParamDto;
import com.tecnolpet.ot.geotab.dto.LogRecordDto;
import com.tecnolpet.ot.geotab.dto.LogSearchDto;
import com.tecnolpet.ot.geotab.dto.ProcesaDatosGeotabDto;
import com.tecnolpet.ot.geotab.dto.ResultLogRecodDto;
import com.tecnolpet.ot.model.FechaDispositivo;

@EnableScheduling
@Service
public class GeoTabServiceJob {

	@Autowired
	GeoTabService geoTabService;

	@Scheduled(fixedRate = 100000)
	public void procesarGps() {
		System.out.println(new Date());
		procesarGpsFechas();
	}

	private void procesarGpsFechas() {

		ProcesaDatosGeotabDto procesaDatosGeotabDto;
		try {
			FechaDispositivo fechaDispositivo = geoTabService
					.devolverFechaProceso();

			if (null != fechaDispositivo) {
				procesaDatosGeotabDto = new ProcesaDatosGeotabDto();
				procesaDatosGeotabDto.setFechaDispositivo(fechaDispositivo);
				procesaDatosGeotabDto
						.setListaDatos(traerLocalizaciones(fechaDispositivo));

				geoTabService.procesarLocalizaciones(procesaDatosGeotabDto);
				
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}

	}

	private List<LocalizazionesGeotabDto> traerLocalizaciones(
			FechaDispositivo fechaDispositivo) {

		RestTemplate restTemplate = new RestTemplate();
		List<LocalizazionesGeotabDto> localizazionesGeotabDto = null;
		try {
			LogRecordDto logRecord = new LogRecordDto();
			logRecord.setMethod("Get");
			LogParamDto logParamDto = new LogParamDto();
			logParamDto.setTypeName("LogRecord");
			LogSearchDto logSearchDto = new LogSearchDto();
			logSearchDto.setFromDate(fechaDispositivo.getFechaInicio());
			logSearchDto.setToDate(fechaDispositivo.getFechaFinal());
			logParamDto.setSearch(logSearchDto);
			LogCredentialsDto logCredentialsDto = new LogCredentialsDto();

			logParamDto.setCredentials(logCredentialsDto);

			logRecord.setParams(logParamDto);

			ResponseEntity<ResultLogRecodDto> response = restTemplate
					.postForEntity("https://my49.geotab.com/apiv1", logRecord,
							ResultLogRecodDto.class);

			ResultLogRecodDto dato = response.getBody();
			localizazionesGeotabDto = dato.getResult();

		} catch (Exception ex) {

		}

		return localizazionesGeotabDto;

	}
}
