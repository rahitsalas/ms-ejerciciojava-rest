package com.ejerciciojava.api.exception;

import org.springframework.http.HttpStatus;

public class DBException extends BaseException {

	private static final long serialVersionUID = 1L;

	public DBException(Exception exception) {
		super(exception);
	}

	public static class Builder {

		private Exception exception; // This is important, so we'll pass it to the constructor.
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
			return this; // By returning the builder each time, we can create a fluent interface.
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

		public DBException build() {
			DBException dbException = new DBException(exception);dbException.code = this.code;
			dbException.messageDeveloper = this.messageDeveloper;
			dbException.origen = this.origen;
			dbException.metodo = this.metodo;
			dbException.status = this.status;
			dbException.number = this.number;

			return dbException;

		}

	}

}
