package com.TPO.gestionturnos.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TPO.gestionturnos.entity.Afiliacion;
import com.TPO.gestionturnos.entity.ObraSocial;
import com.TPO.gestionturnos.entity.Paciente;
import com.TPO.gestionturnos.entity.Plan;
import com.TPO.gestionturnos.exceptions.AfiliacionIncompatibleException;
import com.TPO.gestionturnos.exceptions.AfiliacionInexistenteException;
import com.TPO.gestionturnos.exceptions.AfiliacionNoCreadaException;
import com.TPO.gestionturnos.exceptions.ObraSocialInexistenteException;
import com.TPO.gestionturnos.exceptions.PacienteInexistenteException;
import com.TPO.gestionturnos.exceptions.PlanInexistenteException;
import com.TPO.gestionturnos.repository.AfiliacionesRepository;

@Service
public class AfiliacionServiceImpl implements AfiliacionService {
    @Autowired
    private AfiliacionesRepository afiliacionRepository;
    @Autowired
    private ObraSocialService obraSocialService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private PlanService planService;

    @Override
    public List<Afiliacion> getAfiliaciones() {
        return afiliacionRepository.findAll();
    }

    @Override
    public Optional<Afiliacion> getAfiliacionById(Long afiliacionId) throws AfiliacionInexistenteException {
        return afiliacionRepository.findById(afiliacionId);
    }

    @Override
    public Optional<List<Afiliacion>> getAfiliacioesnByObraSocial(Long obraSocialId) throws ObraSocialInexistenteException {
        Optional<ObraSocial> obraSocial = obraSocialService.getObraSocialById(obraSocialId);
        return afiliacionRepository.findByObraSocial(obraSocial);
    }

    @Override
    public Optional<List<Afiliacion>> getAfiliacionesByPaciente(Long pacienteId) throws PacienteInexistenteException {
        Optional<Paciente> paciente = pacienteService.getPacienteById(pacienteId);
        return afiliacionRepository.findByPaciente(paciente);
    }

    @Override
    public Afiliacion createAfiliacion(String nroAfiliado, LocalDate fechaAlta, LocalDate fechaFin, Long idPaciente,
            Long idObraSocial, Long idPlan) throws AfiliacionIncompatibleException, AfiliacionNoCreadaException, ObraSocialInexistenteException, PacienteInexistenteException, PlanInexistenteException {
        
        Optional<ObraSocial> obraSocial;
        Optional<Paciente> paciente;
        Optional<Plan> plan;
        
        try{
            obraSocial = obraSocialService.getObraSocialById(idObraSocial);
            paciente = pacienteService.getPacienteById(idPaciente);
            plan = planService.getPlanById(idPlan);
            Afiliacion afiliacion = new Afiliacion(nroAfiliado, fechaAlta, fechaFin, paciente.get(), obraSocial.get(), plan.get());
            return afiliacionRepository.save(afiliacion);
        }
        catch (PacienteInexistenteException e) {
            throw new PacienteInexistenteException();
        } catch (ObraSocialInexistenteException e2){
            throw new ObraSocialInexistenteException();
        }
        catch (PlanInexistenteException e3) {
            throw new PlanInexistenteException();
        }
    }

    @Override
    public Afiliacion modifyAfiliacion(Long id, String nroAfiliado, LocalDate fechaAlta, LocalDate fechaFin, Long idObraSocial, Long idPlan) 
        throws AfiliacionInexistenteException, AfiliacionNoCreadaException, ObraSocialInexistenteException, PacienteInexistenteException, PlanInexistenteException {
        Optional<Afiliacion> afiliacionOriginal = afiliacionRepository.findById(id);

        if (afiliacionOriginal.isEmpty()) {
            throw new AfiliacionInexistenteException();
        }

        Optional<Plan> plan;
        Optional<ObraSocial> obraSocial;
        Optional<Paciente> paciente;

        try {
            plan = planService.getPlanById(idPlan);
            obraSocial = obraSocialService.getObraSocialById(idObraSocial);
            paciente = pacienteService.getPacienteById(afiliacionOriginal.get().getPaciente().getId());
        } catch (PlanInexistenteException e) {
            throw new PlanInexistenteException();
        } catch (ObraSocialInexistenteException e) {
            throw new ObraSocialInexistenteException();
        } catch (PacienteInexistenteException e) {
            throw new PacienteInexistenteException();
        }

        Afiliacion afiliacion = afiliacionOriginal.get();
        afiliacion.setNroAfiliado(nroAfiliado);
        afiliacion.setFechaAlta(fechaAlta);
        afiliacion.setFechaFin(fechaFin);
        afiliacion.setObraSocial(obraSocial.get());
        afiliacion.setPlan(plan.get());
        return afiliacionRepository.save(afiliacion);
    }

    @Override
    public void deleteAfiliacion(Long id) throws AfiliacionInexistenteException {
        if (!afiliacionRepository.existsById(id)) {
                throw new AfiliacionInexistenteException();
            }

        afiliacionRepository.deleteById(id);
    }
}
