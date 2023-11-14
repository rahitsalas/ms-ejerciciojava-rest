package com.ejerciciojava.api.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "telefono")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Telefono {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    private String number;

    private String citycode;
    
    private String countrycode;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;


}