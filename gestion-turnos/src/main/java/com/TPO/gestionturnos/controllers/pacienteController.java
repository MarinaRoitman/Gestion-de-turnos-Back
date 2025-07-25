package com.TPO.gestionturnos.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.TPO.gestionturnos.entity.Paciente;
import com.TPO.gestionturnos.entity.DTOs.EliminarPacienteRequest;
import com.TPO.gestionturnos.entity.DTOs.EmailRequest;
import com.TPO.gestionturnos.entity.DTOs.LoginPacienteRequest;
import com.TPO.gestionturnos.entity.DTOs.ModificarPacienteRequest;
import com.TPO.gestionturnos.entity.DTOs.ModificarPasswordRequest;
import com.TPO.gestionturnos.entity.DTOs.NuevoPacienteRequest;
import com.TPO.gestionturnos.exceptions.PacienteExistenteException;
import com.TPO.gestionturnos.exceptions.PacienteInexistenteException;
import com.TPO.gestionturnos.exceptions.PacienteLoginNoExitosoException;
import com.TPO.gestionturnos.service.PacienteService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/pacientes")
public class pacienteController {

    @Autowired
    private PacienteService pacienteService;

    @Operation(summary = "Listar pacientes", description = "Devuelve una lista con todos los pacientes.")
    @GetMapping
    public ResponseEntity<List<Paciente>> getPacientes() {
        return ResponseEntity.ok(pacienteService.getPacientes());
    }

    @Operation(summary = "Listar pacientes por id", description = "Devuelve el paciente cuyo id sea el ingresado.")
    @GetMapping("/{pacienteId}")
    public ResponseEntity<Paciente> getPacienteById(@PathVariable Long pacienteId) throws PacienteInexistenteException {
        Optional<Paciente> result = pacienteService.getPacienteById(pacienteId);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Listar pacientes por email", description = "Devuelve el paciente cuyo email sea el ingresado.")
    @GetMapping("/mail/{pacienteMail}")
    public ResponseEntity<Paciente> getPacienteByMail(@PathVariable String pacienteMail) throws PacienteInexistenteException {
        Optional<Paciente> result = pacienteService.getPacienteByMail(pacienteMail);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Crear un paciente", description = "Crea un nuevo paciente en el sistema con nombre, apellido, mail y contraseña. Si el mail ya existe, lanza una excepción.")
    @PostMapping
    public ResponseEntity<Object> createPaciente(
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del nuevo paciente a crear")
    @RequestBody NuevoPacienteRequest pacienteRequest)
    throws PacienteExistenteException {
        Paciente result = pacienteService.createPaciente(pacienteRequest.getNombre(), pacienteRequest.getApellido(), pacienteRequest.getMail(), pacienteRequest.getPassword(), pacienteRequest.getDni(), pacienteRequest.getFechaNacimiento(), pacienteRequest.getTelefono());
        return ResponseEntity.created(URI.create("/paciente/" + result.getId())).body(result);
    }

    @Operation(summary = "Checkea que el email y contraseña matcheen", description = "Verifica si el mail y la contraseña proporcionados corresponden a un paciente registrado. Si son válidos, devuelve el ID del paciente.")
    @PostMapping("/login")
    public ResponseEntity<Object> loginPaciente(
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Mail y password del paciente iniciando sesion")
    @RequestBody LoginPacienteRequest pacienteRequest)
        throws PacienteLoginNoExitosoException {
        Long result = pacienteService.loginPaciente(pacienteRequest.getMail(), pacienteRequest.getPassword());
        return ResponseEntity.created(URI.create("/usuarios/login/" + result)).body(result);
    }
    
    // hacer que este metodo sea privado
    @Operation(summary = "Modificar un paciente", description = "Modifica los datos de un paciente ya registrado (nombre, apellido, mail y/o contraseña) según el ID proporcionado. Si no existe el paciente, lanza una excepción.")
    @PutMapping("/{pacienteId}")
    public ResponseEntity<Object> modifyPaciente(
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Mail y password del paciente iniciando sesion")
    @RequestBody ModificarPacienteRequest pacienteRequest) throws PacienteInexistenteException{
        Paciente result = pacienteService.modifyPaciente(pacienteRequest.getId(), pacienteRequest.getNombre(), pacienteRequest.getApellido(), pacienteRequest.getMail(), pacienteRequest.getPassword(), pacienteRequest.getDni(), pacienteRequest.getFechaNacimiento(), pacienteRequest.getTelefono());
        return ResponseEntity.created(URI.create("/usuarios/" + result.getId())).body(result);
    }
    
    @Operation(summary = "Recuperar contraseña de un paciente", description = "Recibe el mail del usuario y lanza  una exceción si el mail no está registrado.")
    @PutMapping("recupero/{pacienteMail}")
    public ResponseEntity<Object> recoverPassword(@RequestBody ModificarPasswordRequest passwordRequest) throws PacienteInexistenteException{
        // hacer funcion despues :)
        Paciente result = pacienteService.recoverPassword(passwordRequest.getMail());
        return ResponseEntity.created(URI.create("/usuarios/" + result.getId())).body(result);
    }
    
    @Operation(summary = "Eliminar un paciente", description = "Elimina un paciente según el ID proporcionado. Si no existe el paciente, lanza una excepción.")
    @DeleteMapping("/{pacienteId}")
    public ResponseEntity<Void> deletePaciente(@RequestBody EliminarPacienteRequest pacienteRequest) throws PacienteInexistenteException{
        pacienteService.deletePaciente(pacienteRequest.getId());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/enviar-correo")
    public ResponseEntity<String> enviarCorreo(@RequestBody EmailRequest request) throws Exception {
        try {
            pacienteService.sendSimpleMessage(request.getTo(), request.getSubject(), request.getText());
            return ResponseEntity.ok("Correo enviado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al enviar correo");
        }
    }
}