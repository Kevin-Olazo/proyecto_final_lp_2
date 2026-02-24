package com.ciberedu.proyectolpii.service;

import java.util.List;

import com.ciberedu.proyectolpii.entity.Plan;

public interface PlanService {

    Plan guardarPlan(Plan plan);

    List<Plan> listarTodosPlan();

    Plan buscarPlanById(Integer id);

    Plan actualizarPlan(Plan plan);

    void eliminarPlanById(Integer id);
}


