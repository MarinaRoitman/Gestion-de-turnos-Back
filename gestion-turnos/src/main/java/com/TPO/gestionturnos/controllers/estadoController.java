package com.TPO.gestionturnos.controllers;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TPO.gestionturnos.entity.Estado;
import com.TPO.gestionturnos.exceptions.EstadoInexistenteException;
import com.TPO.gestionturnos.service.EstadoService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/estado")
public class estadoController {
    @Autowired
    private EstadoService estadoService;

    @Operation(summary = "Listar estados", description = "Devuelve una lista con todos los estados posibles.")
    @GetMapping
    public ResponseEntity<List<Estado>> getEstados() {
        return ResponseEntity.ok(estadoService.getEstados());
    }

    @Operation(summary = "Listar estados por id", description = "Devuelve el estado cuyo id sea el ingresado. Si no existe, lanza una excepcion")
    @GetMapping("/{estadoId}")
    public ResponseEntity<Optional<Estado>> getEstadoById(@PathVariable Long estadoId) throws EstadoInexistenteException {
        return ResponseEntity.ok(estadoService.getEstadoById(estadoId));
    }

    // el resto de metodos no deben existir con la base de datos actual, ya que tiene valores insertados y enumerados
}
