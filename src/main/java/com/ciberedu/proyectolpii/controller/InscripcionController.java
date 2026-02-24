package com.ciberedu.proyectolpii.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ciberedu.proyectolpii.entity.Inscripcion;
import com.ciberedu.proyectolpii.entity.Usuario;
import com.ciberedu.proyectolpii.service.ClienteService;
import com.ciberedu.proyectolpii.service.InscripcionService;
import com.ciberedu.proyectolpii.service.PlanService;
import com.ciberedu.proyectolpii.service.UsuarioService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/inscripcion")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PlanService planService;

    @Autowired
    private UsuarioService usuarioService;

    // Listar todas las inscripciones
    @GetMapping
    public String listInscripciones(Model model) {
        model.addAttribute("inscripciones", inscripcionService.listarTodasInscripciones());
        return "inscripcion/index";
    }

    // Mostrar formulario para nueva inscripción
    @GetMapping("/new")
    public String createInscripcionForm(Model model) {
        model.addAttribute("inscripcion", new Inscripcion());
        model.addAttribute("clienteList", clienteService.listarTodosCliente());
        model.addAttribute("planList", planService.listarTodosPlan());
        model.addAttribute("usuarioList", usuarioService.listarTodosUsuario());
        return "inscripcion/create";
    }

    // Registrar nueva inscripción
    @PostMapping
    public String saveInscripcion(Inscripcion inscripcion,
                                  HttpServletRequest request,
                                  RedirectAttributes redirectAttrs) {
        Usuario usuarioLogueado = (Usuario) request.getSession().getAttribute("usuarioLogueado");
        if (usuarioLogueado == null) {
            return "redirect:/";
        }
        inscripcion.setUsuario(usuarioLogueado);
        inscripcionService.registrarInscripcion(inscripcion);
        redirectAttrs.addFlashAttribute("mensaje", "✓ Inscripción registrada correctamente");
        redirectAttrs.addFlashAttribute("clase", "success");
        return "redirect:/inscripcion/new";
    }

    // CONSULTA: Inscripciones activas ----
    @GetMapping("/activas")
    public String verActivas(Model model) {
        model.addAttribute("inscripciones", inscripcionService.buscarInscripcionesActivas());
        return "inscripcion/activas";
    }

    // CONSULTA: Historial de inscripciones ----
    @GetMapping("/historial")
    public String verHistorial(@RequestParam(required = false) Integer idCliente, Model model) {
        model.addAttribute("clienteList", clienteService.listarTodosCliente());
        if (idCliente != null) {
            model.addAttribute("inscripciones", inscripcionService.buscarPorCliente(idCliente));
            model.addAttribute("idCliente", idCliente);
        }
        return "inscripcion/historial";
    }
}