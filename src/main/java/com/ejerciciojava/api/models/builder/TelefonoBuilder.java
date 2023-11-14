package com.ejerciciojava.api.models.builder;

import com.ejerciciojava.api.models.Telefono;
import com.ejerciciojava.api.models.Usuario;

public class TelefonoBuilder {
    private final Telefono telefono;

    public TelefonoBuilder() {
    	telefono = new Telefono();
    }

    public TelefonoBuilder withNumber(String number) {
    	telefono.setNumber(number);
        return this;
    }

    public TelefonoBuilder withCityCode(String cityCode) {
    	telefono.setCitycode(cityCode);
        return this;
    }

    public TelefonoBuilder withCountryCode(String countryCode) {
    	telefono.setCountrycode(countryCode);
        return this;
    }
    
    public TelefonoBuilder withUsuario(Usuario usuario) {
    	telefono.setUsuario(usuario);
        return this;
    }
 
    public Telefono build() {
        return telefono;
    }
}
