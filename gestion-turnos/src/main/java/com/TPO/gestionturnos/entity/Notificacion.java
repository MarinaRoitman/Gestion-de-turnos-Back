package com.TPO.gestionturnos.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "notificacion")
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String texto;

    @ManyToOne
    @JoinColumn(name = "fkTurno", nullable = false)
    private Turno turno;

    @ManyToOne
    @JoinColumn(name = "fkPaciente", nullable = false)
    private Paciente paciente;

    @Column(nullable = false)
    private LocalDate fechaEnvio;

    @Column(nullable = false)
    private LocalTime horaEnvio;

    @Column(nullable = false)
    private boolean visible;

    public Notificacion() {
        this.fechaEnvio = LocalDate.now();
        this.horaEnvio = LocalTime.now();
        this.visible = true;
    }

    public Notificacion(String texto, Turno turno, Paciente paciente) {
        this.texto = texto;
        this.turno = turno;
        this.paciente = paciente;
        this.fechaEnvio = LocalDate.now();
        this.horaEnvio = LocalTime.now();
        this.visible = true;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDate getFechaEnvio() {
        return fechaEnvio;
    }

    public LocalTime getHoraEnvio() {
        return horaEnvio;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
