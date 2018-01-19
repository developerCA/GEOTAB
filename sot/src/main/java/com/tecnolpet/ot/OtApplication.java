package com.tecnolpet.ot;

import java.text.SimpleDateFormat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@ComponentScan
@Controller
@SpringBootApplication
@PropertySources({
		@PropertySource(value = { "classpath:default.properties" }),
		@PropertySource(value = "file:${external.config}", ignoreResourceNotFound = true) })
public class OtApplication extends SpringBootServletInitializer{
	 

	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		return application.sources(OtApplication.class);
	}

	private static Class<OtApplication> applicationClass = OtApplication.class;
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(applicationClass, args);
	}

	
	@Bean
	public Jackson2ObjectMapperBuilder jacksonBuilder() {
	
		Jackson2ObjectMapperBuilder b = new Jackson2ObjectMapperBuilder();
		b.indentOutput(true).dateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		return b;
	}
}
