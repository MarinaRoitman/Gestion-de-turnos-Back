package com.TPO.gestionturnos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TPO.gestionturnos.entity.ObraSocial;
import com.TPO.gestionturnos.exceptions.ObraSocialExistenteException;
import com.TPO.gestionturnos.exceptions.ObraSocialInexistenteException;
import com.TPO.gestionturnos.repository.ObrasSocialesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ObraSocialServiceImpl implements ObraSocialService{
    @Autowired
    private ObrasSocialesRepository obraSocialRepository;

    public List<ObraSocial> getObrasSociales() {
        return obraSocialRepository.findAll();
    }

    @Override
    public Optional<ObraSocial> getObraSocialById(Long id) throws ObraSocialInexistenteException {
        return obraSocialRepository.findById(id);
    }

    @Override
    public Optional<ObraSocial> getObraSocialByNombre(String obraSocialNombre) throws ObraSocialInexistenteException {
        return obraSocialRepository.findByNombre(obraSocialNombre);
    }

    @Override
    public ObraSocial createObraSocial(String nombre)
            throws ObraSocialExistenteException {
        Optional<ObraSocial> obraSocialExistente = obraSocialRepository.findByNombre(nombre);
        if (obraSocialExistente.isPresent()) {
            throw new ObraSocialExistenteException();
        }

        return obraSocialRepository.save(new ObraSocial(nombre));
    }

    @Override
    public ObraSocial modifyObraSocial(Long id, String nombre)
            throws ObraSocialInexistenteException {
        Optional<ObraSocial> obraSocialOriginal = obraSocialRepository.findById(id);

        if (obraSocialOriginal.isEmpty()) {
            throw new ObraSocialInexistenteException();
        }

        ObraSocial obraSocial = obraSocialOriginal.get();

        if (nombre != null) obraSocial.setNombre(nombre);

        return obraSocialRepository.save(obraSocial);
    }

    @Override
    public void deleteObraSocial(Long id) throws ObraSocialInexistenteException {
        if (!obraSocialRepository.existsById(id)) {
                throw new ObraSocialInexistenteException();
            }

        obraSocialRepository.deleteById(id);
    }
    
}
