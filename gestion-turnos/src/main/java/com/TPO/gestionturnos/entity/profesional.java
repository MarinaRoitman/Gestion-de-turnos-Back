package com.TPO.gestionturnos.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "profesional")
public class Profesional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String matricula;

    @ManyToOne
    @JoinColumn(name = "fk_especialidad")
    private Especialidad especialidad;

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

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public List<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(List<Turno> turnos) {
        this.turnos = turnos;
    }
}
