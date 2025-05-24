package com.TPO.gestionturnos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TPO.gestionturnos.entity.ObraSocial;

public interface ObrasSocialesRepository extends JpaRepository<ObraSocial, Long>{
    Optional<ObraSocial> findById(Long id);
    Optional<ObraSocial> findByNombre(String obraSocialNombre);
}