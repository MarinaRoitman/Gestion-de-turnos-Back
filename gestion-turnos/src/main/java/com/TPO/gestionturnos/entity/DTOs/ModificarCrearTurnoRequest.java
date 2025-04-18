package com.TPO.gestionturnos.entity.DTOs;
import java.time.LocalDate;
import java.time.LocalTime;

import com.TPO.gestionturnos.entity.Afiliacion;
import com.TPO.gestionturnos.entity.Estado;
import com.TPO.gestionturnos.entity.Paciente;
import com.TPO.gestionturnos.entity.Profesional;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class ModificarCrearTurnoRequest {
    private LocalDate fecha;
    private LocalTime hora;
    private Paciente paciente;
    private Profesional profesional;
    private Estado estado;
    private Afiliacion afiliacion;
    // chequear bien que se necesita acá
}
