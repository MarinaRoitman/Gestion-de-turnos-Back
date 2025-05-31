package com.TPO.gestionturnos.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TPO.gestionturnos.entity.Estado;
import com.TPO.gestionturnos.entity.Imagen;
import com.TPO.gestionturnos.entity.Paciente;
import com.TPO.gestionturnos.entity.Profesional;
import com.TPO.gestionturnos.entity.Turno;
import com.TPO.gestionturnos.exceptions.EstadoInexistenteException;
import com.TPO.gestionturnos.exceptions.ImagenInexistenteException;
import com.TPO.gestionturnos.exceptions.PacienteInexistenteException;
import com.TPO.gestionturnos.exceptions.ProfesionalInexistenteException;
import com.TPO.gestionturnos.exceptions.TurnoInexistenteException;
import com.TPO.gestionturnos.repository.EstadosRepository;
import com.TPO.gestionturnos.repository.ImagenesRepository;
import com.TPO.gestionturnos.repository.PacientesRepository;
import com.TPO.gestionturnos.repository.ProfesionalesRepository;
import com.TPO.gestionturnos.repository.TurnosRepository;

@Service
public class TurnoServiceImpl implements TurnoService {

    @Autowired
    private TurnosRepository turnosRepository;

    @Autowired
    private ProfesionalesRepository profesionalesRepository;

    @Autowired
    private PacientesRepository pacienteRepository;

    @Autowired
    private EstadosRepository estadosRepository;

    @Autowired
    ImagenesRepository imagenesRepository;

    @Override
    public List<Turno> getTurnos() {
        return turnosRepository.findAll();
    }

    @Override
    public Optional<Turno> getTurnoById(Long turnolId) throws TurnoInexistenteException {
        return turnosRepository.findById(turnolId);
    }

    @Override
    public Optional<List<Turno>> getTurnosPorFecha(LocalDate fechaTurno) {
        return turnosRepository.findByFecha(fechaTurno);
    }

    @Override
    public Optional<List<Turno>> getTurnosMayorFecha(LocalDate fechaTurno) {
        return turnosRepository.findByFechaGreaterThanEqual(fechaTurno);
    }

    @Override
    public Optional<List<Turno>> getTurnosByEstado(Long estadoId) throws EstadoInexistenteException {
        Optional<Estado> estado = estadosRepository.findById(estadoId);
        if (estado.isEmpty()) {
            throw new EstadoInexistenteException();
        }
        return turnosRepository.findByEstado(estado.get());
    }

    @Override
    public Optional<List<Turno>> getTurnosByPaciente(Long pacienteId) throws PacienteInexistenteException {
        Optional<Paciente> paciente = pacienteRepository.findById(pacienteId);
        if (paciente.isEmpty()) {
            throw new PacienteInexistenteException();
        }
        return turnosRepository.findByPaciente(paciente.get());
    }

    @Override
    public Optional<List<Turno>> getTurnosByProfesional(Long profesionalid) throws ProfesionalInexistenteException {
        Optional<Profesional> profesional = profesionalesRepository.findById(profesionalid);
        if (profesional.isEmpty()) {
            throw new ProfesionalInexistenteException();
        }
        return turnosRepository.findByProfesional(profesional.get());
    }

    @Override
    public Optional<List<Turno>> getTurnosByPacienteYEstado(Long pacienteId, Long estadoId)
            throws PacienteInexistenteException, EstadoInexistenteException {
        Optional<Paciente> paciente = pacienteRepository.findById(pacienteId);
        if (paciente.isEmpty()) {
            throw new PacienteInexistenteException();
        }
        Optional<Estado> estado = estadosRepository.findById(estadoId);
        if (estado.isEmpty()) {
            throw new EstadoInexistenteException();
        }
        return turnosRepository.findByPacienteAndEstado(paciente.get(), estado.get());
    }

