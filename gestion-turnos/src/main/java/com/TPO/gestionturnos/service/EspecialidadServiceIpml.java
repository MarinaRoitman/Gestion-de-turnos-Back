package com.TPO.gestionturnos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TPO.gestionturnos.entity.Especialidad;
import com.TPO.gestionturnos.entity.ObraSocial;
import com.TPO.gestionturnos.exceptions.EspecialidadExistenteException;
import com.TPO.gestionturnos.exceptions.EspecialidadInexistenteException;
import com.TPO.gestionturnos.repository.EspecialidadesRepository;

@Service
public class EspecialidadServiceIpml implements EspecialidadService {
    @Autowired
    private EspecialidadesRepository especialidadesRepository;

    @Override
    public List<Especialidad> getEspecialidades() {
        return especialidadesRepository.findAll();
    }

    @Override
    public Optional<Especialidad> getEspecialidadById(Long especialidadId) throws EspecialidadInexistenteException {
        return especialidadesRepository.findById(especialidadId);
    }

    @Override
    public Especialidad createEspecialidad(String nombre) throws EspecialidadExistenteException {
        return especialidadesRepository.save(new Especialidad(nombre));
    }

    @Override
    public Especialidad modifyEspecialidad(Long id, String nombre) throws EspecialidadInexistenteException {
        Optional<Especialidad> especialidadOriginal = especialidadesRepository.findById(id);
        Especialidad especialidad = especialidadOriginal.get();
        if (!especialidadOriginal.isPresent()) {
            throw new EspecialidadInexistenteException();
        }
        if (nombre != null) especialidad.setNombre(nombre);

        return especialidadesRepository.save(especialidad);
    }

    @Override
    public Long deleteEspecialidad(Long id) throws EspecialidadInexistenteException {
        Optional<Especialidad> especialidad = especialidadesRepository.findById(id);
        if (!especialidad.isPresent()) {
            throw new EspecialidadInexistenteException();
        }

        especialidadesRepository.delete(especialidad.get());
        return id;
    }
    
}
