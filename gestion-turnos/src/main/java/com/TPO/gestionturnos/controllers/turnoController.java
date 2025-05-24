package com.TPO.gestionturnos.controllers;
import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TPO.gestionturnos.entity.Turno;
import com.TPO.gestionturnos.entity.DTOs.EliminarTurnosRequest;
import com.TPO.gestionturnos.entity.DTOs.ModificarTurnoRequest;
import com.TPO.gestionturnos.entity.DTOs.CrearTurnoRequest;
import com.TPO.gestionturnos.exceptions.TurnoInexistenteException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/turno")
public class turnoController {
    @Operation(summary = "Listar turnos", description = "Devuelve una lista con todos los turnos.")
    @GetMapping
    public ResponseEntity<List<Turno>> getTurnos() {
        // return ResponseEntity.ok(pacienteService.getPacientes());
        return null;
    }

    @Operation(summary = "Listar turnos por id", description = "Devuelve el turno cuyo id sea el ingresado. Si no existe, lanza una excepcion")
    @GetMapping("/{turnoId}")
    public ResponseEntity<Turno> getTurnoById(@PathVariable Long turnoId) throws TurnoInexistenteException {
        // Optional<Paciente> result = pacienteService.getPacienteById(especialidadId);
        // if (result.isPresent())
        //     return ResponseEntity.ok(result.get());
        // return ResponseEntity.noContent().build();
        return null;
    }

    @Operation(summary = "Listar turnos con fecha igual a la ingresada", description = "Devuelve turnos cuya fecha sea la ingresada")
    @GetMapping("fecha/{fecha}")
    public ResponseEntity<List<Turno>> getTurnosPorFecha(@PathVariable LocalDate fecha) {
        // Optional<Paciente> result = pacienteService.getPacienteById(especialidadId);
        // if (result.isPresent())
        //     return ResponseEntity.ok(result.get());
        // return ResponseEntity.noContent().build();
        return null;
    }

    @Operation(summary = "Listar turnos con fecha mayor a la ingresada", description = "Devuelve turnos cuya fecha sea mayor a la ingresada")
    @GetMapping("desde/{fecha}")
    public ResponseEntity<List<Turno>> getTurnosMayorFecha(@PathVariable LocalDate fecha) {
        // Optional<Paciente> result = pacienteService.getPacienteById(especialidadId);
        // if (result.isPresent())
        //     return ResponseEntity.ok(result.get());
        // return ResponseEntity.noContent().build();
        return null;
    }

    // listar turnos por estado
    @Operation(summary = "Listar turnos por estado", description = "Devuelve turnos cuyo estado sea el ingresado")
    @GetMapping("estado/{estadoId}")
    public ResponseEntity<List<Turno>> getTurnosByEstado(@PathVariable Long estadoId) {
        // Optional<Paciente> result = pacienteService.getPacienteById(especialidadId);
        // if (result.isPresent())
        //     return ResponseEntity.ok(result.get());
        // return ResponseEntity.noContent().build();
        return null;
    }

    // listar turnos por paciente
    @Operation(summary = "Listar turnos por paciente", description = "Devuelve los turnos de un paciente")
    @GetMapping("paciente/{pacienteId}")
    public ResponseEntity<List<Turno>> getTurnosByPaciente(@PathVariable Long pacienteId) {
        // Optional<Paciente> result = pacienteService.getPacienteById(especialidadId);
        // if (result.isPresent())
        //     return ResponseEntity.ok(result.get());
        // return ResponseEntity.noContent().build();
        return null;
    }

    // listar turnos por profesional
    @Operation(summary = "Listar turnos por estado", description = "Devuelve los turnos de un profesional")
    @GetMapping("profesional/{profesionalId}")
    public ResponseEntity<List<Turno>> getTurnosByProfesional(@PathVariable Long profesionalId) {
        // Optional<Paciente> result = pacienteService.getPacienteById(especialidadId);
        // if (result.isPresent())
        //     return ResponseEntity.ok(result.get());
        // return ResponseEntity.noContent().build();
        return null;
    }


    @Operation(summary = "Crear un turno", description = "Crea un turno.")
    @PostMapping
    public ResponseEntity<Object> createTurno(@RequestBody CrearTurnoRequest turnoRequest) {// deberia tirar error si ya existe? a lo mejor el medico puede atender doble?
        // Paciente result = pacienteService.createPaciente(pacienteRequest.getNombre(), pacienteRequest.getApellido(), pacienteRequest.getMail(), pacienteRequest.getPassword());
        // return ResponseEntity.created(URI.create("/paciente/" + result.getId())).body(result);
        return null;
    }

    // hacer que este metodo sea privado, hacer void?
    @Operation(summary = "Modificar un turno", description = "Modifica un turno según el ID proporcionado. Si no existe el turno, lanza una excepción.")
    @PutMapping("/{turnoId}")
    public ResponseEntity<Object> modifyTurno(@RequestBody ModificarTurnoRequest turnoRequest) throws TurnoInexistenteException{
        // Paciente result = pacienteService.modifyPaciente(pacienteRequest.getId(), pacienteRequest.getNombre(), pacienteRequest.getApellido(), pacienteRequest.getMail(), pacienteRequest.getPassword());
        // return ResponseEntity.created(URI.create("/usuarios/" + result.getId())).body(result);
        return null;
    }

    @Operation(summary = "Eliminar un turno", description = "Elimina un turno según el ID proporcionado. Si no existe el turno, lanza una excepción.")
    @DeleteMapping("/{turnoId}")
    public void deleteTurno(@RequestBody EliminarTurnosRequest turnoRequest) throws TurnoInexistenteException{
        // hacer funcion
    }
}
