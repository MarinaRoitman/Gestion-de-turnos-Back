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

import com.TPO.gestionturnos.entity.Profesional;
import com.TPO.gestionturnos.entity.DTOs.ModificarProfesionalRequest;
import com.TPO.gestionturnos.entity.DTOs.AgregarEliminarEspecialidadRequest;
import com.TPO.gestionturnos.entity.DTOs.CrearProfesionalRequest;
import com.TPO.gestionturnos.exceptions.EspecialidadInexistenteException;
import com.TPO.gestionturnos.exceptions.ProfesionalExistenteException;
import com.TPO.gestionturnos.exceptions.ProfesionalInexistenteException;
import com.TPO.gestionturnos.service.ProfesionalService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/profesional")
public class profesionalController {
    @Autowired
    private ProfesionalService profesionalService;

    @Operation(summary = "Listar profesionales", description = "Devuelve una lista con todos los profesionales.")
    @GetMapping
    public ResponseEntity<List<Profesional>> getProfesionales() {
        return ResponseEntity.ok(profesionalService.getProfesionales());
    }

    @Operation(summary = "Listar profesionales por id", description = "Devuelve el profesional cuyo id sea el ingresado.")
    @GetMapping("/{profesionalId}")
    public ResponseEntity<Optional<Profesional>> getProfesionalById(@PathVariable Long profesionalId) throws ProfesionalInexistenteException {
        return ResponseEntity.ok(profesionalService.getProfesionalById(profesionalId));
    }

    @Operation(summary = "Listar profesionales por email", description = "Devuelve el profesional cuyo email sea el ingresado.")
    @GetMapping("/mail/{profesionalMail}")
    public ResponseEntity<Optional<Profesional>> getProfesionalByMail(@PathVariable String profesionalMail) throws ProfesionalInexistenteException {
        return ResponseEntity.ok(profesionalService.getProfesionalByMail(profesionalMail));
    }

    @Operation(summary = "Listar profesionales por especialidad", description = "Devuelve los profesionales especializados en al especialidad ingresada. Si la especialidad no existe, lanza una excepción")
    @GetMapping("/especialidad/{especialidadId}")
    public ResponseEntity<Optional<List<Profesional>>> getProfesionalesByEspecialidad(@PathVariable Long especialidadId) throws EspecialidadInexistenteException {
        return ResponseEntity.ok(profesionalService.getProfesionalesByEspecialidad(especialidadId));
    }

    @Operation(summary = "Listar profesionales por nombre", description = "Devuelve los profesionales con el nombre o apellido ingresado.")
    @GetMapping("/nombre/{profesionalNombre}")
    public ResponseEntity<Optional<List<Profesional>>> getProfesionalesByNombre(@PathVariable String profesionalNombre) {
        return ResponseEntity.ok(profesionalService.getProfesionalesByNombre(profesionalNombre));
    }

    @Operation(summary = "Crear un profesional", description = "Crea un nuevo profesional en el sistema con nombre, apellido, mail y nroMatricula. Si el mail o nroMatricula ya existe, lanza una excepción.")
    @PostMapping
    public ResponseEntity<Object> createProfesional(@RequestBody CrearProfesionalRequest profesionalRequest) throws ProfesionalExistenteException {
        Profesional result = profesionalService.createProfesional(
            profesionalRequest.getNombre(),
            profesionalRequest.getApellido(),
            profesionalRequest.getMail(),
            profesionalRequest.getMatricula()
        );
        return ResponseEntity.created(URI.create("/profesional/" + result.getId())).body(result);
    }

    @Operation(summary = "Modificar un profesional", description = "Modifica los datos de un profesional ya registrado (nombre, apellido, mail y/o matricula) según el ID proporcionado. Si no existe el profesional, lanza una excepción.")
    @PutMapping("/{profesionalId}")
    public ResponseEntity<Object> modifyProfesional(@RequestBody ModificarProfesionalRequest profesionalRequest) throws ProfesionalInexistenteException{
        Profesional result = profesionalService.modifyProfesional(
            profesionalRequest.getId(),
            profesionalRequest.getNombre(),
            profesionalRequest.getApellido(),
            profesionalRequest.getMail(),
            profesionalRequest.getMatricula()
        );
        return ResponseEntity.created(URI.create("/profesional/" + result.getId())).body(result);
    }

    @Operation(summary = "Eliminar un profesional", description = "Elimina un profesional según el ID proporcionado. Si no existe el profesional, lanza una excepción.")
    @DeleteMapping("/{profesionalId}")
    public ResponseEntity<Void> deleteProfesional(@PathVariable Long profesionalId) throws ProfesionalInexistenteException{
        profesionalService.deleteProfesional(profesionalId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Agrega una especialidad a un profesional", description = "Agrega la especialidad cuyo ID sea el ingresado al profesional. Si ya la tiene, no hace nada.")
    @PutMapping("agregar")
    public ResponseEntity<Object> addEspecialidad(@RequestBody AgregarEliminarEspecialidadRequest profesionalRequest)
        throws ProfesionalInexistenteException, EspecialidadInexistenteException {
        Profesional result = profesionalService.addEspecialidad(profesionalRequest.getProfesionalId(), profesionalRequest.getEspecialidadId());
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Elimina una especialidad a un profesional", description = "Elimina la especialidad cuyo ID sea el ingresado del profesional. Si no la tiene, no hace nada.")
    @PutMapping("eliminar")
    public ResponseEntity<Object> deleteEspecialidad(@RequestBody AgregarEliminarEspecialidadRequest profesionalRequest)
        throws ProfesionalInexistenteException, EspecialidadInexistenteException {
        Profesional result = profesionalService.deleteEspecialidad(profesionalRequest.getProfesionalId(), profesionalRequest.getEspecialidadId());
        return ResponseEntity.ok(result);
    }
}
