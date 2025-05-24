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

import com.TPO.gestionturnos.entity.Afiliacion;
import com.TPO.gestionturnos.entity.DTOs.EliminarAfiliacionRequest;
import com.TPO.gestionturnos.entity.DTOs.ModificarAfiliacionRequest;
import com.TPO.gestionturnos.entity.DTOs.CrearAfiliacionRequest;
import com.TPO.gestionturnos.exceptions.AfiliacionIncompatibleException;
import com.TPO.gestionturnos.exceptions.AfiliacionInexistenteException;
import com.TPO.gestionturnos.exceptions.ObraSocialInexistenteException;
import com.TPO.gestionturnos.exceptions.PacienteInexistenteException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/afiliacion")
public class afiliacionController {
        
    //espacio para el service

    @Operation(summary = "Listar afiliaciones", description = "Devuelve una lista con todas las afiliaciones.")
    @GetMapping
    public ResponseEntity<List<Afiliacion>> getAfiliaciones() {
        // return ResponseEntity.ok(pacienteService.getPacientes());
        return null;
    }

    @Operation(summary = "Listar afiliaciones por id", description = "Devuelve la afiliacion cuyo id sea el ingresado.")
    @GetMapping("/{afiliacionId}")
    public ResponseEntity<Afiliacion> getAfiliacionById(@PathVariable Long afiliacionId) throws AfiliacionInexistenteException {
        // Optional<Paciente> result = pacienteService.getPacienteById(especialidadId);
        // if (result.isPresent())
        //     return ResponseEntity.ok(result.get());
        // return ResponseEntity.noContent().build();
        return null;
    }

    //listar por obra social

    @Operation(summary = "Listar afiliaciones por obra social", description = "Devuelve las afiliaciones que estén en una obra social. Si no existe la obra social, lanza una expceción")
    @GetMapping("afiliacion_obraSocial/{obraSocialId}")
    public ResponseEntity<List<Afiliacion>> getAfiliacioesnByObraSocial(@PathVariable Long obraSocialId) throws ObraSocialInexistenteException {
        // Optional<Paciente> result = pacienteService.getPacienteById(especialidadId);
        // if (result.isPresent())
        //     return ResponseEntity.ok(result.get());
        // return ResponseEntity.noContent().build();
        return null;
    }

    //listar por paciente

    @Operation(summary = "Listar afiliaciones por paciente", description = "Devuelve las afiliaciones de un paciente. Si no existe el paciente, lanza una expceción")
    @GetMapping("afiliacion_paciente/{pacienteId}")
    public ResponseEntity<List<Afiliacion>> getAfiliacionesByPaciente(@PathVariable Long pacienteId) throws PacienteInexistenteException {
        // Optional<Paciente> result = pacienteService.getPacienteById(especialidadId);
        // if (result.isPresent())
        //     return ResponseEntity.ok(result.get());
        // return ResponseEntity.noContent().build();
        return null;
    }

    @Operation(summary = "Crear una afiliacion", description = "Crea una nueva afiliacion en el sistema con el nroAfiliado, fkObraSocial, fkPaciente, fechaAlta, fechaFin. Si la afiliacion ya existe, lanza una excepción.")
    @PostMapping
    public ResponseEntity<Object> createAfiliacion(@RequestBody CrearAfiliacionRequest afiliacionRequest) throws AfiliacionIncompatibleException {
        // Paciente result = pacienteService.createPaciente(pacienteRequest.getNombre(), pacienteRequest.getApellido(), pacienteRequest.getMail(), pacienteRequest.getPassword());
        // return ResponseEntity.created(URI.create("/paciente/" + result.getId())).body(result);
        return null;
    }

    // hacer que este metodo sea privado, hacer void?
    @Operation(summary = "Modificar una afiliacion", description = "Modifica la afiliacion según el ID proporcionado. Si no existe la afiliacion, lanza una excepción.")
    @PutMapping("/{afiliacionId}")
    public ResponseEntity<Object> modifyAfiliacion(@RequestBody ModificarAfiliacionRequest afiliacionRequest) throws AfiliacionInexistenteException{
        // Paciente result = pacienteService.modifyPaciente(pacienteRequest.getId(), pacienteRequest.getNombre(), pacienteRequest.getApellido(), pacienteRequest.getMail(), pacienteRequest.getPassword());
        // return ResponseEntity.created(URI.create("/usuarios/" + result.getId())).body(result);
        return null;
    }

    @Operation(summary = "Eliminar una afiliacion", description = "Elimina una afiliacion según el ID proporcionado. Si no existe la afiliacion, lanza una excepción.")
    @DeleteMapping("/{afiliacionId}")
    public void deleteAfiliacion(@RequestBody EliminarAfiliacionRequest afiliacionRequest) throws AfiliacionInexistenteException{
        // hacer funcion
    }
}
