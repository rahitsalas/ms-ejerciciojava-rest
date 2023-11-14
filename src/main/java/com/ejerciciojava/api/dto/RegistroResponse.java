package com.ejerciciojava.api.dto;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class RegistroResponse {
	UUID id;
	Date created;
	Date modified;
	Date lastLogin;
	String token;
	boolean isActive;
}
