package com.TPO.gestionturnos.entity.DTOs;
import com.TPO.gestionturnos.entity.Turno;

import lombok.Data;

@Data
public class CrearImagenRequest {
    private Long idTurno;
    private String titulo;
    private byte[] imagen;
}
