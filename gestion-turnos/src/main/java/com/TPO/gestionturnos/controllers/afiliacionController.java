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

import com.TPO.gestionturnos.entity.Afiliacion;
import com.TPO.gestionturnos.entity.DTOs.EliminarAfiliacionRequest;
import com.TPO.gestionturnos.entity.DTOs.ModificarAfiliacionRequest;
import com.TPO.gestionturnos.entity.DTOs.CrearAfiliacionRequest;
import com.TPO.gestionturnos.exceptions.AfiliacionIncompatibleException;
import com.TPO.gestionturnos.exceptions.AfiliacionInexistenteException;
import com.TPO.gestionturnos.exceptions.AfiliacionNoCreadaException;
import com.TPO.gestionturnos.exceptions.ObraSocialInexistenteException;
import com.TPO.gestionturnos.exceptions.PacienteInexistenteException;
import com.TPO.gestionturnos.service.AfiliacionService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/afiliacion")
public class afiliacionController {
        
    @Autowired
    AfiliacionService afiliacionService;

    @Operation(summary = "Listar afiliaciones", description = "Devuelve una lista con todas las afiliaciones.")
    @GetMapping
    public ResponseEntity<List<Afiliacion>> getAfiliaciones() {
        return ResponseEntity.ok(afiliacionService.getAfiliaciones());
    }

    @Operation(summary = "Listar afiliaciones por id", description = "Devuelve la afiliacion cuyo id sea el ingresado.")
    @GetMapping("/{afiliacionId}")
    public ResponseEntity<Optional<Afiliacion>> getAfiliacionById(@PathVariable Long afiliacionId) throws AfiliacionInexistenteException {
        return ResponseEntity.ok(afiliacionService.getAfiliacionById(afiliacionId));
    }

    @Operation(summary = "Listar afiliaciones por obra social", description = "Devuelve las afiliaciones que estén en una obra social. Si no existe la obra social, lanza una expceción")
    @GetMapping("afiliacionObraSocial/{obraSocialId}")
    public ResponseEntity<Optional<List<Afiliacion>>> getAfiliacioesnByObraSocial(@PathVariable Long obraSocialId) throws ObraSocialInexistenteException {
        return ResponseEntity.ok(afiliacionService.getAfiliacioesnByObraSocial(obraSocialId));
    }

    @Operation(summary = "Listar afiliaciones por paciente", description = "Devuelve las afiliaciones de un paciente. Si no existe el paciente, lanza una expceción")
    @GetMapping("afiliacionPaciente/{pacienteId}")
    public ResponseEntity<Optional<List<Afiliacion>>> getAfiliacionesByPaciente(@PathVariable Long pacienteId) throws PacienteInexistenteException {
        return ResponseEntity.ok(afiliacionService.getAfiliacionesByPaciente(pacienteId));
    }

    @Operation(summary = "Crear una afiliacion", description = "Crea una nueva afiliacion en el sistema con el nroAfiliado, fkObraSocial, fkPaciente, fechaAlta, fechaFin. Si la afiliacion ya existe, lanza una excepción.")
    @PostMapping
    public ResponseEntity<Object> createAfiliacion(@RequestBody CrearAfiliacionRequest afiliacionRequest) throws AfiliacionIncompatibleException, AfiliacionNoCreadaException {
        Afiliacion result = afiliacionService.createAfiliacion(afiliacionRequest.getNroAfiliado(), afiliacionRequest.getFechaAlta(), afiliacionRequest.getFechaFin(), afiliacionRequest.getIdPaciente(), afiliacionRequest.getIdObraSocial());
        return ResponseEntity.created(URI.create("/afiliacion/" + result.getId())).body(result);
    }

    // hacer que este metodo sea privado, hacer void?
    @Operation(summary = "Modificar una afiliacion", description = "Modifica la afiliacion según el ID proporcionado. Si no existe la afiliacion, lanza una excepción.")
    @PutMapping("/{afiliacionId}")
    public ResponseEntity<Object> modifyAfiliacion(@RequestBody ModificarAfiliacionRequest afiliacionRequest) throws AfiliacionInexistenteException, ObraSocialInexistenteException{
        Afiliacion result = afiliacionService.modifyAfiliacion(afiliacionRequest.getId(), afiliacionRequest.getNroAfiliado(), afiliacionRequest.getFechaAlta(), afiliacionRequest.getFechaFin(), afiliacionRequest.getIdObraSocial());
        return ResponseEntity.created(URI.create("/plan/" + result.getId())).body(result);
    }

    @Operation(summary = "Eliminar una afiliacion", description = "Elimina una afiliacion según el ID proporcionado. Si no existe la afiliacion, lanza una excepción.")
    @DeleteMapping("/{afiliacionId}")
    public ResponseEntity<Void> deleteAfiliacion(@RequestBody EliminarAfiliacionRequest afiliacionRequest) throws AfiliacionInexistenteException{
        afiliacionService.deleteAfiliacion(afiliacionRequest.getId());
        return ResponseEntity.noContent().build();
    }
}
