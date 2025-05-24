package com.TPO.gestionturnos.service;

import java.util.List;
import java.util.Optional;

import com.TPO.gestionturnos.entity.Estado;
import com.TPO.gestionturnos.exceptions.EstadoInexistenteException;

public interface EstadoService {
    public List<Estado> getEstados();
    public Optional<Estado> getEstadoById(Long estadoId) throws EstadoInexistenteException;
}
