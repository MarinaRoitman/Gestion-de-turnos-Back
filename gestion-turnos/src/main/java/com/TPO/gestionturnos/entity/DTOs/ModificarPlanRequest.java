package com.TPO.gestionturnos.entity.DTOs;
import com.TPO.gestionturnos.entity.ObraSocial;

import lombok.Data;

@Data
public class ModificarPlanRequest {
    private Long id;
    private String nombre;
}
