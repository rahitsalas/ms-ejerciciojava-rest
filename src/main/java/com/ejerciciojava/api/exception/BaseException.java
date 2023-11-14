package com.ejerciciojava.api.exception;

import org.springframework.http.HttpStatus;

public class BaseException extends Exception {

	private static final long serialVersionUID = -1422898168604012952L;

	protected String code;
	protected String messageDeveloper;
	protected String origen;
	protected String metodo;
	protected HttpStatus status;
	protected String number;

	protected BaseException(Exception exception) {
		super(exception);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessageDeveloper() {
		return messageDeveloper;
	}

	public void setMessageDeveloper(String messageDeveloper) {
		this.messageDeveloper = messageDeveloper;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getMetodo() {
		return metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	 public BaseException(String code, String message, HttpStatus status, String number) {
			super(message);
			this.messageDeveloper = message;
			this.code = code;
			this.status = status;
			this.number = number;
		}
}
