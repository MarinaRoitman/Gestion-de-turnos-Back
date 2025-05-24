package com.TPO.gestionturnos.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "afiliacion")
public class Afiliacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nroAfiliado;
    private LocalDate fechaAlta;
    private LocalDate fechaFin;

    @ManyToOne
    @JoinColumn(name = "fkPaciente")
    @JsonBackReference
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "fkObraSocial")
    @JsonBackReference
    private ObraSocial obraSocial;

    public Afiliacion(){
        
    }
    // Getters y setters

    public Afiliacion(String nroAfiliado, LocalDate fechaAlta, LocalDate fechaFin, Paciente paciente,
            ObraSocial obraSocial) {
        this.nroAfiliado = nroAfiliado;
        this.fechaAlta = fechaAlta;
        this.fechaFin = fechaFin;
        this.paciente = paciente;
        this.obraSocial = obraSocial;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNroAfiliado() {
        return nroAfiliado;
    }

    public void setNroAfiliado(String nroAfiliado) {
        this.nroAfiliado = nroAfiliado;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public ObraSocial getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(ObraSocial obraSocial) {
        this.obraSocial = obraSocial;
    }
}