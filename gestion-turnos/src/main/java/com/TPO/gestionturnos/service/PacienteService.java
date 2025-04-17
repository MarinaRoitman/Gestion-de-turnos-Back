package com.TPO.gestionturnos.service;

import java.util.List;
import java.util.Optional;

import com.TPO.gestionturnos.entity.Paciente;
import com.TPO.gestionturnos.exceptions.PacienteExistenteException;
import com.TPO.gestionturnos.exceptions.PacienteInexistenteException;
import com.TPO.gestionturnos.exceptions.PacienteLoginNoExitosoException;

public interface PacienteService {

    public List<Paciente> getPacientes();
    
    public Paciente createPaciente(String nombre, String apellido, String mail, String password) throws PacienteExistenteException;

    public Paciente modifyPaciente(Long id, String nombre, String apellido, String mail, String password) throws PacienteInexistenteException;

    public Optional <Paciente> getPacienteById(Long id) throws PacienteInexistenteException;

    public Optional <Paciente> getPacienteByMail(String mail) throws PacienteInexistenteException;

    public Long loginPaciente (String mail, String password) throws PacienteLoginNoExitosoException;
    
}
