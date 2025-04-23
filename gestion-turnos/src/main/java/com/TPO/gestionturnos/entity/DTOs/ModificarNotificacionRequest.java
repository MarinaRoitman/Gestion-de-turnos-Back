package com.TPO.gestionturnos.entity.DTOs;
import java.time.LocalDate;
import java.time.LocalTime;

import com.TPO.gestionturnos.entity.Paciente;
import com.TPO.gestionturnos.entity.Turno;

import lombok.Data;

@Data
public class ModificarNotificacionRequest {
    private String texto;
    private Turno turno;
    private Paciente paciente;
    private LocalDate fechaEnvio;
    private LocalTime horaEnvio;
    private boolean visible;
}
