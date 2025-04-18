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

import com.TPO.gestionturnos.entity.Especialidad;
import com.TPO.gestionturnos.entity.Profesional;
import com.TPO.gestionturnos.entity.DTOs.EliminarProfesionalRequest;
import com.TPO.gestionturnos.entity.DTOs.ModificarCrearProfesionalRequest;
import com.TPO.gestionturnos.exceptions.EspecialidadInexistenteException;
import com.TPO.gestionturnos.exceptions.ProfesionalExistenteException;
import com.TPO.gestionturnos.exceptions.ProfesionalInexistenteException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/profesional")
public class profesionalController {
    
    @Operation(summary = "Listar profesionales", description = "Devuelve una lista con todos los profesionales.")
    @GetMapping
    public ResponseEntity<List<Profesional>> getProfesionales() {
        // return ResponseEntity.ok(Service.getProfesionales());
        return null;
    }

    @Operation(summary = "Listar profesionales por id", description = "Devuelve el profesional cuyo id sea el ingresado.")
    @GetMapping("/{profesionalId}")
    public ResponseEntity<Profesional> getProfesionalById(@PathVariable Long profesionalId) throws ProfesionalInexistenteException {
        // Optional<Paciente> result = pacienteService.getPacienteById(pacienteId);
        // if (result.isPresent())
        //     return ResponseEntity.ok(result.get());

        // return ResponseEntity.noContent().build();
        return null;
    }

    @Operation(summary = "Listar profesionales por email", description = "Devuelve el profesional cuyo email sea el ingresado.")
    @GetMapping("/mail/{profesionalMail}")
    public ResponseEntity<Profesional> getProfesionalByMail(@PathVariable String profesionalMail) throws ProfesionalInexistenteException {
        // Optional<Paciente> result = pacienteService.getPacienteByMail(pacientemail);
        // if (result.isPresent())
        //     return ResponseEntity.ok(result.get());

        // return ResponseEntity.noContent().build();
        return null;
    }

    @Operation(summary = "Listar profesionales por especialidad", description = "Devuelve los profesionales especializados en al especialidad ingresada. Si la especialidad no existe, lanza una excepción")
    @GetMapping("/especialidad/{especialidadId}")
    public ResponseEntity<List<Profesional>> getProfesionalesByEspecialidad(@PathVariable Long especialidadId/* chequear si va la clase o el id */) throws EspecialidadInexistenteException {
        // Optional<Paciente> result = pacienteService.getPacienteByMail(pacientemail);
        // if (result.isPresent())
        //     return ResponseEntity.ok(result.get());

        // return ResponseEntity.noContent().build();
        return null;
    }

    @Operation(summary = "Listar profesionales por nombre", description = "Devuelve los profesionales con el nombre o apellido ingresado.")
    @GetMapping("/nombre/{profesionalNombre}")
    public ResponseEntity<List<Profesional>> getProfesionalesByNombre(@PathVariable String profesionalNombre/* chequear si va la clase o el id */) /* no tira excepción, solo devolveria una lista vacia */ {
        // Optional<Paciente> result = pacienteService.getPacienteByMail(pacientemail);
        // if (result.isPresent())
        //     return ResponseEntity.ok(result.get());

        // return ResponseEntity.noContent().build();
        return null;
    }

    @Operation(summary = "Crear un profesional", description = "Crea un nuevo profesional en el sistema con nombre, apellido, mail y nroMatricula. Si el mail o nroMatricula ya existe, lanza una excepción.")
    @PostMapping
    public ResponseEntity<Object> createProfesional(@RequestBody ModificarCrearProfesionalRequest profesionalRequest) throws ProfesionalExistenteException {
        // Paciente result = pacienteService.createPaciente(pacienteRequest.getNombre(), pacienteRequest.getApellido(), pacienteRequest.getMail(), pacienteRequest.getPassword());
        // return ResponseEntity.created(URI.create("/paciente/" + result.getId())).body(result);
        return null;
    }

    // hacer que este metodo sea privado
    @Operation(summary = "Modificar un profesional", description = "Modifica los datos de un profesional ya registrado (nombre, apellido, mail y/o matricula) según el ID proporcionado. Si no existe el profesional, lanza una excepción.")
    @PutMapping("/{profesionalId}")
    public ResponseEntity<Object> modifyProfesional(@RequestBody ModificarCrearProfesionalRequest profesionalRequest) throws ProfesionalInexistenteException{
        // Paciente result = pacienteService.modifyPaciente(pacienteRequest.getId(), pacienteRequest.getNombre(), pacienteRequest.getApellido(), pacienteRequest.getMail(), pacienteRequest.getPassword());
        // return ResponseEntity.created(URI.create("/usuarios/" + result.getId())).body(result);
        return null;
    }

    @Operation(summary = "Eliminar un profesional", description = "Elimina un profesional según el ID proporcionado. Si no existe el profesional, lanza una excepción.")
    @DeleteMapping("/{profesionalId}")
    public void deleteProfesional(@RequestBody EliminarProfesionalRequest profesionalRequest) throws ProfesionalInexistenteException{
        // hacer funcion
    }

}
