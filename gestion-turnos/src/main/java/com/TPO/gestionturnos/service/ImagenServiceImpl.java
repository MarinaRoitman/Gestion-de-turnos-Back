package com.TPO.gestionturnos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TPO.gestionturnos.entity.Especialidad;
import com.TPO.gestionturnos.entity.Imagen;
import com.TPO.gestionturnos.entity.Profesional;
import com.TPO.gestionturnos.entity.Turno;
import com.TPO.gestionturnos.exceptions.EspecialidadInexistenteException;
import com.TPO.gestionturnos.exceptions.ImagenInexistenteException;
import com.TPO.gestionturnos.exceptions.TurnoInexistenteException;
import com.TPO.gestionturnos.repository.ImagenesRepository;
import com.TPO.gestionturnos.repository.TurnosRepository;

@Service
public class ImagenServiceImpl implements ImagenService {

    @Autowired
    ImagenesRepository imagenesRepository;

    @Autowired
    TurnosRepository turnosRepository;

    @Override
    public List<Imagen> getImagenes() {
        return imagenesRepository.findAll();
    }

    @Override
    public Optional<Imagen> getImagenById(Long imagenId) throws ImagenInexistenteException {
        return imagenesRepository.findById(imagenId);
    }

    @Override
    public Optional<List<Imagen>> getImagenesByTurno(Long turnoId) throws TurnoInexistenteException {
        Optional<Turno> turno = turnosRepository.findById(turnoId);
        if (!turno.isPresent()) {
            throw new TurnoInexistenteException();
        }
        return imagenesRepository.findByTurno(turno.get());
    }

    @Override
    public Imagen createImagen(Long idTurno, String titulo, byte[] imagen) throws TurnoInexistenteException {
        Optional<Turno> turno = turnosRepository.findById(idTurno);
        if (!turno.isPresent()) {
            throw new TurnoInexistenteException();
        }
        return imagenesRepository.save(new Imagen(turno.get(), titulo, imagen));
    }

    @Override
    public Imagen updateImagen(Long id, String titulo, byte[] imagen) throws ImagenInexistenteException {
        Imagen imagenExistente = imagenesRepository.findById(id)
            .orElseThrow(ImagenInexistenteException::new);

        imagenExistente.setTitulo(titulo);
        imagenExistente.setImagen(imagen);

        return imagenesRepository.save(imagenExistente);
    }

    @Override
    public void deleteImagen(Long id) throws ImagenInexistenteException {
        Imagen imagenExistente = imagenesRepository.findById(id)
            .orElseThrow(ImagenInexistenteException::new);

        imagenesRepository.delete(imagenExistente);
    }
}