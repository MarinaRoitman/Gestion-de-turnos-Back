package com.TPO.gestionturnos.entity.DTOs;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.TPO.gestionturnos.entity.Afiliacion;
import com.TPO.gestionturnos.entity.Estado;
import com.TPO.gestionturnos.entity.Imagen;
import com.TPO.gestionturnos.entity.Paciente;
import com.TPO.gestionturnos.entity.Profesional;

import lombok.Data;

@Data
public class ModificarCrearTurnoRequest {
    private LocalDate fecha;
    private LocalTime hora;
    private Paciente paciente;
    private Profesional profesional;
    private Estado estado;
    private Afiliacion afiliacion;
    private List<Imagen> imagenes;
    // chequear bien que se necesita acá
}
