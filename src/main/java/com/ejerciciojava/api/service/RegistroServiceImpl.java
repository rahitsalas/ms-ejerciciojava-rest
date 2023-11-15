package com.ejerciciojava.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ejerciciojava.api.config.ApplicationProperties;
import com.ejerciciojava.api.dto.PhoneDTO;
import com.ejerciciojava.api.dto.RegistroRequest;
import com.ejerciciojava.api.dto.RegistroResponse;
import com.ejerciciojava.api.exception.DBException;
import com.ejerciciojava.api.exception.ServiceFunctionalException;
import com.ejerciciojava.api.exception.ServiceTechnicalException;
import com.ejerciciojava.api.models.Telefono;
import com.ejerciciojava.api.models.Usuario;
import com.ejerciciojava.api.models.builder.TelefonoBuilder;
import com.ejerciciojava.api.models.builder.UsuarioBuilder;
import com.ejerciciojava.api.repository.TelefonoRepository;
import com.ejerciciojava.api.repository.UsuarioRepository;
import com.ejerciciojava.api.util.Constantes;
import com.ejerciciojava.api.util.Utilitario;

@Service("RegistroService")
public class RegistroServiceImpl implements RegistroService {

	@Autowired
	private ApplicationProperties applicationProperties;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	TelefonoRepository telefonoRepository;

	@Override
	public RegistroResponse registrar(RegistroRequest request)
			throws ServiceTechnicalException, ServiceFunctionalException, DBException {
		String metodoController = Constantes.METODO_USUARIO_REGISTRAR;
		String origen = this.getClass().getSimpleName() + "."
				+ Thread.currentThread().getStackTrace()[1].getMethodName();
		Usuario usuarioNuevo = new Usuario();
		try {
			Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(request.getEmail());
			if (usuarioOptional.isPresent()) {
				Usuario usuarioEncontrado = usuarioOptional.get();
				String errorMessage = applicationProperties.MensajeIdf1 + usuarioEncontrado.getEmail();
				Utilitario.dispararExcepcionErrorFuncionalService(origen, errorMessage, metodoController, origen,
						new Exception(errorMessage));

			}
			usuarioNuevo = guardarUsuarioConTelefonos(request);
		} catch (DBException e) {
			String errorMessage = applicationProperties.MensajeIdt1 + e.getMessage();
			Utilitario.dispararExcepcionErrorBDService(origen, errorMessage, metodoController, origen,
					new Exception(errorMessage));
		} catch (RuntimeException e) {
			String errorMessage = applicationProperties.MensajeIdt3 + e.getMessage();
			Utilitario.dispararExcepcionErrorTecnicoService(origen, errorMessage, metodoController, origen,
					new Exception(errorMessage));
		}
		return buildRegistroResponse(usuarioNuevo);
	}

	@Transactional
	private Usuario guardarUsuarioConTelefonos(RegistroRequest request) throws DBException {
		Usuario usuarioNuevo = parseUsuario(request);
		try {

			usuarioNuevo = usuarioRepository.save(usuarioNuevo);
			if (request.getPhones() != null && !request.getPhones().isEmpty()) {
				guardarTelefonos(request.getPhones(), usuarioNuevo);
			}
			Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioNuevo.getId());
			if (usuarioOptional.isPresent()) {
				usuarioNuevo = usuarioOptional.get();
			}
		} catch (Exception ex) {
			throw new DBException(ex);
		}
		return usuarioNuevo;
	}

	private Usuario parseUsuario(RegistroRequest registroRequest) {
		return new UsuarioBuilder().withName(registroRequest.getName()).withEmail(registroRequest.getEmail())
				.withPassword(registroRequest.getPassword()).withActive(true).build();
	}

	private Telefono parseTelefono(PhoneDTO phoneDTO, Usuario usuario) {
		return new TelefonoBuilder().withNumber(phoneDTO.getNumber()).withCityCode(phoneDTO.getCityCode())
				.withCountryCode(phoneDTO.getCountryCode()).withUsuario(usuario).build();
	}

	public List<Telefono> parseTelefonos(List<PhoneDTO> phoneDTOList, Usuario usuario) {
		return phoneDTOList.stream().map(phoneDTO -> parseTelefono(phoneDTO, usuario)).collect(Collectors.toList());
	}

	private void guardarTelefonos(List<PhoneDTO> phoneDTOList, Usuario usuario) {
		List<Telefono> telefonosNuevos = parseTelefonos(phoneDTOList, usuario);
		telefonoRepository.saveAll(telefonosNuevos);
	}

	private RegistroResponse buildRegistroResponse(Usuario usuario) {
		RegistroResponse registroResponse = new RegistroResponse();
		registroResponse.setId(usuario.getId());
		registroResponse.setCreated(usuario.getFechaCreacion());
		registroResponse.setModified(usuario.getFechaModificacion());
		registroResponse.setLastLogin(usuario.getLastLogin());
		registroResponse.setActive(usuario.isActive());
		return registroResponse;
	}

}
