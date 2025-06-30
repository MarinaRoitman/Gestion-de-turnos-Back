package com.TPO.gestionturnos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TPO.gestionturnos.entity.Especialidad;
import com.TPO.gestionturnos.entity.Profesional;
import com.TPO.gestionturnos.exceptions.EspecialidadInexistenteException;
import com.TPO.gestionturnos.exceptions.ProfesionalExistenteException;
import com.TPO.gestionturnos.exceptions.ProfesionalInexistenteException;
import com.TPO.gestionturnos.repository.EspecialidadesRepository;
import com.TPO.gestionturnos.repository.ProfesionalesRepository;

@Service
public class ProfesionalServiceImpl implements ProfesionalService{

    @Autowired
    private ProfesionalesRepository profesionalesRepository;

    @Autowired
    private EspecialidadesRepository especialidadesRepository;

    @Override
    public List<Profesional> getProfesionales() {
        return profesionalesRepository.findAll();
    }

    @Override
    public Optional<Profesional> getProfesionalById(Long profesionalId) throws ProfesionalInexistenteException {
        return profesionalesRepository.findById(profesionalId);
    }

    @Override
    public Optional<Profesional> getProfesionalByMail(String profesionalMail) throws ProfesionalInexistenteException {
        return profesionalesRepository.findByMail(profesionalMail);
    }

    @Override
    public Optional<List<Profesional>> getProfesionalesByEspecialidad(Long especialidadId)
            throws EspecialidadInexistenteException {
        Optional<Especialidad> especialidad = especialidadesRepository.findById(especialidadId);
        if (!especialidad.isPresent()) {
            throw new EspecialidadInexistenteException();
        }
        return profesionalesRepository.findByEspecialidadesContaining(especialidad.get());
    }

    @Override
    public Optional<List<Profesional>> getProfesionalesByNombre(String profesionalNombre) {
        return profesionalesRepository.findByNombre(profesionalNombre);
    }

    @Override
    public Profesional createProfesional(String nombre, String apellido, String mail, String matricula, byte[] foto)
            throws ProfesionalExistenteException {
        Optional<Profesional> profesionalOriginal = profesionalesRepository.findByMatricula(matricula);
        if (profesionalOriginal.isPresent()) {
            throw new ProfesionalExistenteException();
        }

        Profesional profesional = new Profesional(nombre, apellido, matricula, mail);
        profesional.setFoto(foto);

        return profesionalesRepository.save(profesional);
    }

    @Override
    public Profesional modifyProfesional(Long id, String nombre, String apellido, String mail, String matricula, byte[] foto)
            throws ProfesionalInexistenteException {
        Optional<Profesional> profesionalOriginal = profesionalesRepository.findById(id);
        if (!profesionalOriginal.isPresent()) {
            throw new ProfesionalInexistenteException();
        }

        Profesional profesional = profesionalOriginal.get();
        profesional.setNombre(nombre);
        profesional.setApellido(apellido);
        profesional.setMail(mail);
        profesional.setMatricula(matricula);

        if (foto != null && foto.length > 0) {
            profesional.setFoto(foto);
        }

        return profesionalesRepository.save(profesional);
    }

    @Override
    public void deleteProfesional(Long id) throws ProfesionalInexistenteException {
        Optional<Profesional> profesional = profesionalesRepository.findById(id);

        if (!profesional.isPresent()) {
            throw new ProfesionalInexistenteException();
        }

        profesionalesRepository.delete(profesional.get());
    }

    @Override
    public Profesional deleteEspecialidad(Long profesionalId, Long especialidadId)
            throws ProfesionalInexistenteException, EspecialidadInexistenteException {
        
        Profesional profesional = profesionalesRepository.findById(profesionalId)
                .orElseThrow(ProfesionalInexistenteException::new);


        Especialidad especialidad = especialidadesRepository.findById(especialidadId)
                .orElseThrow(EspecialidadInexistenteException::new);

        profesional.getEspecialidades().remove(especialidad);

        return profesionalesRepository.save(profesional);
    }

    @Override
    public Profesional addEspecialidad(Long profesionalId, Long especialidadId)
            throws ProfesionalInexistenteException, EspecialidadInexistenteException {
        
        Profesional profesional = profesionalesRepository.findById(profesionalId)
                .orElseThrow(ProfesionalInexistenteException::new);

        Especialidad especialidad = especialidadesRepository.findById(especialidadId)
                .orElseThrow(EspecialidadInexistenteException::new);

        // Evitar duplicados
        if (!profesional.getEspecialidades().contains(especialidad)) {
            profesional.getEspecialidades().add(especialidad);
        }

        return profesionalesRepository.save(profesional);
    }
    
}
