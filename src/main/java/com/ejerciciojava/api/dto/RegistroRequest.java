package com.ejerciciojava.api.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;

import lombok.Data;
@Data
public class RegistroRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "name no puede ser blanco o vacio")
	private String name;
	
	@NotBlank(message = "email no puede ser blanco o vacio")
	private String email;
	
	@NotBlank(message = "password no puede ser blanco o vacio")
	private String password;
	private List<PhoneDTO> phones;

	@AssertTrue(message = "Email no cumple con el formato valido")
	private boolean isValidEmail() {
		return email != null && email.matches("^[\\w.-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,}$");
	}

	@AssertTrue(message = "El password debe contener por lo menos 1 letra mayuscula, 1 minuscula y 2 numeros")
	private boolean isValidPassword() {
		return password != null && password.matches("(?=.*[A-Z])(?=.*[a-z])(?=.*\\d.*\\d).+");
	}
}
