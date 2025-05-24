package com.TPO.gestionturnos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TPO.gestionturnos.entity.ObraSocial;
import com.TPO.gestionturnos.entity.Plan;
import java.util.List;


public interface PlanesRepository extends JpaRepository<Plan, Long>{
    Optional<Plan> findById(Long id);
    Optional<Plan> findByNombre(String nombre);
    List<Plan> findByObraSocial(Optional<ObraSocial> obraSocial);
}