package com.TPO.gestionturnos.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "obra_social")
public class ObraSocial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @OneToMany(mappedBy = "obraSocial", cascade = CascadeType.ALL)
    private List<Afiliacion> afiliaciones;

    @OneToMany(mappedBy = "obraSocial", cascade = CascadeType.ALL)
    private List<Plan> planes;

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

    public List<Afiliacion> getAfiliaciones() {
        return afiliaciones;
    }

    public void setAfiliaciones(List<Afiliacion> afiliaciones) {
        this.afiliaciones = afiliaciones;
    }

    public List<Plan> getPlanes() {
        return planes;
    }

    public void setPlanes(List<Plan> planes) {
        this.planes = planes;
    }
}
