package com.TPO.gestionturnos.entity.DTOs;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import lombok.Data;

@Data
public class ModificarTurnoRequest {
    private Long id;
    private LocalDate fecha;
    private LocalTime hora;
    private Long idPaciente;
    private Long idProfesional;
    private Long idEstado;
    private List<Long> idImagenes;
}
