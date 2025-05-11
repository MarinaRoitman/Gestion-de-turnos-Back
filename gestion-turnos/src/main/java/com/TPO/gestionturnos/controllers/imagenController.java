package com.TPO.gestionturnos.controllers;

import com.TPO.gestionturnos.entity.Imagen;
import com.TPO.gestionturnos.entity.DTOs.EliminarImagenRequest;
import com.TPO.gestionturnos.entity.DTOs.ModificarCrearImagenRequest;
import com.TPO.gestionturnos.exceptions.ImagenInexistenteException;
import com.TPO.gestionturnos.exceptions.TurnoInexistenteException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/imagen")
public class imagenController {

    // @Autowired
    // private ImagenService imagenService;

    @Operation(summary = "Listar imágenes", description = "Devuelve una lista con todas las imágenes.")
    @GetMapping
    public ResponseEntity<List<Imagen>> getImagenes() {
        //return ResponseEntity.ok(imagenService.getAll());
        return null;
    }

    @Operation(summary = "Obtener imagen por ID", description = "Devuelve la imagen con el ID proporcionado.")
    @GetMapping("/{imagenId}")
    public ResponseEntity<Imagen> getImagenById(@PathVariable Long imagenId) throws ImagenInexistenteException {
        // return ResponseEntity.of(imagenService.getById(id));
        return null;
    }

    @Operation(summary = "Listar imágenes por turno", description = "Devuelve las imágenes asociadas a un turno.")
    @GetMapping("/turno/{turnoId}")
    public ResponseEntity<List<Imagen>> getImagenesByTurno(@PathVariable Long turnoId) throws TurnoInexistenteException {
        //return ResponseEntity.ok(imagenService.getByTurnoId(turnoId));
        return null;
    }

    @Operation(summary = "Crear imagen", description = "Crea una nueva imagen.")
    @PostMapping
    public ResponseEntity<Object> createImagen(@RequestBody ModificarCrearImagenRequest imagenRequest) {
        // Imagen created = imagenService.create(imagen);
        // return ResponseEntity.created(URI.create("/imagen/" + created.getId())).body(created);
        return null;
    }

    @Operation(summary = "Eliminar imagen", description = "Elimina una imagen según su ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteImagen(@PathVariable EliminarImagenRequest imagenRequest) throws ImagenInexistenteException {
        // imagenService.delete(id);
        // return ResponseEntity.noContent().build();
        return null;
    }

    @Operation(summary = "Modificar imagen", description = "Modifica una imagen existente.")
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateImagen(@RequestBody ModificarCrearImagenRequest imagenRequest) throws ImagenInexistenteException {
        // Imagen updated = imagenService.update(id, imagen);
        // return ResponseEntity.ok(updated);
        return null;
    }
}
