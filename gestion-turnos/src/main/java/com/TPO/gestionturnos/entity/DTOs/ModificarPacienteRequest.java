package com.TPO.gestionturnos.entity.DTOs;
import java.time.LocalDate;

import lombok.Data;

@Data
public class ModificarPacienteRequest {
    private Long id;
    private String nombre;
    private String apellido;
    private String mail;
    private String password;
    private String dni;
    private LocalDate fechaNacimiento;
    private String telefono;
}