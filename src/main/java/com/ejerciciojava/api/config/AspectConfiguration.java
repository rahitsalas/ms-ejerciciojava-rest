package com.ejerciciojava.api.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.ejerciciojava.api.config.aop.LoggingAspect;

@Configuration
@EnableAspectJAutoProxy
public class AspectConfiguration {

    @Bean
    public LoggingAspect loggingAspect(Environment environment, ObjectMapper mapper) {
        return new LoggingAspect(environment,mapper);
    }
}
