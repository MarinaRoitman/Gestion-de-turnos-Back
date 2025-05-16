package com.TPO.gestionturnos.entity.DTOs;

public class TestDTO {
    private String nombre;

    public TestDTO() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "TestDTO{nombre='" + nombre + "'}";
    }
}
