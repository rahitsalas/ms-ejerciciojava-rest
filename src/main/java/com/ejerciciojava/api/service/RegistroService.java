package com.ejerciciojava.api.service;

import com.ejerciciojava.api.dto.RegistroRequest;
import com.ejerciciojava.api.dto.RegistroResponse;
import com.ejerciciojava.api.exception.DBException;
import com.ejerciciojava.api.exception.ServiceFunctionalException;
import com.ejerciciojava.api.exception.ServiceTechnicalException;

public interface RegistroService {
	RegistroResponse registrar(RegistroRequest request)
			throws ServiceTechnicalException, ServiceFunctionalException, DBException;

}
