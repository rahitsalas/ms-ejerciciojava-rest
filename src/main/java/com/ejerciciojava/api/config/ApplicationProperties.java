package com.ejerciciojava.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "applicationproperties")
public class ApplicationProperties {

	@Value("${spring.application.name}")
	public String applicationName;

	@Value("${codigo.mensaje.idf1}")
	public String MensajeIdf1;
	@Value("${codigo.mensaje.idf2}")
	public String MensajeIdf2;
	@Value("${codigo.mensaje.idt1}")
	public String MensajeIdt1;
	@Value("${codigo.mensaje.idt2}")
	public String MensajeIdt2;
	@Value("${codigo.mensaje.idt3}")
	public String MensajeIdt3;
}