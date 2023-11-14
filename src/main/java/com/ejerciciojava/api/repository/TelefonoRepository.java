package com.ejerciciojava.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ejerciciojava.api.models.Telefono;

@Repository
public interface TelefonoRepository extends JpaRepository<Telefono, Long> {
}
