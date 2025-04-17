package com.TPO.gestionturnos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TPO.gestionturnos.entity.Paciente;

public interface PacientesRepository extends JpaRepository<Paciente, Long> {
    Optional<Paciente> findByMail(String mail);
}