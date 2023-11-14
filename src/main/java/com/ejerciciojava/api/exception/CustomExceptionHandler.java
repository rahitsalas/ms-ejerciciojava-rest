package com.ejerciciojava.api.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ejerciciojava.api.dto.DefaultResponse;
import com.ejerciciojava.api.util.Constantes;

@ControllerAdvice
public class CustomExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<DefaultResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

		DefaultResponse response = new DefaultResponse();
		Map<String, Object> params = new LinkedHashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(e -> {
			params.put("Field", e.getField());
			params.put("Value", e.getRejectedValue());
			params.put("defaultMessage", e.getDefaultMessage());
		});
		response.setMensaje(Constantes.ERROR_ARGUMENTS + params);
		return new ResponseEntity<DefaultResponse>(response, HttpStatus.BAD_REQUEST);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = HttpMessageConversionException.class)
	public ResponseEntity<DefaultResponse> handleHttpMessageConversionException(HttpMessageConversionException ex) {

		DefaultResponse response = new DefaultResponse();
		response.setMensaje(Constantes.ERROR_ARGUMENTSCONVERSION);
		return new ResponseEntity<DefaultResponse>(response, HttpStatus.BAD_REQUEST);
	}

	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	@ExceptionHandler(value = HttpMediaTypeException.class)
	public ResponseEntity<DefaultResponse> handleHttpMediaTypeException(HttpMediaTypeException ex) {

		DefaultResponse response = new DefaultResponse();
		response.setMensaje(Constantes.ERROR_MEDIATYPE);
		return new ResponseEntity<DefaultResponse>(response, HttpStatus.BAD_REQUEST);
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = BaseException.class)
	public ResponseEntity<DefaultResponse> handleBaseException(BaseException ex) {
		DefaultResponse response = new DefaultResponse();
		if (ex instanceof ServiceFunctionalException) {
			response.setMensaje(Constantes.ERROR_FUNCTIONAL + ex.getMessage());
		} else if (ex instanceof ServiceTechnicalException) {
			response.setMensaje(Constantes.ERROR_TECHNICAL + ex.getMessage());
		} else if (ex instanceof DBException) {
			response.setMensaje(Constantes.ERROR_DATABASE + ex.getMessage());
		} else {
			response.setMensaje(Constantes.ERROR_DEFAULT + ex.getMessage());
		}
		return new ResponseEntity<DefaultResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
