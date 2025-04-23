package com.TPO.gestionturnos.entity.DTOs;

import com.TPO.gestionturnos.entity.Paciente;
import com.TPO.gestionturnos.entity.Turno;

import lombok.Data;

@Data
public class NuevaNotificacionRequest {
    private String texto;
    private Turno turno;
    private Paciente paciente;
    private boolean visible;
}
