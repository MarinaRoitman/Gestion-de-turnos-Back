package com.TPO.gestionturnos.entity.DTOs;

import lombok.Data;

@Data
public class NuevaNotificacionRequest {
    private String texto;
    private Long idTurno;
    private Long idPaciente;
    private String titulo;
}
