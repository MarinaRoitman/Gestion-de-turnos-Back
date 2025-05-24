package com.TPO.gestionturnos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TPO.gestionturnos.entity.Afiliacion;
import com.TPO.gestionturnos.entity.ObraSocial;
import com.TPO.gestionturnos.entity.Paciente;

import java.util.List;


public interface AfiliacionesRepository extends JpaRepository<Afiliacion, Long>{
    Optional<Afiliacion> findById(Long id);
    Optional<List<Afiliacion>> findByObraSocial(Optional<ObraSocial> obraSocial);
    Optional<List<Afiliacion>> findByPaciente(Optional<Paciente> paciente);
    Optional<Afiliacion> findByNroAfiliadoAndObraSocial(String nroAfiliado, ObraSocial obraSocial);
}