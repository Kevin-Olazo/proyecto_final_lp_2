package com.ciberedu.proyectolpii.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ciberedu.proyectolpii.entity.Usuario;
import com.ciberedu.proyectolpii.service.RolService;
import com.ciberedu.proyectolpii.service.UsuarioService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UsuarioController {

    @Autowired private UsuarioService usuarioService;
    @Autowired private RolService rolService;

    @GetMapping("/")
    public String login(HttpServletRequest request) {
        if (request.getSession(false) != null &&
            request.getSession().getAttribute("usuarioLogueado") != null) {
            return "redirect:/inscripcion/new";
        }
        return "login";
    }

    @PostMapping("/login")
    public String iniciarSesion(Usuario usuario, HttpServletRequest request) {
        boolean valido = usuarioService.login(usuario);
        if (valido) {
            Usuario logueado = usuarioService.buscarByUsuario(usuario.getUsername());
            request.getSession().setAttribute("usuarioLogueado", logueado);
            return "redirect:/inscripcion/new";
        }
        return "redirect:/?error";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }

    // ---------- REGISTRO PÚBLICO — siempre Recepcionista ----------
    @GetMapping("/register")
    public String showRegistroForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "register";
    }

    @PostMapping("/register/save")
    public String guardarRegistro(Usuario usuario, Model model) {
        if (usuarioService.buscarByUsuario(usuario.getUsername()) != null) {
            model.addAttribute("error", "Ya existe un usuario con ese nombre");
            model.addAttribute("usuario", usuario);
            return "register";
        }
        usuario.setRol(rolService.buscarById(2));
        usuarioService.guardarUsuario(usuario);
        return "redirect:/register?success";
    }

    // ---------- PANEL ADMIN ----------
    @GetMapping("/usuario")
    public String listUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.listarTodosUsuario());
        return "usuario/index";
    }

    @GetMapping("/usuario/new")
    public String createUsuarioForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("rolList", rolService.listarTodosRol());
        return "usuario/create";
    }

    @PostMapping("/usuario")
    public String saveUsuario(Usuario usuario) {
        usuarioService.guardarUsuario(usuario);
        return "redirect:/usuario";
    }
}