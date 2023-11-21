package com.ejerciciojava.api.config.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.util.StopWatch;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import com.ejerciciojava.api.config.logging.AuditLogger;
import com.ejerciciojava.api.util.Utilitario;

@Aspect
public class LoggingAspect {

	private static final Logger LOG = LoggerFactory.getLogger(LoggingAspect.class);

	private final Environment env;
	private ObjectMapper mapper;

	public LoggingAspect(Environment env, ObjectMapper mapper) {
		this.env = env;
		this.mapper = mapper;
	}

	// CAPA CONTROLLER ====================================================
	@Pointcut("within(com.ejerciciojava.api.controller..*)")
	public void loggingPointcutController() {
		// CONSTRUCTOR POR DEFECTO
	}

	// LOGS ANTES Y DESPUÉS DE LA EJECUCIÓN
	@Around("loggingPointcutController()")
	public Object logAroundController(ProceedingJoinPoint joinPoint) throws Throwable {

		StopWatch sw = new StopWatch();
		AuditLogger.log(LOG, "START CALL to {}.{}() - [PARAMETERS:] {}",
				joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(),
				Utilitario.printJsonFormat(joinPoint.getArgs()));
		try {

			sw.start();
			Object result = joinPoint.proceed();
			sw.stop();
			AuditLogger.log(LOG, "END CALL to {}.{}() - TIME: {} Millis - [PARAMETERS:] {} ",
					joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(),
					sw.getTotalTimeMillis(), Utilitario.printJsonFormat(result));
			return result;

		} catch (IllegalArgumentException e) {
			LOG.info("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
					joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
			throw e;
		}
	}

	// LOGS AL DISPARAR EXCEPCION
	@AfterThrowing(pointcut = "loggingPointcutController()", throwing = "e")
	public void logAfterThrowingController(JoinPoint joinPoint, Throwable e) {
		Gson gson = new Gson();

		LOG.error("Exception in {}.{}() with cause = {} and exception = {}",
				joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(),
				Utilitario.getCause(e), gson.toJson(e.getStackTrace()));
	}

	// CAPA SERVICE ====================================================
	@Pointcut("within(com.ejerciciojava.api.service..*)")
	public void loggingPointcut() {
	}

	// LOGS ANTES Y DESPUÉS DE LA EJECUCIÓN
	@Around("loggingPointcut()")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

		StopWatch sw = new StopWatch();
		AuditLogger.log(LOG, "START CALL to {}.{}() - [PARAMETERS:] {}",
				joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(),
				Utilitario.printJsonFormat(joinPoint.getArgs()));
		try {

			sw.start();
			Object result = joinPoint.proceed();
			sw.stop();
			AuditLogger.log(LOG, "END CALL to {}.{}() - TIME: {} Millis - [PARAMETERS:] {} ",
					joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(),
					sw.getTotalTimeMillis(), Utilitario.printJsonFormat(result));
			return result;

		} catch (IllegalArgumentException e) {
			LOG.info("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
					joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
			throw e;
		}
	}

}
