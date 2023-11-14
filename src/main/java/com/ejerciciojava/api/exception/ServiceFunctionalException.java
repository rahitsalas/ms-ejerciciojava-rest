package com.ejerciciojava.api.exception;

import org.springframework.http.HttpStatus;

public class ServiceFunctionalException extends BaseException {

	private static final long serialVersionUID = 1L;

	private ServiceFunctionalException(Exception exception) {
		super(exception);
	}

	public ServiceFunctionalException(String code, String message, HttpStatus status, String number) {
		super(code, message, status, number);
	}

	public static class Builder {

		private Exception exception;
		private String code;
		private String messageDeveloper;
		private String origen;
		private String metodo;
		private HttpStatus status;
		private String number;

		public Builder(Exception exception) {
			this.exception = exception;
		}

		public Builder withCode(String code) {
			this.code = code;
			return this;
		}

		public Builder withMessage(String messageDeveloper) {
			this.messageDeveloper = messageDeveloper;
			return this;
		}

		public Builder withOrigin(String origen) {
			this.origen = origen;
			return this;
		}

		public Builder atMethod(String metodo) {
			this.metodo = metodo;
			return this;
		}

		public Builder withHttpStatus(HttpStatus status) {
			this.status = status;
			return this;
		}

		public Builder withNumber(String number) {
			this.number = number;
			return this;
		}

		public ServiceFunctionalException build() {

			ServiceFunctionalException serviceException = new ServiceFunctionalException(exception);
			serviceException.code = this.code;
			serviceException.messageDeveloper = this.messageDeveloper;
			serviceException.origen = this.origen;
			serviceException.metodo = this.metodo;
			serviceException.status = this.status;
			serviceException.number = this.number;

			return serviceException;

		}

	}

}
