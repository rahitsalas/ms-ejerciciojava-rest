package com.ejerciciojava.api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import com.ejerciciojava.api.config.ApplicationProperties;
import com.ejerciciojava.api.dto.RegistroRequest;
import com.ejerciciojava.api.dto.RegistroResponse;
import com.ejerciciojava.api.exception.DBException;
import com.ejerciciojava.api.exception.ServiceFunctionalException;
import com.ejerciciojava.api.exception.ServiceTechnicalException;
import com.ejerciciojava.api.models.Telefono;
import com.ejerciciojava.api.models.Usuario;
import com.ejerciciojava.api.repository.TelefonoRepository;
import com.ejerciciojava.api.repository.UsuarioRepository;
import com.ejerciciojava.api.util.ConstantesUtil;
import com.ejerciciojava.api.util.Util;

@SpringBootTest
public class RegistroServiceImplTest {

	@SpyBean
	private ApplicationProperties properties;

	@InjectMocks
	private RegistroServiceImpl registroService;

	@Mock
	private UsuarioRepository usuarioRepository;

	@Mock
	private TelefonoRepository telefonoRepository;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@DisplayName("S01M01T01 - Prueba de flujo regular")
	public void S01M01T01() throws ServiceTechnicalException, ServiceFunctionalException, DBException {
		// GIVEN
		RegistroRequest request = Util.serializarArchivoAObjeto(ConstantesUtil.S01M01T01_REQUEST,
				RegistroRequest.class);

		RegistroResponse responseEsperado = Util.serializarArchivoAObjeto(ConstantesUtil.S01M01T01_RESPONSE,
				RegistroResponse.class);
		// WHEN
		Usuario usuario = Util.serializarArchivoAObjeto(ConstantesUtil.S01M01T01_GIVEN_REPOSITORY_01, Usuario.class);
		List<Telefono> telefono = Util.serializarArchivoAObjeto(ConstantesUtil.S01M01T01_GIVEN_REPOSITORY_02,
				Telefono.class);

		when(usuarioRepository.findByEmail(any(String.class))).thenReturn(Optional.empty());
		when(usuarioRepository.findById(any(UUID.class))).thenReturn(Optional.of(usuario));
		when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
		when(telefonoRepository.saveAll(any(List.class))).thenReturn(telefono);

		// THEN
		RegistroResponse responseObtenido = registroService.registrar(request);

		assertThat(responseObtenido).usingRecursiveComparison().isEqualTo(responseEsperado);
	}
	
	@Disabled
	@Test
	@DisplayName("S01M01T02 - Prueba de flujo email repetido")
	public void S01M01T02() throws ServiceTechnicalException, ServiceFunctionalException, DBException {
		// GIVEN
		RegistroRequest request = Util.serializarArchivoAObjeto(ConstantesUtil.S01M01T02_REQUEST,
				RegistroRequest.class);
		Usuario usuario = Util.serializarArchivoAObjeto(ConstantesUtil.S01M01T02_GIVEN_REPOSITORY_01, Usuario.class);
		// WHEN
		when(usuarioRepository.findByEmail(any(String.class))).thenReturn(Optional.of(usuario));
		// THEN
		ServiceFunctionalException exception = assertThrows(ServiceFunctionalException.class, () -> {
			registroService.registrar(request);
		});

		String expectedErrorMessage = "java.lang.Exception: " + properties.MensajeIdf1 + usuario.getEmail();
		assertThat(exception.getMessage()).isEqualTo(expectedErrorMessage);
	}

	@Test
	@DisplayName("S01M01T03 - Prueba de flujo null pointer")
	public void S01M01T03() throws ServiceTechnicalException, ServiceFunctionalException, DBException {
		// GIVEN
		RegistroRequest request = Util.serializarArchivoAObjeto(ConstantesUtil.S01M01T03_REQUEST,
				RegistroRequest.class);

		// THEN
		ServiceTechnicalException exception = assertThrows(ServiceTechnicalException.class, () -> {
			registroService.registrar(request);
		});
		String expectedErrorMessage = "java.lang.Exception: " + properties.MensajeIdt3 + "null";
		assertThat(exception.getMessage()).isEqualTo(expectedErrorMessage);
	}

}
