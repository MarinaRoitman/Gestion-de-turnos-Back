package com.TPO.gestionturnos.service;

import java.util.List;
import java.util.Optional;

import com.TPO.gestionturnos.entity.ObraSocial;
import com.TPO.gestionturnos.exceptions.ObraSocialInexistenteException;

import com.TPO.gestionturnos.exceptions.ObraSocialExistenteException;

public interface ObraSocialService {

    public List<ObraSocial> getObrasSociales();

    public Optional<ObraSocial> getObraSocialById(Long id) throws ObraSocialInexistenteException;

    public Optional<ObraSocial> getObraSocialByNombre(String obraSocialNombre) throws ObraSocialInexistenteException;

    public ObraSocial createObraSocial(String nombre) throws ObraSocialExistenteException;

    public ObraSocial modifyObraSocial(Long id, String nombre) throws ObraSocialInexistenteException;

    public void deleteObraSocial(Long id) throws ObraSocialInexistenteException;
}
