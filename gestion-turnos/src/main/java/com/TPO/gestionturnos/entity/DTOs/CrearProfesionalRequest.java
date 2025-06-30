package com.TPO.gestionturnos.entity.DTOs;

import lombok.Data;

@Data
public class CrearProfesionalRequest {
    private String nombre;
    private String apellido;
    private String mail;
    private String matricula;
    private String fotoBase64;
}
