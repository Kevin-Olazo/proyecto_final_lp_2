package com.ciberedu.proyectolpii.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ciberedu.proyectolpii.entity.Plan;
import com.ciberedu.proyectolpii.repository.PlanRepository;
import com.ciberedu.proyectolpii.service.PlanService;

@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanRepository planRepository;

    @Override
    public Plan guardarPlan(Plan plan) {
        return planRepository.save(plan);
    }

    @Override
    public List<Plan> listarTodosPlan() {
        return planRepository.findAll();
    }

    @Override
    public Plan buscarPlanById(Integer id) {
        return planRepository.findById(id).orElse(null);
    }

    @Override
    public Plan actualizarPlan(Plan plan) {
        return planRepository.save(plan);
    }

    @Override
    public void eliminarPlanById(Integer id) {
        planRepository.deleteById(id);
    }
}