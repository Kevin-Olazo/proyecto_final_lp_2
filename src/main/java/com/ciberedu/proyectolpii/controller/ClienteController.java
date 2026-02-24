package com.ciberedu.proyectolpii.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ciberedu.proyectolpii.entity.Cliente;
import com.ciberedu.proyectolpii.service.ClienteService;
import com.ciberedu.proyectolpii.service.InscripcionService;

@Controller
public class ClienteController {

    @Autowired private ClienteService clienteService;
    @Autowired private InscripcionService inscripcionService;

    @GetMapping("/cliente")
    public String listClientes(Model model) {
        model.addAttribute("clientes", clienteService.listarTodosCliente());
        return "cliente/index";
    }

    @GetMapping("/cliente/new")
    public String createClienteForm(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cliente/create";
    }

    @PostMapping("/cliente")
    public String saveCliente(Cliente cliente, Model model) {
        // Verificamos el DNI antes de guardar para evitar duplicados.
        Cliente existente = clienteService.buscarPorDni(cliente.getDni());
        if (existente != null) {
            model.addAttribute("cliente", cliente);
            model.addAttribute("error", "Ya existe un cliente con el DNI: " + cliente.getDni());
            return "cliente/create";
        }
        clienteService.guardarCliente(cliente);
        return "redirect:/cliente";
    }

    @GetMapping("/cliente/edit/{id}")
    public String editClienteForm(@PathVariable Integer id, Model model) {
        model.addAttribute("cliente", clienteService.buscarClienteById(id));
        return "cliente/edit";
    }

    @PostMapping("/cliente/{id}")
    public String updateCliente(@PathVariable Integer id, Cliente cliente) {
        // Buscamos el objeto existente para no pisar campos que no vienen en el form
        Cliente existente = clienteService.buscarClienteById(id);
        existente.setNombres(cliente.getNombres());
        existente.setApellidos(cliente.getApellidos());
        existente.setDni(cliente.getDni());
        existente.setTelefono(cliente.getTelefono());
        existente.setEmail(cliente.getEmail());
        clienteService.actualizarCliente(existente);
        return "redirect:/cliente";
    }

    @GetMapping("/cliente/{id}")
    public String deleteCliente(@PathVariable Integer id,
                                org.springframework.web.servlet.mvc.support.RedirectAttributes redirectAttrs) {
        // Verificamos si el cliente esta inscrito
        if (inscripcionService.clienteTieneInscripciones(id)) {
            redirectAttrs.addFlashAttribute("error",
                "No se puede eliminar este cliente porque tiene inscripciones registradas.");
            return "redirect:/cliente";
        }
        clienteService.eliminarClienteById(id);
        return "redirect:/cliente";
    }

   
    // Buscamos el cliente usando un termino (nombre, apellido o dni)
    @GetMapping("/cliente/buscar")
    public String buscar(@RequestParam(required = false) String termino,
                         @RequestParam(required = false) Integer idCliente,
                         Model model) {
        if (termino != null && !termino.isBlank()) {
            model.addAttribute("clientes", clienteService.buscarPorTermino(termino));
            model.addAttribute("termino", termino);

            if (idCliente != null) {
                model.addAttribute("clienteSeleccionado",
                        clienteService.buscarClienteById(idCliente));
                model.addAttribute("historial",
                        inscripcionService.buscarPorCliente(idCliente));
            }
        }
        return "cliente/buscar";
    }
}