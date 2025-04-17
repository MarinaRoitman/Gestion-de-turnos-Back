package com.TPO.gestionturnos.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.TPO.gestionturnos.entity.Paciente;
import com.TPO.gestionturnos.entity.DTOs.LoginPacienteRequest;
import com.TPO.gestionturnos.entity.DTOs.ModificarPacienteRequest;
import com.TPO.gestionturnos.entity.DTOs.NuevoPacienteRequest;
import com.TPO.gestionturnos.exceptions.PacienteExistenteException;
import com.TPO.gestionturnos.exceptions.PacienteInexistenteException;
import com.TPO.gestionturnos.exceptions.PacienteLoginNoExitosoException;
import com.TPO.gestionturnos.service.PacienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/pacientes")
public class PacientesController {

    @Autowired
    private PacienteService pacienteService;

    @Operation(summary = "Listar pacientes", description = "Devuelve una lista con todos los pacientes.")
    @GetMapping
    public ResponseEntity<List<Paciente>> getPacientes() {
        return ResponseEntity.ok(pacienteService.getPacientes());
    }

    @Operation(summary = "Listar pacientes por id", description = "Devuelve el paciente cuyo id sea el ingresado.")
    @GetMapping("/{pacienteId}")
    public ResponseEntity<Paciente> getPacienteById(@PathVariable Long pacienteId) throws PacienteInexistenteException {
        Optional<Paciente> result = pacienteService.getPacienteById(pacienteId);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Listar pacientes por email", description = "Devuelve el paciente cuyo email sea el ingresado.")
    @GetMapping("/mail/{pacientemail}")
    public ResponseEntity<Paciente> getPacienteByMail(@PathVariable String pacientemail) throws PacienteInexistenteException {
        Optional<Paciente> result = pacienteService.getPacienteByMail(pacientemail);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Crear un paciente", description = "Crea un nuevo paciente en el sistema con nombre, apellido, mail y contraseña. Si el mail ya existe, lanza una excepción.")
    @PostMapping
    public ResponseEntity<Object> createPaciente(@RequestBody NuevoPacienteRequest pacienteRequest)
            throws PacienteExistenteException {
        Paciente result = pacienteService.createPaciente(pacienteRequest.getNombre(), pacienteRequest.getApellido(), pacienteRequest.getMail(), pacienteRequest.getPassword());
        return ResponseEntity.created(URI.create("/paciente/" + result.getId())).body(result);
    }

    @Operation(summary = "Checkea que el email y contraseña matcheen", description = "Verifica si el mail y la contraseña proporcionados corresponden a un paciente registrado. Si son válidos, devuelve el ID del paciente.")
    @PostMapping("/login")
    public ResponseEntity<Object> loginPaciente(@RequestBody LoginPacienteRequest pacienteRequest)
            throws PacienteLoginNoExitosoException {
        Long result = pacienteService.loginPaciente(pacienteRequest.getMail(), pacienteRequest.getPassword());
        return ResponseEntity.created(URI.create("/usuarios/login/" + result)).body(result);
    }

    // hacer que este metodo sea privado
    @Operation(summary = "Modificar un paciente", description = "Modifica los datos de un paciente ya registrado (nombre, apellido, mail y/o contraseña) según el ID proporcionado. Si no existe el paciente, lanza una excepción.")
    @PutMapping
    public ResponseEntity<Object> modifyPaciente(@RequestBody ModificarPacienteRequest pacienteRequest) throws PacienteInexistenteException{
        Paciente result = pacienteService.modifyPaciente(pacienteRequest.getId(), pacienteRequest.getNombre(), pacienteRequest.getApellido(), pacienteRequest.getMail(), pacienteRequest.getPassword());
        return ResponseEntity.created(URI.create("/usuarios/" + result.getId())).body(result);
    }
}