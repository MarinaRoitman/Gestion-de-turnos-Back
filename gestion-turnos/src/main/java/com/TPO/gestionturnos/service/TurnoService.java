package com.TPO.gestionturnos.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import com.TPO.gestionturnos.entity.Estado;
import com.TPO.gestionturnos.entity.Imagen;
import com.TPO.gestionturnos.entity.Paciente;
import com.TPO.gestionturnos.entity.Profesional;
import com.TPO.gestionturnos.entity.Turno;
import com.TPO.gestionturnos.exceptions.EspecialidadInexistenteException;
import com.TPO.gestionturnos.exceptions.EstadoInexistenteException;
import com.TPO.gestionturnos.exceptions.ImagenInexistenteException;
import com.TPO.gestionturnos.exceptions.PacienteInexistenteException;
import com.TPO.gestionturnos.exceptions.ProfesionalInexistenteException;
import com.TPO.gestionturnos.exceptions.TurnoInexistenteException;
import com.TPO.gestionturnos.exceptions.TurnoYaReservadoException;

public interface TurnoService {
    public List<Turno> getTurnos();

    public Optional<Turno> getTurnoById(Long turnolId) throws TurnoInexistenteException;

    public Optional<List<Turno>> getTurnosPorFecha(LocalDate fechaTurno);

    public Optional<List<Turno>> getTurnosMayorFecha(LocalDate fechaTurno);

    public Optional<List<Turno>> getTurnosByEstado(Long estadoId) throws EstadoInexistenteException;

    public Optional<List<Turno>> getTurnosByPaciente(Long pacienteId) throws PacienteInexistenteException;

    public Optional<List<Turno>> getTurnosByPacienteYEstado(Long pacienteId, Long estadoId) throws PacienteInexistenteException, EstadoInexistenteException;

    public Optional<List<Turno>> getTurnosByProfesional(Long profesionalid) throws ProfesionalInexistenteException;

    public Optional<List<Turno>> getTurnosByProfesionalYEstado(Long profesionalId, Long estadoId) throws ProfesionalInexistenteException, EstadoInexistenteException;

    public void deleteTurno(Long id) throws TurnoInexistenteException;

    public Turno createTurno(LocalDate fecha, LocalTime hora, Long idPaciente, Long idProfesional, Long idEstado, List<Long> imagenes, String notas) throws PacienteInexistenteException, EstadoInexistenteException, ProfesionalInexistenteException, ImagenInexistenteException;

    public Turno modifyTurno(Long idTurno, LocalDate fecha, LocalTime hora, Long idPaciente, Long idProfesional, Long idEstado, List<Long> imagenes, String notas) throws TurnoInexistenteException, PacienteInexistenteException, EstadoInexistenteException, ProfesionalInexistenteException, ImagenInexistenteException;

    public Turno reservarTurno(Long idTurno, Long idPaciente) throws TurnoInexistenteException, PacienteInexistenteException, EstadoInexistenteException, TurnoYaReservadoException ;

    Turno cancelarTurno(Long turnoId) throws TurnoInexistenteException, EstadoInexistenteException;
}
