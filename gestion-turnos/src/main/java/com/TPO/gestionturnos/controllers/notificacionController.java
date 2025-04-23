package com.TPO.gestionturnos.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TPO.gestionturnos.entity.Notificacion;
import com.TPO.gestionturnos.entity.DTOs.EliminarNotificacionRequest;
import com.TPO.gestionturnos.entity.DTOs.EliminarPacienteRequest;
import com.TPO.gestionturnos.entity.DTOs.ModificarNotificacionRequest;
import com.TPO.gestionturnos.entity.DTOs.NuevaNotificacionRequest;
import com.TPO.gestionturnos.exceptions.NotificacionInexistenteException;
import com.TPO.gestionturnos.exceptions.PacienteExistenteException;
import com.TPO.gestionturnos.exceptions.PacienteInexistenteException;
import com.TPO.gestionturnos.exceptions.TurnoInexistenteException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/notificaciones")
public class notificacionController {

    // @Autowired
    // private PacienteService pacienteService;

    @Operation(summary = "Listar notificaciones", description = "Devuelve una lista con todas las notificaciones.")
    @GetMapping
    public ResponseEntity<List<Notificacion>> getPacientes() {
        // return ResponseEntity.ok(pacienteService.getPacientes());
        return null;
    }

    @Operation(summary = "Listar notificacion por id", description = "Devuelve la notificacion cuyo id sea el ingresado.")
    @GetMapping("/{notificacionId}")
    public ResponseEntity<Notificacion> getNotificacionById(@PathVariable Long notificacionId) throws NotificacionInexistenteException {
        // Optional<Paciente> result = pacienteService.getPacienteById(pacienteId);
        // if (result.isPresent())
        //     return ResponseEntity.ok(result.get());

        // return ResponseEntity.noContent().build();
        return null;
    }

    @Operation(summary = "Listar notificaciones de un paciente", description = "Devuelve las notificaciones del paciente cuyo Id sea ingresado.")
    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<Notificacion> getNotificacionesByPaciente(@PathVariable String pacienteId) throws PacienteInexistenteException {
        // Optional<Paciente> result = pacienteService.getPacienteByMail(pacienteMail);
        // if (result.isPresent())
        //     return ResponseEntity.ok(result.get());

        // return ResponseEntity.noContent().build();
        return null;
    }

    @Operation(summary = "Listar notificaciones visibles de un paciente", description = "Devuelve las notificaciones del paciente cuyo Id sea ingresado.")
    @GetMapping("/visibles/{pacienteId}")
    public ResponseEntity<Notificacion> getNotificacionesVisiblesByPaciente(@PathVariable String pacienteId) throws PacienteInexistenteException {
        // Optional<Paciente> result = pacienteService.getPacienteByMail(pacienteMail);
        // if (result.isPresent())
        //     return ResponseEntity.ok(result.get());

        // return ResponseEntity.noContent().build();
        return null;
    }

    @Operation(summary = "Crear una notificacion", description = "Crea una nueva notificacion en el sistema con texto, idTurno, idPaciente, fecha y hora de envío. Si el paciente o el turno no existen, lanza una excepción")
    @PostMapping
    public ResponseEntity<Object> createNotificacion(@RequestBody NuevaNotificacionRequest notificacionRequest) throws PacienteExistenteException, TurnoInexistenteException {
        // Paciente result = pacienteService.createPaciente(pacienteRequest.getNombre(), pacienteRequest.getApellido(), pacienteRequest.getMail(), pacienteRequest.getPassword());
        // return ResponseEntity.created(URI.create("/paciente/" + result.getId())).body(result);
        return null;
    }

    // hacer que este metodo sea privado
    @Operation(summary = "Modificar una notificacion", description = "Modifica los datos de una notificacion según el ID proporcionado. Si no existe la notificacion, lanza una excepción.")
    @PutMapping("/{notificacionId}")
    public ResponseEntity<Object> modifyNotificacion(@RequestBody ModificarNotificacionRequest notificacionRequest) throws NotificacionInexistenteException{
        // Paciente result = pacienteService.modifyPaciente(pacienteRequest.getId(), pacienteRequest.getNombre(), pacienteRequest.getApellido(), pacienteRequest.getMail(), pacienteRequest.getPassword());
        // return ResponseEntity.created(URI.create("/usuarios/" + result.getId())).body(result);
        return null;
    }
    
    @Operation(summary = "Eliminar una notificacion", description = "Elimina una notificacion según el ID proporcionado. Si no existe la notificacion, lanza una excepción.")
    @PutMapping("/eliminar/{notificacionId}")
    public void deletePaciente(@RequestBody EliminarNotificacionRequest notificacionRequest) throws PacienteInexistenteException{
        // no borra de la bdd, hace baja logica
        // hacer funcion
    }
}