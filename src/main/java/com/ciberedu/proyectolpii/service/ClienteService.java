package com.ciberedu.proyectolpii.service;

import java.util.List;

import com.ciberedu.proyectolpii.entity.Cliente;

public interface ClienteService {

    Cliente guardarCliente(Cliente cliente);

    List<Cliente> listarTodosCliente();

    Cliente buscarClienteById(Integer id);

    Cliente actualizarCliente(Cliente cliente);

    void eliminarClienteById(Integer id);

    // Búsqueda flexible — acepta nombre, apellido o DNI
    List<Cliente> buscarPorTermino(String termino);

    // Para control de dni duplicados antes de guardar
    Cliente buscarPorDni(String dni);

}