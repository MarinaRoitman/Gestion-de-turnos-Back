package com.TPO.gestionturnos.entity.DTOs;
import java.time.LocalDate;

import lombok.Data;

@Data
public class CrearAfiliacionRequest {
    private String nroAfiliado;
    private LocalDate fechaAlta;
    private LocalDate fechaFin;
    private Long idPaciente;
    private Long idObraSocial;
    private Long idPlan;
}
