package com.TPO.gestionturnos.service;

import java.util.List;
import java.util.Optional;

import com.TPO.gestionturnos.entity.Imagen;
import com.TPO.gestionturnos.exceptions.ImagenInexistenteException;
import com.TPO.gestionturnos.exceptions.TurnoInexistenteException;
public interface ImagenService {

    public List<Imagen> getImagenes();

    public Optional<Imagen> getImagenById(Long imagenId) throws ImagenInexistenteException;

    public Optional<List<Imagen>> getImagenesByTurno(Long turnoId) throws TurnoInexistenteException;

    public Imagen createImagen(Long idTurno, String titulo, byte[] imagen) throws TurnoInexistenteException;

    public Imagen updateImagen(Long id, String titulo, byte[] imagen) throws ImagenInexistenteException;

    public void deleteImagen(Long id) throws ImagenInexistenteException;
}
