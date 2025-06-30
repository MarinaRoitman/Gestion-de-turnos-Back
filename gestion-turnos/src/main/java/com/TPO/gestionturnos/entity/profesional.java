package com.TPO.gestionturnos.entity;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "profesional")
public class Profesional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String mail;
    private String matricula;

    @ManyToMany
    @JoinTable(
    name = "Profesional_Especialidad",
    joinColumns = @JoinColumn(name = "fkProfesional"),
    inverseJoinColumns = @JoinColumn(name = "fkEspecialidad"))
    
    @JsonManagedReference
    private List<Especialidad> especialidades;

    @OneToMany(mappedBy = "profesional", cascade = CascadeType.ALL)
    private List<Turno> turnos;

    @Lob
    @Column(name = "foto")
    private byte[] foto;

    public Profesional() {
    }

    public Profesional(String nombre, String apellido, String matricula, String mail) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
        this.mail = mail;
    }

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

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public List<Especialidad> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<Especialidad> especialidades) {
        this.especialidades = especialidades;
    }

    public List<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(List<Turno> turnos) {
        this.turnos = turnos;
    }

    
    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}
