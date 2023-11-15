package com.ejerciciojava.api.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.SpringConstraintValidatorFactory;
import org.springframework.web.context.WebApplicationContext;

import com.ejerciciojava.api.config.ApplicationProperties;
import com.ejerciciojava.api.dto.RegistroRequest;
import com.ejerciciojava.api.dto.RegistroResponse;
import com.ejerciciojava.api.exception.DBException;
import com.ejerciciojava.api.exception.ServiceFunctionalException;
import com.ejerciciojava.api.exception.ServiceTechnicalException;
import com.ejerciciojava.api.service.RegistroService;
import com.ejerciciojava.api.util.ConstantesUtil;
import com.ejerciciojava.api.util.Util;
import com.google.gson.Gson;

@SpringBootTest
public class RegistroControllerTest {

	@Mock
	private RegistroService registroService;

	@InjectMocks
	private RegistroController registroController;

	@SpyBean
	private ApplicationProperties properties;
	@Autowired
	private WebApplicationContext webApplicationContext;
	private LocalValidatorFactoryBean validator;

	@BeforeEach
	public void setUp() {
		SpringConstraintValidatorFactory springConstraintValidatorFactory = new SpringConstraintValidatorFactory(
				webApplicationContext.getAutowireCapableBeanFactory());
		validator = new LocalValidatorFactoryBean();
		validator.setConstraintValidatorFactory(springConstraintValidatorFactory);
		validator.setApplicationContext(webApplicationContext);
		validator.afterPropertiesSet();
	}

	@Test
	@DisplayName("C01M01T01 - Prueba de flujo regular")
	public void C01M01T01() throws ServiceTechnicalException, ServiceFunctionalException, DBException {

		// GIVEN
		RegistroRequest request = Util.serializarArchivoAObjeto(ConstantesUtil.C01M01T01_REQUEST,
				RegistroRequest.class);

		RegistroResponse responseEsperado = Util.serializarArchivoAObjeto(ConstantesUtil.C01M01T01_RESPONSE,
				RegistroResponse.class);
		// WHEN
		Gson gson = new Gson();
		String jsonString = gson.toJson(request);
		String jsonString2 = gson.toJson(responseEsperado);
		System.out.println(jsonString);
		System.out.println(jsonString2);
		when(registroService.registrar(any(RegistroRequest.class))).thenReturn(responseEsperado);
		// THEN
		ResponseEntity<RegistroResponse> responseObtenido = registroController.registrarUsuario(request);
		ResponseEntity<RegistroResponse> responseEsperadoEntity = new ResponseEntity<>(responseEsperado, HttpStatus.OK);
		jsonString = gson.toJson(responseObtenido);
		jsonString2 = gson.toJson(responseEsperadoEntity);
		System.out.println(jsonString);
		System.out.println(jsonString2);

		assertThat(responseEsperadoEntity).usingRecursiveComparison().isEqualTo(responseObtenido);
		assertThat(responseEsperadoEntity.getBody()).isEqualTo(responseObtenido.getBody());
	}

	@Test
	@DisplayName("C01M01T02 - Prueba de flujo para Email invalido en Request")
	public void C01M01T02() throws ServiceTechnicalException, ServiceFunctionalException, DBException {
		RegistroRequest request = Util.serializarArchivoAObjeto(ConstantesUtil.C01M01T02_REQUEST,
				RegistroRequest.class);
		Set<ConstraintViolation<RegistroRequest>> constraintViolations = validator.validate(request);
		for (ConstraintViolation<RegistroRequest> violation : constraintViolations) {
			String mensaje = violation.getMessage();
			String propiedad = violation.getPropertyPath().toString();
			System.out.println("Violación: " + propiedad + " - " + mensaje);
		}
		assertEquals(1, constraintViolations.size());
	}

	@Test
	@DisplayName("C01M01T03 - Prueba de flujo para Name invalido en Request")
	public void C01M01T03() throws ServiceTechnicalException, ServiceFunctionalException, DBException {
		RegistroRequest request = Util.serializarArchivoAObjeto(ConstantesUtil.C01M01T03_REQUEST,
				RegistroRequest.class);
		Set<ConstraintViolation<RegistroRequest>> constraintViolations = validator.validate(request);
		for (ConstraintViolation<RegistroRequest> violation : constraintViolations) {
			String mensaje = violation.getMessage();
			String propiedad = violation.getPropertyPath().toString();
			System.out.println("Violación: " + propiedad + " - " + mensaje);
		}
		assertEquals(1, constraintViolations.size());
	}

	@Test
	@DisplayName("C01M01T04 - Prueba de flujo para Password invalido en Request")
	public void C01M01T04() throws ServiceTechnicalException, ServiceFunctionalException, DBException {
		RegistroRequest request = Util.serializarArchivoAObjeto(ConstantesUtil.C01M01T04_REQUEST,
				RegistroRequest.class);
		Set<ConstraintViolation<RegistroRequest>> constraintViolations = validator.validate(request);
		for (ConstraintViolation<RegistroRequest> violation : constraintViolations) {
			String mensaje = violation.getMessage();
			String propiedad = violation.getPropertyPath().toString();
			System.out.println("Violación: " + propiedad + " - " + mensaje);
		}
		assertEquals(1, constraintViolations.size());
	}

	@Test
	@DisplayName("C01M01T05 - Prueba de flujo para Email en blanco en Request")
	public void C01M01T05() throws ServiceTechnicalException, ServiceFunctionalException, DBException {
		RegistroRequest request = Util.serializarArchivoAObjeto(ConstantesUtil.C01M01T05_REQUEST,
				RegistroRequest.class);
		Set<ConstraintViolation<RegistroRequest>> constraintViolations = validator.validate(request);
		for (ConstraintViolation<RegistroRequest> violation : constraintViolations) {
			String mensaje = violation.getMessage();
			String propiedad = violation.getPropertyPath().toString();
			System.out.println("Violación: " + propiedad + " - " + mensaje);
		}
		assertEquals(2, constraintViolations.size());
	}

	@Test
	@DisplayName("C01M01T06 - Prueba de flujo para Name en nulo en Request")
	public void C01M01T06() throws ServiceTechnicalException, ServiceFunctionalException, DBException {
		RegistroRequest request = Util.serializarArchivoAObjeto(ConstantesUtil.C01M01T06_REQUEST,
				RegistroRequest.class);
		Set<ConstraintViolation<RegistroRequest>> constraintViolations = validator.validate(request);
		for (ConstraintViolation<RegistroRequest> violation : constraintViolations) {
			String mensaje = violation.getMessage();
			String propiedad = violation.getPropertyPath().toString();
			System.out.println("Violación: " + propiedad + " - " + mensaje);
		}
		assertEquals(1, constraintViolations.size());
	}

	@Test
	@DisplayName("C01M01T07 - Prueba de flujo para Pasword en nulo en Request")
	public void C01M01T07() throws ServiceTechnicalException, ServiceFunctionalException, DBException {
		RegistroRequest request = Util.serializarArchivoAObjeto(ConstantesUtil.C01M01T07_REQUEST,
				RegistroRequest.class);
		Set<ConstraintViolation<RegistroRequest>> constraintViolations = validator.validate(request);
		for (ConstraintViolation<RegistroRequest> violation : constraintViolations) {
			String mensaje = violation.getMessage();
			String propiedad = violation.getPropertyPath().toString();
			System.out.println("Violación: " + propiedad + " - " + mensaje);
		}
		assertEquals(2, constraintViolations.size());
	}
}