    @Override
    public Optional<List<Turno>> getTurnosByProfesionalYEstado(Long profesionalId, Long estadoId)
            throws ProfesionalInexistenteException, EstadoInexistenteException {
        Optional<Profesional> profesional = profesionalesRepository.findById(profesionalId);
        if (profesional.isEmpty()) {
            throw new ProfesionalInexistenteException();
        }
        Optional<Estado> estado = estadosRepository.findById(estadoId);
        if (estado.isEmpty()) {
            throw new EstadoInexistenteException();
        }
        return turnosRepository.findByProfesionalAndEstado(profesional.get(), estado.get());
    }

    @Override
    public void deleteTurno(Long id) throws TurnoInexistenteException {
        Optional<Turno> turno = turnosRepository.findById(id);
        if (turno.isEmpty()) {
            throw new TurnoInexistenteException();
        }
        turnosRepository.deleteById(id);
    }

    @Override
    public Turno createTurno(LocalDate fecha, LocalTime hora, Long idPaciente, Long idProfesional, Long idEstado,
            List<Long> imagenes)
            throws PacienteInexistenteException, EstadoInexistenteException, ProfesionalInexistenteException, ImagenInexistenteException {
        
        Optional<Paciente> paciente = pacienteRepository.findById(idPaciente);
        if (paciente.isEmpty()) {
            throw new PacienteInexistenteException();
        }

        Optional<Profesional> profesional = profesionalesRepository.findById(idProfesional);
        if (profesional.isEmpty()) {
            throw new ProfesionalInexistenteException();
        }

        Optional<Estado> estado = estadosRepository.findById(idEstado);
        if (estado.isEmpty()) {
            throw new EstadoInexistenteException();
        }

        List<Imagen> imagenesList = new ArrayList<>();
        for (Long idImagen : imagenes) {
            Optional<Imagen> imagenOpt = imagenesRepository.findById(idImagen);
            if (imagenOpt.isEmpty()) {
                throw new ImagenInexistenteException();
            }
            imagenesList.add(imagenOpt.get());
        }

        Turno turno = new Turno();
        turno.setFecha(fecha);
        turno.setHora(hora);
        turno.setPaciente(paciente.get());
        turno.setProfesional(profesional.get());
        turno.setEstado(estado.get());
        turno.setImagenes(imagenesList);
        return turnosRepository.save(turno);
    }

    @Override
    public Turno modifyTurno(Long idTurno, LocalDate fecha, LocalTime hora, Long idPaciente, Long idProfesional,
            Long idEstado, List<Long> imagenes) throws TurnoInexistenteException, PacienteInexistenteException,
            EstadoInexistenteException, ProfesionalInexistenteException, ImagenInexistenteException {
        Optional<Turno> turnoOriginal = turnosRepository.findById(idTurno);
        if (turnoOriginal.isEmpty()) {
            throw new TurnoInexistenteException();
        }
        Turno turno = turnoOriginal.get();
        if (fecha != null) turno.setFecha(fecha);
        if (hora != null) turno.setHora(hora);
        if (idPaciente != null) {
            Optional<Paciente> paciente = pacienteRepository.findById(idPaciente);
            if (paciente.isEmpty()) {
                throw new PacienteInexistenteException();
            }
            turno.setPaciente(paciente.get());
        }
        if (idProfesional != null) {
            Optional<Profesional> profesional = profesionalesRepository.findById(idProfesional);
            if (profesional.isEmpty()) {
                throw new ProfesionalInexistenteException();
            }
            turno.setProfesional(profesional.get());
        }
        if (idEstado != null) {
            Optional<Estado> estado = estadosRepository.findById(idEstado);
            if (estado.isEmpty()) {
                throw new EstadoInexistenteException();
            }
            turno.setEstado(estado.get());
        }
        if (imagenes != null) {
            List<Imagen> imagenesList = new ArrayList<>();
            for (Long idImagen : imagenes) {
                Optional<Imagen> imagenOpt = imagenesRepository.findById(idImagen);
                if (imagenOpt.isEmpty()) {
                    throw new ImagenInexistenteException();
                }
                imagenesList.add(imagenOpt.get());
            }
            turno.setImagenes(imagenesList);
        }
        return turnosRepository.save(turno);
    }    
}
