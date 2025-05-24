package com.TPO.gestionturnos.service;

import java.util.List;
import java.util.Optional;

import com.TPO.gestionturnos.entity.Plan;
import com.TPO.gestionturnos.exceptions.ObraSocialInexistenteException;
import com.TPO.gestionturnos.exceptions.PlanExistenteException;
import com.TPO.gestionturnos.exceptions.PlanInexistenteException;

public interface PlanService {

    public List<Plan> getPlanes();
    
    public Plan createPlan(String nombre, Long idObraSocial) throws PlanExistenteException;

    public Plan modifyPlan(Long id, String nombre) throws PlanInexistenteException;

    public Optional <Plan> getPlanById(Long id) throws PlanInexistenteException;
    
    public void deletePlan(Long id) throws PlanInexistenteException;

    public List<Plan> getPlanesByObraSocial(Long idObraSocial) throws ObraSocialInexistenteException;
}
