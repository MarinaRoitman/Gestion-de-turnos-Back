package com.TPO.gestionturnos.controllers;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TPO.gestionturnos.entity.Especialidad;
import com.TPO.gestionturnos.entity.DTOs.EliminarEspecialidadRequest;
import com.TPO.gestionturnos.entity.DTOs.ModificarEspecialidadRequest;
import com.TPO.gestionturnos.entity.DTOs.CrearEspecialidadRequest;
import com.TPO.gestionturnos.exceptions.EspecialidadExistenteException;
import com.TPO.gestionturnos.exceptions.EspecialidadInexistenteException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/especialidad")
public class especialidadController {
    
    //espacio para el service

    @Operation(summary = "Listar especialidades", description = "Devuelve una lista con todas las especialidades.")
    @GetMapping
    public ResponseEntity<List<Especialidad>> getEspecialidades() {
        // return ResponseEntity.ok(pacienteService.getPacientes());
        return null;
    }

    @Operation(summary = "Listar especialidades por id", description = "Devuelve la especialidad cuyo id sea el ingresado.")
    @GetMapping("/{especialidadId}")
    public ResponseEntity<Especialidad> getEspecialidadById(@PathVariable Long especialidadId) throws EspecialidadInexistenteException {
        // Optional<Paciente> result = pacienteService.getPacienteById(especialidadId);
        // if (result.isPresent())
        //     return ResponseEntity.ok(result.get());
        // return ResponseEntity.noContent().build();
        return null;
    }

    @Operation(summary = "Crear una especialidad", description = "Crea una nueva especialidad en el sistema con el nombre. Si la especialidad ya existe, lanza una excepción.")
    @PostMapping
    public ResponseEntity<Object> createEspecialidad(@RequestBody CrearEspecialidadRequest especialidadRequest) throws EspecialidadExistenteException {
        // Paciente result = pacienteService.createPaciente(pacienteRequest.getNombre(), pacienteRequest.getApellido(), pacienteRequest.getMail(), pacienteRequest.getPassword());
        // return ResponseEntity.created(URI.create("/paciente/" + result.getId())).body(result);
        return null;
    }

    // hacer que este metodo sea privado, hacer void?
    @Operation(summary = "Modificar una especialidad", description = "Modifica el nombre de una especialidad según el ID proporcionado. Si no existe la especialidad, lanza una excepción.")
    @PutMapping("/{especialidadId}")
    public ResponseEntity<Object> modifyEspecialidad(@RequestBody ModificarEspecialidadRequest especialidadRequest) throws EspecialidadInexistenteException{
        // Paciente result = pacienteService.modifyPaciente(pacienteRequest.getId(), pacienteRequest.getNombre(), pacienteRequest.getApellido(), pacienteRequest.getMail(), pacienteRequest.getPassword());
        // return ResponseEntity.created(URI.create("/usuarios/" + result.getId())).body(result);
        return null;
    }

    @Operation(summary = "Eliminar una especialidad", description = "Elimina una especialidad según el ID proporcionado. Si no existe la especialidad, lanza una excepción.")
    @DeleteMapping("/{especialidadId}")
    public void deleteEspecialidad(@RequestBody EliminarEspecialidadRequest especialidadRequest) throws EspecialidadInexistenteException{
        // hacer funcion
    }
}
