package com.TPO.gestionturnos.entity;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "especialidad")
public class Especialidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToMany(mappedBy = "especialidades")
    @JsonBackReference
    private List<Profesional> profesionales;

    // Getters y setters
    public Especialidad() {
    }

    public Especialidad(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Profesional> getProfesionales() {
        return profesionales;
    }

    public void setProfesionales(List<Profesional> profesionales) {
        this.profesionales = profesionales;
    }
}
