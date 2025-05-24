package com.TPO.gestionturnos.entity;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "plan")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "fkObraSocial")
    @JsonBackReference
    private ObraSocial obraSocial;

    public Plan(){

    }
    // Getters y setters

    public Plan(String nombre2, Long idObraSocial) {
        //TODO Auto-generated constructor stub
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

    public ObraSocial getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(ObraSocial obraSocial) {
        this.obraSocial = obraSocial;
    }
}
