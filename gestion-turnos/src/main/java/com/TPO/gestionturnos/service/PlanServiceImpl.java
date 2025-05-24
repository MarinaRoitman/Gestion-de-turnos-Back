package com.TPO.gestionturnos.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TPO.gestionturnos.entity.ObraSocial;
import com.TPO.gestionturnos.entity.Plan;
import com.TPO.gestionturnos.exceptions.PlanInexistenteException;
import com.TPO.gestionturnos.exceptions.PlanExistenteException;
import com.TPO.gestionturnos.repository.ObrasSocialesRepository;
import com.TPO.gestionturnos.repository.PlanesRepository;
import com.TPO.gestionturnos.exceptions.ObraSocialExistenteException;
import com.TPO.gestionturnos.exceptions.ObraSocialInexistenteException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService{
    @Autowired
    private PlanesRepository planRepository;
    
    @Autowired
    private ObrasSocialesRepository obraSocialRepository;

    public List<Plan> getPlanes() {
        return planRepository.findAll();
    }

    @Override
    public Plan createPlan(String nombre, Long idObraSocial) throws PlanExistenteException {
        if (planRepository.findByNombre(nombre).isPresent()) {
        throw new PlanExistenteException();
        }

        ObraSocial obraSocial = obraSocialRepository.findById(idObraSocial)
            .orElseThrow(() -> new IllegalArgumentException("Obra social no encontrada"));

        Plan plan = new Plan();
        plan.setNombre(nombre);
        plan.setObraSocial(obraSocial);

        return planRepository.save(plan);
    }

    @Override
    public Plan modifyPlan(Long id, String nombre)
            throws PlanInexistenteException {
        Optional<Plan> planOriginal = planRepository.findById(id);

        if (planOriginal.isEmpty()) {
            throw new PlanInexistenteException();
        }

        Plan plan = planOriginal.get();

        if (nombre != null) plan.setNombre(nombre);

        return planRepository.save(plan);
    }

    @Override
    public Optional<Plan> getPlanById(Long id) throws PlanInexistenteException {
        return planRepository.findById(id);
    }

    @Override
    public void deletePlan(Long id) throws PlanInexistenteException {
        if (!planRepository.existsById(id)) {
                throw new PlanInexistenteException();
            }

        planRepository.deleteById(id);
    }

    @Override
    public List<Plan> getPlanesByObraSocial(Long idObraSocial) throws ObraSocialInexistenteException {
        Optional<ObraSocial> obraSocial = obraSocialRepository.findById(idObraSocial);
        return planRepository.findByObraSocial(obraSocial);
    }
    
}
