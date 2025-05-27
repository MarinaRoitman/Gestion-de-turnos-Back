package com.TPO.gestionturnos.controllers;

import com.TPO.gestionturnos.entity.Imagen;
import com.TPO.gestionturnos.entity.Notificacion;
import com.TPO.gestionturnos.entity.DTOs.EliminarImagenRequest;
import com.TPO.gestionturnos.entity.DTOs.ModificarImagenRequest;
import com.TPO.gestionturnos.entity.DTOs.CrearImagenRequest;
import com.TPO.gestionturnos.exceptions.ImagenInexistenteException;
import com.TPO.gestionturnos.exceptions.TurnoInexistenteException;
import com.TPO.gestionturnos.service.ImagenService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/imagen")
public class imagenController {

    @Autowired
    private ImagenService imagenService;

    @Operation(summary = "Listar imágenes", description = "Devuelve una lista con todas las imágenes.")
    @GetMapping
    public ResponseEntity<List<Imagen>> getImagenes() {
        return ResponseEntity.ok(imagenService.getImagenes());
    }

    @Operation(summary = "Obtener imagen por ID", description = "Devuelve la imagen con el ID proporcionado.")
    @GetMapping("/{imagenId}")
    public ResponseEntity<Optional<Imagen>> getImagenById(@PathVariable Long imagenId) throws ImagenInexistenteException {
        return ResponseEntity.ok(imagenService.getImagenById(imagenId));
    }

    @Operation(summary = "Listar imágenes por turno", description = "Devuelve las imágenes asociadas a un turno.")
    @GetMapping("/turno/{turnoId}")
    public ResponseEntity<Optional<List<Imagen>>> getImagenesByTurno(@PathVariable Long turnoId) throws TurnoInexistenteException {
        return ResponseEntity.ok(imagenService.getImagenesByTurno(turnoId));
    }

    @Operation(summary = "Crear imagen", description = "Crea una nueva imagen.")
    @PostMapping
    public ResponseEntity<Object> createImagen(@RequestBody CrearImagenRequest imagenRequest) throws TurnoInexistenteException {
        Imagen result = imagenService.createImagen(imagenRequest.getIdTurno(), imagenRequest.getTitulo(), imagenRequest.getImagen());
        return ResponseEntity.created(URI.create("/imagenes/" + result.getId())).body(result);
    }

    @Operation(summary = "Modificar imagen", description = "Modifica una imagen existente.")
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateImagen(@RequestBody ModificarImagenRequest imagenRequest) throws ImagenInexistenteException {
        Imagen result = imagenService.updateImagen(imagenRequest.getId(), imagenRequest.getTitulo(), imagenRequest.getImagen());
        return ResponseEntity.created(URI.create("/imagenes/" + result.getId())).body(result);
    }

    @Operation(summary = "Eliminar imagen", description = "Elimina una imagen según su ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteImagen(@PathVariable EliminarImagenRequest imagenRequest) throws ImagenInexistenteException {
        imagenService.deleteImagen(imagenRequest.getId());
        return ResponseEntity.noContent().build();
    }    
}
