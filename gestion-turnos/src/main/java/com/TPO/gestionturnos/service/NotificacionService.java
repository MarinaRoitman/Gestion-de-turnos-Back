package com.TPO.gestionturnos.service;

import java.util.List;
import java.util.Optional;

import com.TPO.gestionturnos.entity.Notificacion;
import com.TPO.gestionturnos.exceptions.NotificacionInexistenteException;
import com.TPO.gestionturnos.exceptions.PacienteInexistenteException;
import com.TPO.gestionturnos.exceptions.TurnoInexistenteException;

public interface NotificacionService {
    public List<Notificacion> getNotificaciones();
    public Optional<Notificacion> getNotificacionById(Long notificacionId) throws NotificacionInexistenteException;
    public Optional<List<Notificacion>> getNotificacionesByPaciente(Long pacienteId) throws PacienteInexistenteException;
    public Optional<List<Notificacion>> getNotificacionesVisiblesByPaciente(Long pacienteId) throws PacienteInexistenteException;
    public Notificacion createNotificacion(String texto, Long idTurno, Long idPaciente) throws PacienteInexistenteException, TurnoInexistenteException;
    public Long deleteNotificacion(Long id) throws NotificacionInexistenteException;
}
