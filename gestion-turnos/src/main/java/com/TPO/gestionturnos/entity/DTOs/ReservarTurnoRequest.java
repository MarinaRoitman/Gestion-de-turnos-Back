package com.TPO.gestionturnos.entity.DTOs;
import lombok.Data;

@Data
public class ReservarTurnoRequest {
    private Long idTurno;
    private Long idPaciente;
}
