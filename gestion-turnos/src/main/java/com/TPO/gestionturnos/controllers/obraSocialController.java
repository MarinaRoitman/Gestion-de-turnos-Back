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
import com.TPO.gestionturnos.entity.DTOs.EliminarObraSocialRequest;
import com.TPO.gestionturnos.entity.DTOs.ModificarObraSocialRequest;
import com.TPO.gestionturnos.entity.DTOs.CrearObraSocialRequest;
import com.TPO.gestionturnos.exceptions.ObraSocialExistenteException;
import com.TPO.gestionturnos.exceptions.ObraSocialInexistenteException;
import com.TPO.gestionturnos.service.ObraSocialService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/obraSocial")
public class obraSocialController {
    @Autowired
    private ObraSocialService obraSocialService;

    @Operation(summary = "Listar obras sociales", description = "Devuelve una lista con todas las obras sociales.")
    @GetMapping
    public ResponseEntity<List<ObraSocial>> getObrasSociales() {
        return ResponseEntity.ok(obraSocialService.getObrasSociales());
    }

    @Operation(summary = "Listar obras sociales por id", description = "Devuelve la obra social cuyo id sea el ingresado.")
    @GetMapping("/{obraSocialId}")
    public ResponseEntity<ObraSocial> getObraSocialById(@PathVariable Long obraSocialId) throws ObraSocialInexistenteException {
        Optional<ObraSocial> result = obraSocialService.getObraSocialById(obraSocialId);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Listar obras sociales por nombre", description = "Devuelve la obra social cuyo nombre sea el ingresado.")
    @GetMapping("nombre/{obraSocialNombre}")
    public ResponseEntity<ObraSocial> getObraSocialByNombre(@PathVariable String obraSocialNombre) throws ObraSocialInexistenteException {
        Optional<ObraSocial> result = obraSocialService.getObraSocialByNombre(obraSocialNombre);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Crear una orba social", description = "Crea una nueva obra social en el sistema con el nombre. Si la obra social ya existe, lanza una excepción.")
    @PostMapping
    public ResponseEntity<Object> createObraSocial(@RequestBody CrearObraSocialRequest obraSocialRequest) throws ObraSocialExistenteException {
        ObraSocial result = obraSocialService.createObraSocial(obraSocialRequest.getNombre());
        return ResponseEntity.created(URI.create("/obraSocial/" + result.getId())).body(result);
    }

    // hacer que este metodo sea privado, hacer void?
    @Operation(summary = "Modificar una obra social", description = "Modifica el nombre de una obra social según el ID proporcionado. Si no existe la obra social, lanza una excepción.")
    @PutMapping("/{obraSocialId}")
    public ResponseEntity<Object> modifyObraSocial(@RequestBody ModificarObraSocialRequest obraSocialRequest) throws ObraSocialInexistenteException{
        ObraSocial result = obraSocialService.modifyObraSocial(obraSocialRequest.getId(), obraSocialRequest.getNombre());
        return ResponseEntity.created(URI.create("/usuarios/" + result.getId())).body(result);
    }

    @Operation(summary = "Eliminar una obra social", description = "Elimina una obra social según el ID proporcionado. Si no existe la obra social, lanza una excepción.")
    @DeleteMapping("/{obraSocialId}")
    public ResponseEntity<Void> deleteObraSocial(@RequestBody EliminarObraSocialRequest obraSocialRequest) throws ObraSocialInexistenteException{
        obraSocialService.deleteObraSocial(obraSocialRequest.getId());
        return ResponseEntity.noContent().build();
    }

}
