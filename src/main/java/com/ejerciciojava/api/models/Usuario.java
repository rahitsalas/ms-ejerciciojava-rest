package com.ejerciciojava.api.models;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuario")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Usuario {

	@Id
	@GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
	
    private String name;

    private String email;
    
    private String password;
    
    @CreationTimestamp
    @Column(name = "created_time")
    private Date fechaCreacion;


    @UpdateTimestamp
    @Column(name = "modified_time")
    private Date fechaModificacion;
    
    @UpdateTimestamp
    @Column(name = "last_login")
    private Date lastLogin;
    
    private String token;
    
    @Column(name = "is_Active")
    private boolean isActive;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Telefono> telefonos;

}