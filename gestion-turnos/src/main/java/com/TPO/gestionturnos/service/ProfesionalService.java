package com.TPO.gestionturnos.service;

import java.util.List;
import java.util.Optional;

import com.TPO.gestionturnos.entity.Profesional;
import com.TPO.gestionturnos.exceptions.EspecialidadInexistenteException;
import com.TPO.gestionturnos.exceptions.ProfesionalExistenteException;
import com.TPO.gestionturnos.exceptions.ProfesionalInexistenteException;

public interface ProfesionalService {
    public List<Profesional> getProfesionales();

    public Optional<Profesional> getProfesionalById(Long profesionalId) throws ProfesionalInexistenteException;

    public Optional<Profesional> getProfesionalByMail(String profesionalMail) throws ProfesionalInexistenteException;

    public Optional<List<Profesional>> getProfesionalesByEspecialidad(Long especialidadId) throws EspecialidadInexistenteException;

    public Optional<List<Profesional>> getProfesionalesByNombre(String profesionalNombre);

    public Profesional createProfesional(String nombre, String apellido, String mail, String matricula, byte[] foto) throws ProfesionalExistenteException;

    public Profesional modifyProfesional(Long id, String nombre, String apellido, String mail, String matricula, byte[] foto) throws ProfesionalInexistenteException;

    public void deleteProfesional(Long id) throws ProfesionalInexistenteException;

    public Profesional deleteEspecialidad(Long profesionalId, Long especialidadId) throws ProfesionalInexistenteException, EspecialidadInexistenteException;

    public Profesional addEspecialidad(Long profesionalId, Long especialidadId) throws ProfesionalInexistenteException, EspecialidadInexistenteException;
}
