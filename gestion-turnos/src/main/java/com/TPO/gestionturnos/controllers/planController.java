package com.TPO.gestionturnos.controllers;
import java.net.URI;
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

import com.TPO.gestionturnos.entity.ObraSocial;
import com.TPO.gestionturnos.entity.Plan;
import com.TPO.gestionturnos.entity.DTOs.EliminarPlanRequest;
import com.TPO.gestionturnos.entity.DTOs.ModificarPlanRequest;
import com.TPO.gestionturnos.entity.DTOs.CrearPlanRequest;
import com.TPO.gestionturnos.exceptions.ObraSocialInexistenteException;
import com.TPO.gestionturnos.exceptions.PlanExistenteException;
import com.TPO.gestionturnos.exceptions.PlanInexistenteException;
import com.TPO.gestionturnos.service.PlanService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/plan")
public class planController {
    @Autowired
    PlanService planService;

    @Operation(summary = "Listar planes", description = "Devuelve una lista con todos los planes.")
    @GetMapping
    public ResponseEntity<List<Plan>> getPlanes() {
        return ResponseEntity.ok(planService.getPlanes());
    }

    @Operation(summary = "Listar planes por id", description = "Devuelve el plan cuyo id sea el ingresado.")
    @GetMapping("/{planId}")
    public ResponseEntity<Optional<Plan>> getPlanById(@PathVariable Long planId) throws PlanInexistenteException {
        return ResponseEntity.ok(planService.getPlanById(planId));
    }

    @Operation(summary = "Listar planes por obra social", description = "Devuelve los planes de una obra social. Si la obra social no existe, lanza una expceción.")
    @GetMapping("planSearch/{obraSocialId}")
    public ResponseEntity<List<Plan>> getPlanesByObraSocial(@PathVariable Long obraSocialId) throws ObraSocialInexistenteException {
        return ResponseEntity.ok(planService.getPlanesByObraSocial(obraSocialId));
    }

    @Operation(summary = "Crear un plan", description = "Crea un nuevo plan en el sistema con el nombre y la fkObraSocial. Si le plan ya existe, lanza una excepción.")
    @PostMapping
    public ResponseEntity<Object> createPlan(@RequestBody CrearPlanRequest planRequest) throws PlanExistenteException {
        Plan result = planService.createPlan(planRequest.getNombre(), planRequest.getIdObraSocial());
        return ResponseEntity.created(URI.create("/plan/" + result.getId())).body(result);
    }

    // hacer que este metodo sea privado, hacer void?
    @Operation(summary = "Modificar un plan", description = "Modifica el nombre y/o la obra social de un plan según el ID proporcionado. Si no existe el plan, lanza una excepción.")
    @PutMapping("/{planId}")
    public ResponseEntity<Object> modifyPlan(@RequestBody ModificarPlanRequest planRequest) throws PlanInexistenteException{
        Plan result = planService.modifyPlan(planRequest.getId(), planRequest.getNombre());
        return ResponseEntity.created(URI.create("/plan/" + result.getId())).body(result);
    }

    @Operation(summary = "Eliminar un plan", description = "Elimina un plan según el ID proporcionado. Si no existe el plan, lanza una excepción.")
    @DeleteMapping("/{planId}")
    public ResponseEntity<Void> deletePlan(@RequestBody EliminarPlanRequest planRequest) throws PlanInexistenteException{
        planService.deletePlan(planRequest.getId());
        return ResponseEntity.noContent().build();
    }
}
