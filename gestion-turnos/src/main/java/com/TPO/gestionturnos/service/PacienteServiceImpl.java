package com.TPO.gestionturnos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TPO.gestionturnos.entity.Paciente;
import com.TPO.gestionturnos.exceptions.PacienteExistenteException;
import com.TPO.gestionturnos.exceptions.PacienteInexistenteException;
import com.TPO.gestionturnos.exceptions.PacienteLoginNoExitosoException;
import com.TPO.gestionturnos.repository.PacientesRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class PacienteServiceImpl implements PacienteService {
    @Autowired
    private PacientesRepository pacienteRepository;

    public List<Paciente> getPacientes() {
        return pacienteRepository.findAll();
    }
    

    @Override
    public Paciente createPaciente(String nombre, String apellido, String mail, String password) throws PacienteExistenteException {
        mail = mail.toLowerCase();

        Optional<Paciente> pacienteExistente = pacienteRepository.findByMail(mail);
        if (pacienteExistente.isPresent()) {
            throw new PacienteExistenteException();
        }

        return pacienteRepository.save(new Paciente(nombre, apellido, mail, password));
    }

        
    @Override
    public Paciente modifyPaciente(Long id, String nombre, String apellido, String mail, String password) throws PacienteInexistenteException {
        mail = mail.toLowerCase();
        Optional<Paciente> paciente = pacienteRepository.findByMail(mail);
        
        if (!paciente.isEmpty())
            return pacienteRepository.save(new Paciente(id, nombre, apellido, mail, password));
        throw new PacienteInexistenteException();
    }

    @Override
    public Optional<Paciente> getPacienteById(Long id) throws PacienteInexistenteException {
        return pacienteRepository.findById(id);
    }

    @Override
    public Optional<Paciente> getPacienteByMail(String mail) throws PacienteInexistenteException {
        return pacienteRepository.findByMail(mail);
    }

    @Override
    public Long loginPaciente(String mail, String password) throws PacienteLoginNoExitosoException {
        mail = mail.toLowerCase();
        Optional<Paciente> optionalPaciente = pacienteRepository.findByMail(mail);

        if (optionalPaciente.isEmpty()) {
            throw new PacienteLoginNoExitosoException();
        }

        Paciente paciente = optionalPaciente.get();

        if (paciente.getPassword().equals(password)) {
            return paciente.getId();
        } else {
            throw new PacienteLoginNoExitosoException();
        }
    }


}