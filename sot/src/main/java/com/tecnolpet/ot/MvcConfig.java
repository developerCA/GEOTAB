package com.tecnolpet.ot;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

	@Value("${filesDocumentos}")
	private String staticPath;

	@Value("${version}")
	private String version;

	@Override
	public void configureMessageConverters(
			List<HttpMessageConverter<?>> converters) {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
		builder.indentOutput(true);
		converters
				.add(new MappingJackson2HttpMessageConverter(builder.build()));
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/login").setViewName("login");
		registry.addStatusController("/error500",
				HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String myExternalFilePath = "file:///" + staticPath;
		// String myExternalImgPath = "file:///"+imaganes;

		String files;
		// String imagen;
		files = "/files/**";

		// if ("".equals(version)){
		//
		// imagen="/img/**";
		//
		// }
		// else{
		//
		// imagen="/"+version+"/img/**";
		// }

		registry.addResourceHandler(files).addResourceLocations(
				myExternalFilePath);
		// registry.addResourceHandler(imagen).addResourceLocations(myExternalImgPath);

		super.addResourceHandlers(registry);
	}

}