package com.TPO.gestionturnos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TPO.gestionturnos.entity.Estado;
import com.TPO.gestionturnos.entity.Paciente;
import com.TPO.gestionturnos.entity.Profesional;
import com.TPO.gestionturnos.entity.Turno;
import java.util.List;
import java.time.LocalDate;



public interface TurnosRepository extends JpaRepository<Turno, Long>{
    Optional<Turno> findById(Long id);
    Optional<List<Turno>> findByFecha(LocalDate fecha);
    Optional<List<Turno>> findByEstado(Estado estado);
    Optional<List<Turno>> findByPaciente(Paciente paciente);
    Optional<List<Turno>> findByPacienteAndEstado(Paciente paciente, Estado estado);
    Optional<List<Turno>> findByProfesional(Profesional profesional);
    Optional<List<Turno>> findByProfesionalAndEstado(Profesional profesional, Estado estado);
    Optional<List<Turno>> findByFechaGreaterThanEqual(LocalDate fecha);
}