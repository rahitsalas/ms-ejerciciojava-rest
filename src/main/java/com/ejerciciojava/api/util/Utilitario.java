package com.ejerciciojava.api.util;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import com.ejerciciojava.api.exception.DBException;
import com.ejerciciojava.api.exception.ServiceFunctionalException;
import com.ejerciciojava.api.exception.ServiceTechnicalException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class Utilitario implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(Utilitario.class);
	private static final long serialVersionUID = -3301605591108950415L;

	private Utilitario() {
	}
	
	public static void dispararExcepcionErrorFuncionalService(String codigo, String mensaje, String metodoController, String origen,
			Exception e) throws ServiceFunctionalException {
		throw new ServiceFunctionalException.Builder(e).withCode(codigo).withMessage(mensaje).withOrigin(origen)
				.atMethod(metodoController).build();
	}
	
	public static void dispararExcepcionErrorBDService(String codigo, String mensaje, String metodoController, String origen,
			Exception e) throws DBException {
		throw new DBException.Builder(e).withCode(codigo).withMessage(mensaje).withOrigin(origen)
				.atMethod(metodoController).build();
	}
	
	public static void dispararExcepcionErrorTecnicoService(String codigo, String mensaje, String metodoController, String origen,
			Exception e) throws ServiceTechnicalException {
		throw new ServiceTechnicalException.Builder(e).withCode(codigo).withMessage(mensaje).withOrigin(origen)
				.atMethod(metodoController).build();
	}
	
//	public static String getJWTToken(String username) {
//		String secretKey = "mySecretKey";
//		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
//				.commaSeparatedStringToAuthorityList("ROLE_USER");
//		
//		String token = Jwts
//				.builder()
//				.setId("softtekJWT")
//				.setSubject(username)
//				.claim("authorities",
//						grantedAuthorities.stream()
//								.map(GrantedAuthority::getAuthority)
//								.collect(Collectors.toList()))
//				.setIssuedAt(new Date(System.currentTimeMillis()))
//				.setExpiration(new Date(System.currentTimeMillis() + 600000))
//				.signWith(SignatureAlgorithm.HS512,
//						secretKey.getBytes()).compact();
//
//		return "Bearer " + token;
//	}
	

	
}
