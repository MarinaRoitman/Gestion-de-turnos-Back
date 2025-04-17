package com.TPO.gestionturnos.entity.DTOs;
import lombok.Data;

@Data
public class NuevoPacienteRequest {
    private String nombre;
    private String apellido;
    private String mail;
    private String password;
}
