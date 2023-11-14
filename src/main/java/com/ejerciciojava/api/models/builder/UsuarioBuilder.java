package com.ejerciciojava.api.models.builder;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ejerciciojava.api.models.Telefono;
import com.ejerciciojava.api.models.Usuario;

public class UsuarioBuilder {
    private final Usuario usuario;

    public UsuarioBuilder() {
        usuario = new Usuario();
    }

    public UsuarioBuilder withName(String name) {
        usuario.setName(name);
        return this;
    }

    public UsuarioBuilder withEmail(String email) {
        usuario.setEmail(email);
        return this;
    }

    public UsuarioBuilder withPassword(String password) {
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        usuario.setPassword(encoder.encode(password));
        return this;
    }
    
    public UsuarioBuilder withTelefonos(List<Telefono> telefonos) {
        usuario.setTelefonos(telefonos);
        return this;
    }
    
    public UsuarioBuilder withToken(String token) {
        usuario.setToken(
//        		Utilitario.getJWTToken(
        				token
//        				)
        		);
        return this;
    }
    
 
    public Usuario build() {
        return usuario;
    }
}
