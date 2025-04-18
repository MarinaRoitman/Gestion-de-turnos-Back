package com.TPO.gestionturnos.entity.DTOs;
import com.TPO.gestionturnos.entity.ObraSocial;

import lombok.Data;

@Data
public class ModificarCrearPlanRequest {
    private String nombre;
    private ObraSocial obraSocial;
}
