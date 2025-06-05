package com.TPO.gestionturnos.controllers;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.TPO.gestionturnos.exceptions.EstadoInexistenteException;
import com.TPO.gestionturnos.exceptions.ImagenInexistenteException;
import com.TPO.gestionturnos.exceptions.PacienteInexistenteException;
import com.TPO.gestionturnos.exceptions.ProfesionalInexistenteException;
import com.TPO.gestionturnos.exceptions.TurnoInexistenteException;
import com.TPO.gestionturnos.service.TurnoService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/turno")
public class turnoController {
    @Autowired
    private TurnoService turnoService;

    @Operation(summary = "Listar turnos", description = "Devuelve una lista con todos los turnos.")
    @GetMapping
    public ResponseEntity<List<Turno>> getTurnos() {
        return ResponseEntity.ok(turnoService.getTurnos());
    }

    @Operation(summary = "Listar turnos por id", description = "Devuelve el turno cuyo id sea el ingresado. Si no existe, lanza una excepcion")
    @GetMapping("/{turnoId}")
    public ResponseEntity<Optional<Turno>> getTurnoById(@PathVariable Long turnoId) throws TurnoInexistenteException {
        return ResponseEntity.ok(turnoService.getTurnoById(turnoId));
    }

    @Operation(summary = "Listar turnos con fecha igual a la ingresada", description = "Devuelve turnos cuya fecha sea la ingresada")
    @GetMapping("fecha/{fecha}")
    public ResponseEntity<Optional<List<Turno>>> getTurnosPorFecha(@PathVariable LocalDate fecha){
        return ResponseEntity.ok(turnoService.getTurnosPorFecha(fecha));
    }

    @Operation(summary = "Listar turnos con fecha mayor a la ingresada", description = "Devuelve turnos cuya fecha sea mayor a la ingresada")
    @GetMapping("desde/{fecha}")
    public ResponseEntity<Optional<List<Turno>>> getTurnosMayorFecha(@PathVariable LocalDate fecha) {
        return ResponseEntity.ok(turnoService.getTurnosMayorFecha(fecha));
    }

    // listar turnos por estado
    @Operation(summary = "Listar turnos por estado", description = "Devuelve turnos cuyo estado sea el ingresado")
    @GetMapping("estado/{estadoId}")
    public ResponseEntity<Optional<List<Turno>>> getTurnosByEstado(@PathVariable Long estadoId) throws EstadoInexistenteException {
        return ResponseEntity.ok(turnoService.getTurnosByEstado(estadoId));
    }

    // listar turnos por paciente
    @Operation(summary = "Listar turnos por paciente", description = "Devuelve los turnos de un paciente")
    @GetMapping("paciente/{pacienteId}")
    public ResponseEntity<Optional<List<Turno>>> getTurnosByPaciente(@PathVariable Long pacienteId) throws PacienteInexistenteException {
        return ResponseEntity.ok(turnoService.getTurnosByPaciente(pacienteId));
    }

    // listar turnos por profesional
    @Operation(summary = "Listar turnos por profesional", description = "Devuelve los turnos de un profesional")
    @GetMapping("profesional/{profesionalId}")
    public ResponseEntity<Optional<List<Turno>>> getTurnosByProfesional(@PathVariable Long profesionalId) throws ProfesionalInexistenteException{
        return ResponseEntity.ok(turnoService.getTurnosByProfesional(profesionalId));
    }

    @Operation(summary = "Listar turnos por paciente y estado", description = "Devuelve los turnos de un paciente con un estado determinado")
    @GetMapping("paciente/{pacienteId}/estado/{estadoId}")
    public ResponseEntity<Optional<List<Turno>>> getTurnosByPacienteYEstado(@PathVariable Long pacienteId, @PathVariable Long estadoId) throws PacienteInexistenteException, EstadoInexistenteException {
        return ResponseEntity.ok(turnoService.getTurnosByPacienteYEstado(pacienteId, estadoId));
    }

    @Operation(summary = "Listar turnos por profesional y estado", description = "Devuelve los turnos de un profesional con un estado determinado")
    @GetMapping("profesional/{profesionalId}/estado/{estadoId}")
    public ResponseEntity<Optional<List<Turno>>> getTurnosByProfesionalYEstado(@PathVariable Long profesionalId, @PathVariable Long estadoId) throws ProfesionalInexistenteException, EstadoInexistenteException {
        return ResponseEntity.ok(turnoService.getTurnosByProfesionalYEstado(profesionalId, estadoId));
    }

    @Operation(summary = "Crear un turno", description = "Crea un turno.")
    @PostMapping
    public ResponseEntity<Object> createTurno(@RequestBody CrearTurnoRequest turnoRequest) throws PacienteInexistenteException, EstadoInexistenteException, ProfesionalInexistenteException, ImagenInexistenteException {// deberia tirar error si ya existe? a lo mejor el medico puede atender doble?
        Turno result = turnoService.createTurno(
                turnoRequest.getFecha(),
                turnoRequest.getHora(),
                turnoRequest.getIdPaciente(),
                turnoRequest.getIdProfesional(),
                turnoRequest.getIdEstado(),
                turnoRequest.getIdImagenes());
        return ResponseEntity.created(URI.create("/turno/" + result.getId())).body(result);
    }

    @Operation(summary = "Modificar un turno", description = "Modifica un turno según el ID proporcionado. Si no existe el turno, lanza una excepción.")
    @PutMapping("/{turnoId}")
    public ResponseEntity<Object> modifyTurno(@RequestBody ModificarTurnoRequest turnoRequest) throws TurnoInexistenteException, PacienteInexistenteException, EstadoInexistenteException, ProfesionalInexistenteException, ImagenInexistenteException{
        Turno result = turnoService.modifyTurno(
                turnoRequest.getId(),
                turnoRequest.getFecha(),
                turnoRequest.getHora(),
                turnoRequest.getIdPaciente(),
                turnoRequest.getIdProfesional(),
                turnoRequest.getIdEstado(),
                turnoRequest.getIdImagenes());
        return ResponseEntity.created(URI.create("/turno/" + result.getId())).body(result);
    }

    @Operation(summary = "Eliminar un turno", description = "Elimina un turno según el ID proporcionado. Si no existe el turno, lanza una excepción.")
    @DeleteMapping("/{turnoId}")
    public ResponseEntity<Void> deleteTurno(@RequestBody EliminarTurnosRequest turnoRequest) throws TurnoInexistenteException{
        turnoService.deleteTurno(turnoRequest.getId());
        return ResponseEntity.noContent().build();
    }
}
