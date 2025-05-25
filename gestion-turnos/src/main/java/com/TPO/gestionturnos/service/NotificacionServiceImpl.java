package com.TPO.gestionturnos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TPO.gestionturnos.entity.Notificacion;
import com.TPO.gestionturnos.entity.Paciente;
import com.TPO.gestionturnos.entity.Turno;
import com.TPO.gestionturnos.exceptions.NotificacionInexistenteException;
import com.TPO.gestionturnos.exceptions.PacienteInexistenteException;
import com.TPO.gestionturnos.exceptions.TurnoInexistenteException;
import com.TPO.gestionturnos.repository.NotificacionesRepository;
import com.TPO.gestionturnos.repository.PacientesRepository;
import com.TPO.gestionturnos.repository.TurnosRepository;

@Service
public class NotificacionServiceImpl implements NotificacionService {
    @Autowired
    NotificacionesRepository notificacionesRepository;
    @Autowired
    PacientesRepository pacientesRepository;
    @Autowired
    TurnosRepository turnosRepository;

    @Override
    public List<Notificacion> getNotificaciones() {
        return notificacionesRepository.findAll();
    }

    @Override
    public Optional<Notificacion> getNotificacionById(Long notificacionId) throws NotificacionInexistenteException {
        return notificacionesRepository.findById(notificacionId);
    }

    @Override
    public Optional<List<Notificacion>> getNotificacionesByPaciente(Long pacienteId) throws PacienteInexistenteException {
        Optional<Paciente> paciente = pacientesRepository.findById(pacienteId);
        return notificacionesRepository.findByPaciente(paciente.get());
    }

    @Override
    public Optional<List<Notificacion>> getNotificacionesVisiblesByPaciente(Long pacienteId)
            throws PacienteInexistenteException {
        Optional<Paciente> paciente = pacientesRepository.findById(pacienteId);
        return notificacionesRepository.findByPacienteAndVisibleTrue(paciente.get());
    }

    @Override
    public Notificacion createNotificacion(String texto, Long idTurno, Long idPaciente)
            throws PacienteInexistenteException, TurnoInexistenteException {
        Optional<Paciente> paciente = pacientesRepository.findById(idPaciente);
        if (!paciente.isPresent()) {
            throw new PacienteInexistenteException();
        }

        Optional<Turno> turno = turnosRepository.findById(idTurno);
        if (!turno.isPresent()) {
            throw new TurnoInexistenteException();
        }
        return notificacionesRepository.save(new Notificacion(texto, turno.get(), paciente.get()));
    }

    @Override
    public Long deleteNotificacion(Long id) throws NotificacionInexistenteException {
        Optional<Notificacion> notificacion = notificacionesRepository.findById(id);
        if (notificacion.isPresent()){
            notificacion.get().setVisible(false);
            notificacionesRepository.save(notificacion.get());
            return notificacion.get().getId();
        }
        else{
            throw new NotificacionInexistenteException();
        }
    }
    
}
