package com.TPO.gestionturnos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TPO.gestionturnos.entity.Especialidad;

public interface EspecialidadesRepository extends JpaRepository<Especialidad, Long>{
    Optional<Especialidad> findById(Long id);
}