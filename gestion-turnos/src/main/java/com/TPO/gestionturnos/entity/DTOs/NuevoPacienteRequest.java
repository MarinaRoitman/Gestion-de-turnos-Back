package com.TPO.gestionturnos.entity.DTOs;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NuevoPacienteRequest {
    @NotNull
    private String nombre;
    
    @NotNull
    private String apellido;
    
    @NotNull
    private String mail;
    
    @NotNull
    private String password;
}
