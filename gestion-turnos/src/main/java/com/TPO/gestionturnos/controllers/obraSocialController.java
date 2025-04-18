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

import com.TPO.gestionturnos.entity.ObraSocial;
import com.TPO.gestionturnos.entity.DTOs.EliminarObraSocialRequest;
import com.TPO.gestionturnos.entity.DTOs.ModificarCrearObraSocialRequest;
import com.TPO.gestionturnos.exceptions.ObraSocialExistenteException;
import com.TPO.gestionturnos.exceptions.ObraSocialInexistenteException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/obraSocial")
public class obraSocialController {
    
    //espacio para el service

    @Operation(summary = "Listar obras sociales", description = "Devuelve una lista con todas las obras sociales.")
    @GetMapping
    public ResponseEntity<List<ObraSocial>> getObrasSociales() {
        // return ResponseEntity.ok(pacienteService.getPacientes());
        return null;
    }

    @Operation(summary = "Listar obras sociales por id", description = "Devuelve la obra social cuyo id sea el ingresado.")
    @GetMapping("/{obraSocialId}")
    public ResponseEntity<ObraSocial> getObraSocialById(@PathVariable Long obraSocialId) throws ObraSocialInexistenteException {
        // Optional<Paciente> result = pacienteService.getPacienteById(especialidadId);
        // if (result.isPresent())
        //     return ResponseEntity.ok(result.get());
        // return ResponseEntity.noContent().build();
        return null;
    }
    // no creo que haga falta hacer este metodo
    @Operation(summary = "Listar obras sociales por nombre del plan", description = "Devuelve obras sociales que contengan planes con el nombre ingresado.")
    @GetMapping("os_search/{nombrePlan}")
    public ResponseEntity<List<ObraSocial>> getObrasSocialesByIPlan(@PathVariable String nombrePlan) /* deberia poder devolver una lista vacia? esto está pensando para que lo use el paciente */ {
        // Optional<Paciente> result = pacienteService.getPacienteById(especialidadId);
        // if (result.isPresent())
        //     return ResponseEntity.ok(result.get());
        // return ResponseEntity.noContent().build();
        return null;
    }

    @Operation(summary = "Listar obras sociales por nombre", description = "Devuelve la obra social cuyo nombre sea el ingresado.")
    @GetMapping("nombre/{obraSocialNombre}")
    public ResponseEntity<ObraSocial> getObraSocialByNombre(@PathVariable String obraSocialNombre) throws ObraSocialInexistenteException {
        // Optional<Paciente> result = pacienteService.getPacienteById(especialidadId);
        // if (result.isPresent())
        //     return ResponseEntity.ok(result.get());
        // return ResponseEntity.noContent().build();
        return null;
    }

    @Operation(summary = "Crear una orba social", description = "Crea una nueva obra social en el sistema con el nombre. Si la obra social ya existe, lanza una excepción.")
    @PostMapping
    public ResponseEntity<Object> createObraSocial(@RequestBody ModificarCrearObraSocialRequest obraSocialRequest) throws ObraSocialExistenteException {
        // Paciente result = pacienteService.createPaciente(pacienteRequest.getNombre(), pacienteRequest.getApellido(), pacienteRequest.getMail(), pacienteRequest.getPassword());
        // return ResponseEntity.created(URI.create("/paciente/" + result.getId())).body(result);
        return null;
    }

    // hacer que este metodo sea privado, hacer void?
    @Operation(summary = "Modificar una obra social", description = "Modifica el nombre de una obra social según el ID proporcionado. Si no existe la obra social, lanza una excepción.")
    @PutMapping("/{obraSocialId}")
    public ResponseEntity<Object> modifyObraSocial(@RequestBody ModificarCrearObraSocialRequest obraSocialRequest) throws ObraSocialInexistenteException{
        // Paciente result = pacienteService.modifyPaciente(pacienteRequest.getId(), pacienteRequest.getNombre(), pacienteRequest.getApellido(), pacienteRequest.getMail(), pacienteRequest.getPassword());
        // return ResponseEntity.created(URI.create("/usuarios/" + result.getId())).body(result);
        return null;
    }

    @Operation(summary = "Eliminar una obra social", description = "Elimina una obra social según el ID proporcionado. Si no existe la obra social, lanza una excepción.")
    @DeleteMapping("/{obraSocialId}")
    public void deleteObraSocial(@RequestBody EliminarObraSocialRequest obraSocialRequest) throws ObraSocialInexistenteException{
        // hacer funcion
    }

}
