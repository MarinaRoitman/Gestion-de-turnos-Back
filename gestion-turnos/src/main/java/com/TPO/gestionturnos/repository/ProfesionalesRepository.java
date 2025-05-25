package com.TPO.gestionturnos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TPO.gestionturnos.entity.Especialidad;
import com.TPO.gestionturnos.entity.Profesional;

public interface ProfesionalesRepository extends JpaRepository<Profesional, Long> {
    public Optional<Profesional> findById(Long id);
    public Optional<Profesional> findByMail(String mail);
    public Optional<List<Profesional>> findByEspecialidadesContaining(Especialidad especialidad);
    public Optional<List<Profesional>> findByNombre(String nombre);
    public Optional<Profesional> findByMatricula(String matricula);
}
