package com.ejerciciojava.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejerciciojava.api.dto.RegistroRequest;
import com.ejerciciojava.api.dto.RegistroResponse;
import com.ejerciciojava.api.exception.DBException;
import com.ejerciciojava.api.exception.ServiceFunctionalException;
import com.ejerciciojava.api.exception.ServiceTechnicalException;
import com.ejerciciojava.api.service.RegistroService;

@RestController
@RequestMapping("/usuario")
public class RegistroController {
	
	
	@Autowired
	private RegistroService registroService;

	
	@PostMapping(value = "/registro", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RegistroResponse> consultarRecibos(
			 @RequestBody @Validated RegistroRequest request) throws ServiceTechnicalException, ServiceFunctionalException, DBException{
		RegistroResponse responseService = registroService.registrar(request);
		return new ResponseEntity<>(responseService, HttpStatus.OK);
	}
}