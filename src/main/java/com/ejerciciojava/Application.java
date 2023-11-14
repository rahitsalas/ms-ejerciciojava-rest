package com.ejerciciojava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableConfigurationProperties()
public class Application {

	private final Environment env;

	Application(Environment env) {
		this.env = env;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
