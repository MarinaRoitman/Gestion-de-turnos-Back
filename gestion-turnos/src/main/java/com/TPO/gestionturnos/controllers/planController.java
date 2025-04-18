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

import com.TPO.gestionturnos.entity.Plan;
import com.TPO.gestionturnos.entity.DTOs.EliminarPlanRequest;
import com.TPO.gestionturnos.entity.DTOs.ModificarCrearPlanRequest;
import com.TPO.gestionturnos.exceptions.ObraSocialInexistenteException;
import com.TPO.gestionturnos.exceptions.PlanExistenteException;
import com.TPO.gestionturnos.exceptions.PlanInexistenteException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/plan")
public class planController {
    //espacio para el service

    @Operation(summary = "Listar planes", description = "Devuelve una lista con todos los planes.")
    @GetMapping
    public ResponseEntity<List<Plan>> getPlanes() {
        // return ResponseEntity.ok(pacienteService.getPacientes());
        return null;
    }

    @Operation(summary = "Listar planes por id", description = "Devuelve el plan cuyo id sea el ingresado.")
    @GetMapping("/{planId}")
    public ResponseEntity<Plan> getPlanById(@PathVariable Long planId) throws PlanInexistenteException {
        // Optional<Paciente> result = pacienteService.getPacienteById(especialidadId);
        // if (result.isPresent())
        //     return ResponseEntity.ok(result.get());
        // return ResponseEntity.noContent().build();
        return null;
    }

    @Operation(summary = "Listar planes por obra social", description = "Devuelve los planes de una obra social. Si la obra social no existe, lanza una expceción.")
    @GetMapping("plan_search/{obraSocialId}")
    public ResponseEntity<List<Plan>> getPlanesByObraSocial(@PathVariable Long obraSocialId) throws ObraSocialInexistenteException {
        // Optional<Paciente> result = pacienteService.getPacienteById(especialidadId);
        // if (result.isPresent())
        //     return ResponseEntity.ok(result.get());
        // return ResponseEntity.noContent().build();
        return null;
    }

    @Operation(summary = "Crear un plan", description = "Crea un nuevo plan en el sistema con el nombre y la fkObraSocial. Si le plan ya existe, lanza una excepción.")
    @PostMapping
    public ResponseEntity<Object> createPlan(@RequestBody ModificarCrearPlanRequest planRequest) throws PlanExistenteException {
        // Paciente result = pacienteService.createPaciente(pacienteRequest.getNombre(), pacienteRequest.getApellido(), pacienteRequest.getMail(), pacienteRequest.getPassword());
        // return ResponseEntity.created(URI.create("/paciente/" + result.getId())).body(result);
        return null;
    }

    // hacer que este metodo sea privado, hacer void?
    @Operation(summary = "Modificar un plan", description = "Modifica el nombre y/o la obra social de un plan según el ID proporcionado. Si no existe el plan, lanza una excepción.")
    @PutMapping("/{planId}")
    public ResponseEntity<Object> modifyPlan(@RequestBody ModificarCrearPlanRequest planRequest) throws PlanInexistenteException{
        // Paciente result = pacienteService.modifyPaciente(pacienteRequest.getId(), pacienteRequest.getNombre(), pacienteRequest.getApellido(), pacienteRequest.getMail(), pacienteRequest.getPassword());
        // return ResponseEntity.created(URI.create("/usuarios/" + result.getId())).body(result);
        return null;
    }

    @Operation(summary = "Eliminar un plan", description = "Elimina un plan según el ID proporcionado. Si no existe el plan, lanza una excepción.")
    @DeleteMapping("/{planId}")
    public void deletePlan(@RequestBody EliminarPlanRequest planRequest) throws PlanInexistenteException{
        // hacer funcion
    }
}
