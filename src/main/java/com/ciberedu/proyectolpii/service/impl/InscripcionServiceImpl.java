package com.ciberedu.proyectolpii.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ciberedu.proyectolpii.entity.Inscripcion;
import com.ciberedu.proyectolpii.entity.Plan;
import com.ciberedu.proyectolpii.repository.InscripcionRepository;
import com.ciberedu.proyectolpii.service.InscripcionService;
import com.ciberedu.proyectolpii.service.PlanService;

import jakarta.transaction.Transactional;

@Service
public class InscripcionServiceImpl implements InscripcionService {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private PlanService planService;

    @Override
    @Transactional
    public Inscripcion registrarInscripcion(Inscripcion inscripcion) {
        // Traemos el plan completo para leer su duración y precio       
        Plan plan = planService.buscarPlanById(inscripcion.getPlan().getIdPlan());

        // La fecha de inicio es hoy, la fecha de fin se calcula sumando los días del plan
        inscripcion.setFechaInicio(LocalDate.now());
        inscripcion.setFechaFin(LocalDate.now().plusDays(plan.getDuracionDias()));
        
        // El total se calcula con el precio del plan
        inscripcion.setTotal(plan.getPrecio());

        return inscripcionRepository.save(inscripcion);
    }

    @Override
    public List<Inscripcion> listarTodasInscripciones() {
        return inscripcionRepository.findAll();
    }

    @Override
    public List<Inscripcion> buscarInscripcionesActivas() {
        return inscripcionRepository.buscarInscripcionesActivas();
    }

    @Override
    public List<Inscripcion> buscarPorCliente(Integer idCliente) {
        return inscripcionRepository.buscarPorCliente(idCliente);
    }
    
    @Override
    public boolean clienteTieneInscripciones(Integer idCliente) {
        return inscripcionRepository.contarPorCliente(idCliente) > 0;
    }

    @Override
    public boolean planTieneInscripciones(Integer idPlan) {
        return inscripcionRepository.contarPorPlan(idPlan) > 0;
    }
}