package com.ciberedu.proyectolpii.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ciberedu.proyectolpii.entity.Cliente;
import com.ciberedu.proyectolpii.repository.ClienteRepository;
import com.ciberedu.proyectolpii.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> listarTodosCliente() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarClienteById(Integer id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public Cliente actualizarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public void eliminarClienteById(Integer id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public List<Cliente> buscarPorTermino(String termino) {
        return clienteRepository.buscarPorTermino(termino);
    }

    @Override
    public Cliente buscarPorDni(String dni) {
        return clienteRepository.buscarPorDni(dni);
    }


}