package com.ciberedu.proyectolpii.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ciberedu.proyectolpii.entity.Plan;
import com.ciberedu.proyectolpii.service.InscripcionService;
import com.ciberedu.proyectolpii.service.PlanService;

@Controller
public class PlanController {

    @Autowired private PlanService planService;
    @Autowired private InscripcionService inscripcionService;

    @GetMapping("/plan")
    public String listPlanes(Model model) {
        model.addAttribute("planes", planService.listarTodosPlan());
        return "plan/index";
    }

    @GetMapping("/plan/new")
    public String createPlanForm(Model model) {
        model.addAttribute("plan", new Plan());
        return "plan/create";
    }

    @PostMapping("/plan")
    public String savePlan(Plan plan) {
        planService.guardarPlan(plan);
        return "redirect:/plan";
    }

    @GetMapping("/plan/edit/{id}")
    public String editPlanForm(@PathVariable Integer id, Model model) {
        model.addAttribute("plan", planService.buscarPlanById(id));
        return "plan/edit";
    }

    @PostMapping("/plan/{id}")
    public String updatePlan(@PathVariable Integer id, Plan plan) {
        // Buscamos el plan para actualizar
        Plan existente = planService.buscarPlanById(id);
        existente.setNombre(plan.getNombre());
        existente.setDescripcion(plan.getDescripcion());
        existente.setDuracionDias(plan.getDuracionDias());
        existente.setPrecio(plan.getPrecio());
        planService.actualizarPlan(existente);
        return "redirect:/plan";
    }

    @GetMapping("/plan/{id}")
    public String deletePlan(@PathVariable Integer id,
                            RedirectAttributes redirectAttrs) {
        // Verificamos que el plan no tenga clientes inscritos
        if (inscripcionService.planTieneInscripciones(id)) {
            redirectAttrs.addFlashAttribute("error",
                "No se puede eliminar este plan porque ya tiene inscripciones registradas.");
            return "redirect:/plan";
        }
        planService.eliminarPlanById(id);
        return "redirect:/plan";
    }
}
