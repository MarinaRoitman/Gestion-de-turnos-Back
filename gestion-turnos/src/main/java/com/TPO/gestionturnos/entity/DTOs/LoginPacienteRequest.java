package com.TPO.gestionturnos.entity.DTOs;

import lombok.Data;

@Data
public class LoginPacienteRequest {
    private String mail;
    private String password;
}
