package com.TPO.gestionturnos.entity.DTOs;
import java.time.LocalDate;


import lombok.Data;

@Data
public class ModificarAfiliacionRequest {
    private Long id;
    private String nroAfiliado;
    private LocalDate fechaAlta;
    private LocalDate fechaFin;
    private Long idObraSocial;
}
