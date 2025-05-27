package com.TPO.gestionturnos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TPO.gestionturnos.entity.Imagen;
import com.TPO.gestionturnos.entity.Turno;

import java.util.List;


public interface ImagenesRepository extends JpaRepository<Imagen, Long>{
    Optional<Imagen> findById(Long id);
    Optional<List<Imagen>> findByTurno(Turno turno);
}