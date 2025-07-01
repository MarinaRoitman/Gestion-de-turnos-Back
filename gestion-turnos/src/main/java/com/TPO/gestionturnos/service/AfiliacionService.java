package com.TPO.gestionturnos.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.TPO.gestionturnos.entity.Afiliacion;
import com.TPO.gestionturnos.exceptions.AfiliacionIncompatibleException;
import com.TPO.gestionturnos.exceptions.AfiliacionInexistenteException;
import com.TPO.gestionturnos.exceptions.AfiliacionNoCreadaException;
import com.TPO.gestionturnos.exceptions.ObraSocialInexistenteException;
import com.TPO.gestionturnos.exceptions.PacienteInexistenteException;
import com.TPO.gestionturnos.exceptions.PlanInexistenteException;

public interface AfiliacionService {

    public List<Afiliacion> getAfiliaciones();

    public Optional<Afiliacion> getAfiliacionById(Long afiliacionId) throws AfiliacionInexistenteException;

    public Optional<List<Afiliacion>> getAfiliacioesnByObraSocial(Long obraSocialId) throws ObraSocialInexistenteException;

    public Optional<List<Afiliacion>> getAfiliacionesByPaciente(Long pacienteId) throws PacienteInexistenteException;

    public Afiliacion createAfiliacion(String nroAfiliado, LocalDate fechaAlta, LocalDate fechaFin, Long idPaciente, Long idObraSocial, Long idPlan) throws AfiliacionIncompatibleException, AfiliacionNoCreadaException, ObraSocialInexistenteException, PacienteInexistenteException, PlanInexistenteException;

    public Afiliacion modifyAfiliacion(Long id, String nroAfiliado, LocalDate fechaAlta, LocalDate fechaFin, Long idObraSocial, Long idPlan) throws AfiliacionInexistenteException, AfiliacionNoCreadaException, ObraSocialInexistenteException, PacienteInexistenteException, PlanInexistenteException;

    public void deleteAfiliacion(Long id) throws AfiliacionInexistenteException;
}
