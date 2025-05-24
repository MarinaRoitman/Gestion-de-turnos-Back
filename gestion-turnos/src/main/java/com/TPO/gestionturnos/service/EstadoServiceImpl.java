package com.TPO.gestionturnos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TPO.gestionturnos.entity.Estado;
import com.TPO.gestionturnos.exceptions.EstadoInexistenteException;
import com.TPO.gestionturnos.repository.EstadosRepository;

@Service
public class EstadoServiceImpl implements EstadoService{
    @Autowired
    private EstadosRepository estadosRepository;

    @Override
    public List<Estado> getEstados() {
        return estadosRepository.findAll();
    }

    @Override
    public Optional<Estado> getEstadoById(Long id) throws EstadoInexistenteException {
        return estadosRepository.findById(id);
    }
    
}
