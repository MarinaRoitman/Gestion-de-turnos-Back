package com.TPO.gestionturnos.entity;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "profesional")
public class Profesional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String matricula;

    @ManyToMany
    @JoinTable(
    name = "Profesional_Especialidad",
    joinColumns = @JoinColumn(name = "fkProfesional"),
    inverseJoinColumns = @JoinColumn(name = "fkEspecialidad"))
    @JsonIgnore
    private List<Especialidad> especialidades;

    @OneToMany(mappedBy = "profesional", cascade = CascadeType.ALL)
    private List<Turno> turnos;

    // Getters y setters

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public List<Especialidad> getEspecialidad() {
        return especialidades;
    }

    public void setEspecialidad(List<Especialidad> especialidades) {
        this.especialidades = especialidades;
    }

    public List<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(List<Turno> turnos) {
        this.turnos = turnos;
    }
}
