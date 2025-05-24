package com.TPO.gestionturnos.entity.DTOs;
import com.TPO.gestionturnos.entity.ObraSocial;

import lombok.Data;

@Data
public class CrearPlanRequest {
    private String nombre;
    private Long idObraSocial;
}
