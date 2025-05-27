package com.TPO.gestionturnos.entity.DTOs;
import com.TPO.gestionturnos.entity.Turno;

import lombok.Data;

@Data
public class ModificarImagenRequest {
    private Long id;
    private String titulo;
    private byte[] imagen;
}
