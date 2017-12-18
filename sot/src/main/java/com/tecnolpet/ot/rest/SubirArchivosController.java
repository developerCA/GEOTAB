package com.tecnolpet.ot.rest;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tecnolpet.ot.dto.UploaderDto;
import com.tecnolpet.ot.model.TareaDetalleNotaPedido;
import com.tecnolpet.ot.repository.TareaDetalleNotaPedidoRepository;



@RestController
@RequestMapping("api/upload")
@PropertySources(value = { @PropertySource("classpath:/application.properties") })
public class SubirArchivosController {

	@Autowired
	private Environment env;

	private final TareaDetalleNotaPedidoRepository tareaDetalleNotaPedidoRepository;

	@Autowired
	public SubirArchivosController(TareaDetalleNotaPedidoRepository tareaDetalleNotaPedidoRepository) {
		this.tareaDetalleNotaPedidoRepository = tareaDetalleNotaPedidoRepository;
	}

	@RequestMapping(value = "/cargarAccesos", method = RequestMethod.POST)
	public @ResponseBody UploaderDto cargarAccesosExcel(
			@RequestParam("id") Integer id,
			@RequestParam("file") MultipartFile file) {
		UploaderDto response = new UploaderDto();
		TareaDetalleNotaPedido tarea=tareaDetalleNotaPedidoRepository.findOne(id);
		Integer intfile;
		String filename;

		String path = env.getProperty("ruta.file");

		StringBuilder newPath = new StringBuilder();
		String originalFileName;
		StringBuilder newFileName = new StringBuilder();
		Date date = new Date();
		String extFile;
		String dateStr;

		dateStr = new Timestamp(date.getTime()).toString().replace(" ", "")
				.replace(":", "").replace("/", "").replace("-", "")
				.replace(".", "");

		if (!file.isEmpty()) {
			try {

				originalFileName = file.getOriginalFilename();
				extFile = originalFileName.substring(
						originalFileName.lastIndexOf(".") + 1,
						originalFileName.length());

				newFileName.append("carga").append("_").append(dateStr)
						.append(".").append(extFile);
				newPath.append(path).append("/").append(newFileName.toString());

				intfile = newPath.toString().lastIndexOf("/");
				filename = newPath.toString().substring(intfile + 1);

				byte[] bytes = file.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(newPath.toString())));
				stream.write(bytes);
				stream.close();

				tarea.setArchivoReal(filename);
				tarea.setArchivoTarea(originalFileName);
				tareaDetalleNotaPedidoRepository.save(tarea);
				
				response.setTarea(tarea);
				response.setAnswer("You successfully uploaded " + "w" + "!");

			} catch (Exception e) {
				
				response.setAnswer("You failed to upload " + "w" + " => "
						+ e.getMessage());

			}
		} else {
			response.setAnswer("You failed to upload because the file was empty.");

		}

		return response;
	}
	
	@RequestMapping(value = "/cargarArchivo", method = RequestMethod.POST)
	public @ResponseBody UploaderDto cargarArchivo(
			@RequestParam("id") Integer id,
			@RequestParam("file") MultipartFile file) {
		UploaderDto response = new UploaderDto();
		Integer intfile;
		String filename;

		String path = env.getProperty("ruta.file");

		StringBuilder newPath = new StringBuilder();
		String originalFileName;
		StringBuilder newFileName = new StringBuilder();
		Date date = new Date();
		String extFile;
		String dateStr;

		dateStr = new Timestamp(date.getTime()).toString().replace(" ", "")
				.replace(":", "").replace("/", "").replace("-", "")
				.replace(".", "");

		if (!file.isEmpty()) {
			try {

				originalFileName = file.getOriginalFilename();
				extFile = originalFileName.substring(
						originalFileName.lastIndexOf(".") + 1,
						originalFileName.length());

				newFileName.append("carga").append("_").append(dateStr)
						.append(".").append(extFile);
				newPath.append(path).append("/").append(newFileName.toString());

				intfile = newPath.toString().lastIndexOf("/");
				filename = newPath.toString().substring(intfile + 1);

				byte[] bytes = file.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(newPath.toString())));
				stream.write(bytes);
				stream.close();

				response.setName(filename);
				response.setNameR(originalFileName);
				response.setAnswer("You successfully uploaded " + "w" + "!");

			} catch (Exception e) {
				
				response.setAnswer("You failed to upload " + "w" + " => "
						+ e.getMessage());

			}
		} else {
			response.setAnswer("You failed to upload because the file was empty.");

		}

		return response;
	}


}

