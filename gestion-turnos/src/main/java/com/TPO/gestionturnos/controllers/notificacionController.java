package com.TPO.gestionturnos.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TPO.gestionturnos.entity.Notificacion;
import com.TPO.gestionturnos.entity.ObraSocial;
import com.TPO.gestionturnos.entity.DTOs.EliminarNotificacionRequest;
import com.TPO.gestionturnos.entity.DTOs.NuevaNotificacionRequest;
import com.TPO.gestionturnos.exceptions.NotificacionInexistenteException;
import com.TPO.gestionturnos.exceptions.PacienteInexistenteException;
import com.TPO.gestionturnos.exceptions.TurnoInexistenteException;
import com.TPO.gestionturnos.service.NotificacionService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/notificaciones")
public class notificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @Operation(summary = "Listar notificaciones", description = "Devuelve una lista con todas las notificaciones.")
    @GetMapping
    public ResponseEntity<List<Notificacion>> getNotificaciones() {
        return ResponseEntity.ok(notificacionService.getNotificaciones());
    }

    @Operation(summary = "Listar notificacion por id", description = "Devuelve la notificacion cuyo id sea el ingresado.")
    @GetMapping("/{notificacionId}")
    public ResponseEntity<Optional<Notificacion>> getNotificacionById(@PathVariable Long notificacionId) throws NotificacionInexistenteException {
        return ResponseEntity.ok(notificacionService.getNotificacionById(notificacionId));
    }

    @Operation(summary = "Listar notificaciones de un paciente", description = "Devuelve las notificaciones del paciente cuyo Id sea ingresado.")
    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<Optional<List<Notificacion>>> getNotificacionesByPaciente(@PathVariable Long pacienteId) throws PacienteInexistenteException {
        return ResponseEntity.ok(notificacionService.getNotificacionesByPaciente(pacienteId));
    }
    
    @Operation(summary = "Listar notificaciones visibles de un paciente", description = "Devuelve las notificaciones del paciente cuyo Id sea ingresado.")
    @GetMapping("/visibles/{pacienteId}")
    public ResponseEntity<Optional<List<Notificacion>>> getNotificacionesVisiblesByPaciente(@PathVariable Long pacienteId) throws PacienteInexistenteException {
        return ResponseEntity.ok(notificacionService.getNotificacionesVisiblesByPaciente(pacienteId));
    }

    @Operation(summary = "Crear una notificacion", description = "Crea una nueva notificacion en el sistema con texto, idTurno, idPaciente, fecha y hora de envío. Si el paciente o el turno no existen, lanza una excepción")
    @PostMapping
    public ResponseEntity<Object> createNotificacion(@RequestBody NuevaNotificacionRequest notificacionRequest) throws PacienteInexistenteException, TurnoInexistenteException {
        Notificacion result = notificacionService.createNotificacion(notificacionRequest.getTexto(), notificacionRequest.getIdTurno(), notificacionRequest.getIdPaciente());
        return ResponseEntity.created(URI.create("/notificaciones/" + result.getId())).body(result);
    }
    
    @Operation(summary = "Eliminar una notificacion", description = "Elimina una notificacion según el ID proporcionado. Si no existe la notificacion, lanza una excepción.")
    @PutMapping("/eliminar/{notificacionId}")
    public ResponseEntity<Long> deletePaciente(@RequestBody EliminarNotificacionRequest notificacionRequest) throws NotificacionInexistenteException{
        Long result = notificacionService.deleteNotificacion(notificacionRequest.getId());
        return ResponseEntity.created(URI.create("/notificaciones/" + result)).body(result);
    }
}