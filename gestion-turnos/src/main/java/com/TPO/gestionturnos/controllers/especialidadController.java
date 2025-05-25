package com.TPO.gestionturnos.controllers;
import java.util.List;
import java.util.Optional;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.TPO.gestionturnos.service.EspecialidadService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/especialidad")
public class especialidadController {
    @Autowired
    private EspecialidadService especialidadService;

    @Operation(summary = "Listar especialidades", description = "Devuelve una lista con todas las especialidades.")
    @GetMapping
    public ResponseEntity<List<Especialidad>> getEspecialidades() {
        return ResponseEntity.ok(especialidadService.getEspecialidades());
    }

    @Operation(summary = "Listar especialidades por id", description = "Devuelve la especialidad cuyo id sea el ingresado.")
    @GetMapping("/{especialidadId}")
    public ResponseEntity<Optional<Especialidad>> getEspecialidadById(@PathVariable Long especialidadId) throws EspecialidadInexistenteException {
        return ResponseEntity.ok(especialidadService.getEspecialidadById(especialidadId));
    }

    @Operation(summary = "Crear una especialidad", description = "Crea una nueva especialidad en el sistema con el nombre. Si la especialidad ya existe, lanza una excepción.")
    @PostMapping
    public ResponseEntity<Object> createEspecialidad(@RequestBody CrearEspecialidadRequest especialidadRequest) throws EspecialidadExistenteException {
        Especialidad result = especialidadService.createEspecialidad(especialidadRequest.getNombre());
        return ResponseEntity.created(URI.create("/especialidad/" + result.getId())).body(result);
    }

    @Operation(summary = "Modificar una especialidad", description = "Modifica el nombre de una especialidad según el ID proporcionado. Si no existe la especialidad, lanza una excepción.")
    @PutMapping("/{especialidadId}")
    public ResponseEntity<Object> modifyEspecialidad(@RequestBody ModificarEspecialidadRequest especialidadRequest) throws EspecialidadInexistenteException{
        Especialidad result = especialidadService.modifyEspecialidad(especialidadRequest.getId(), especialidadRequest.getNombre());
        return ResponseEntity.created(URI.create("/especialidad/" + result.getId())).body(result);
    }

    @Operation(summary = "Eliminar una especialidad", description = "Elimina una especialidad según el ID proporcionado. Si no existe la especialidad, lanza una excepción.")
    @DeleteMapping("/{especialidadId}")
    public ResponseEntity<Void> deleteEspecialidad(@RequestBody EliminarEspecialidadRequest especialidadRequest) throws EspecialidadInexistenteException{
        especialidadService.deleteEspecialidad(especialidadRequest.getId());
        return ResponseEntity.noContent().build();
    }
}
