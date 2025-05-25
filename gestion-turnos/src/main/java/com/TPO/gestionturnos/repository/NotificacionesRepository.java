package com.TPO.gestionturnos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TPO.gestionturnos.entity.Notificacion;
import com.TPO.gestionturnos.entity.Paciente;

import java.util.List;


public interface NotificacionesRepository extends JpaRepository<Notificacion, Long>{
    Optional<Notificacion> findById(Long id);
    Optional<List<Notificacion>> findByPaciente(Paciente paciente);
    Optional<List<Notificacion>> findByPacienteAndVisibleTrue(Paciente paciente);
}