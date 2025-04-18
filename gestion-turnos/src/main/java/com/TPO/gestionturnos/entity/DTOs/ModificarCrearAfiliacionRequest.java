package com.TPO.gestionturnos.entity.DTOs;
import java.time.LocalDate;

import com.TPO.gestionturnos.entity.ObraSocial;
import com.TPO.gestionturnos.entity.Paciente;

import lombok.Data;

@Data
public class ModificarCrearAfiliacionRequest {
    private String nroAfiliado;
    private LocalDate fechaAlta;
    private LocalDate fechaFin;
    private Paciente paciente;
    private ObraSocial obraSocial;
}
