package com.TPO.gestionturnos.entity.DTOs;

import lombok.Data;

@Data
public class ModificarProfesionalRequest {
    private Long id;
    private String nombre;
    private String apellido;
    private String mail;
    private String matricula;
}
