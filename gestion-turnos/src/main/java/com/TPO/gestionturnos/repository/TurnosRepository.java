package com.TPO.gestionturnos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TPO.gestionturnos.entity.Turno;


public interface TurnosRepository extends JpaRepository<Turno, Long>{
    Optional<Turno> findById(Long id);
}