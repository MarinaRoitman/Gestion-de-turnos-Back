package com.TPO.gestionturnos.entity.DTOs;
import java.util.List;

import com.TPO.gestionturnos.entity.Especialidad;
import com.TPO.gestionturnos.entity.Turno;

import lombok.Data;

@Data
public class ModificarProfesionalRequest {
    private Long id;
    private String nombre;
    private String apellido;
    private String mail;
    private String matricula;
    private Especialidad especialidad; // A chequear
    private List<Turno> turnos; // A chequear
}
