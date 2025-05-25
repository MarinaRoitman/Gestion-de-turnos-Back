package com.TPO.gestionturnos.entity.DTOs;

import lombok.Data;

@Data
public class AgregarEliminarEspecialidadRequest {
    private Long profesionalId;
    private Long especialidadId;
}
