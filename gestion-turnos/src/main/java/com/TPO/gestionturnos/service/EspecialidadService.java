package com.TPO.gestionturnos.service;

import java.util.List;
import java.util.Optional;

import com.TPO.gestionturnos.entity.Especialidad;
import com.TPO.gestionturnos.exceptions.EspecialidadExistenteException;
import com.TPO.gestionturnos.exceptions.EspecialidadInexistenteException;

public interface EspecialidadService {
    public List<Especialidad> getEspecialidades();
    public Optional<Especialidad> getEspecialidadById(Long especialidadId) throws EspecialidadInexistenteException;
    public Especialidad createEspecialidad(String nombre) throws EspecialidadExistenteException;
    public Especialidad modifyEspecialidad(Long id, String nombre) throws EspecialidadInexistenteException;
    public Long deleteEspecialidad(Long id) throws EspecialidadInexistenteException;
}
