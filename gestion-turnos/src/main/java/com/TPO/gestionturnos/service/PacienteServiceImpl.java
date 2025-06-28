package com.TPO.gestionturnos.service;

import java.time.LocalDate;
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
    public Paciente createPaciente(String nombre, String apellido, String mail, String password, String dni, LocalDate fechaNacimiento, String telefono) throws PacienteExistenteException {
        // if (mail == null || mail.trim().isEmpty()) {
        //     throw new IllegalArgumentException("El correo electrónico no puede ser nulo o vacío");
        // }
        mail = mail.toLowerCase();

        Optional<Paciente> pacienteExistente = pacienteRepository.findByMail(mail);
        if (pacienteExistente.isPresent()) {
            throw new PacienteExistenteException();  // Si ya existe un paciente con el mismo correo
        }

        return pacienteRepository.save(new Paciente(null, nombre, apellido, mail, password, dni, fechaNacimiento, telefono));
    }

        
    @Override
    public Paciente modifyPaciente(Long id, String nombre, String apellido, String mail, String password, String dni, LocalDate fechaNacimiento, String telefono) throws PacienteInexistenteException {
        Optional<Paciente> pacienteOriginal = pacienteRepository.findById(id);

        if (pacienteOriginal.isEmpty()) {
            throw new PacienteInexistenteException();
        }

        Paciente paciente = pacienteOriginal.get();

        // actualiza campos no nulos
        if (nombre != null) paciente.setNombre(nombre);
        if (apellido != null) paciente.setApellido(apellido);
        if (mail != null) paciente.setMail(mail.toLowerCase());
        if (password != null) paciente.setPassword(password);
        if (dni != null) paciente.setDni(dni);
        if (fechaNacimiento != null) paciente.setFechaNacimiento(fechaNacimiento);
        if (telefono != null) paciente.setTelefono(telefono);

        return pacienteRepository.save(paciente);
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


    @Override
    public void deletePaciente(Long id) throws PacienteInexistenteException {
        if (!pacienteRepository.existsById(id)) {
                throw new PacienteInexistenteException();
            }

        pacienteRepository.deleteById(id);
    }


    @Override
    public Paciente recoverPassword(String mail) throws PacienteInexistenteException {
        // TODO Auto-generated method stub
        // hacer funcion
        throw new UnsupportedOperationException("Unimplemented method 'recoverPassword'");
    }
}