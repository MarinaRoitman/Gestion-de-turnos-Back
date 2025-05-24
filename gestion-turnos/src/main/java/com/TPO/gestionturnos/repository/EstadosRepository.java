package com.TPO.gestionturnos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TPO.gestionturnos.entity.Estado;

public interface EstadosRepository extends JpaRepository<Estado, Long>{
    Optional<Estado> findById(Long id);
    Optional<Estado> findByNombre(String nombreEstado);
}