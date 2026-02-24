package com.ciberedu.proyectolpii.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ciberedu.proyectolpii.entity.Rol;
import com.ciberedu.proyectolpii.repository.RolRepository;
import com.ciberedu.proyectolpii.service.RolService;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository rolRepository;

    @Override
    public List<Rol> listarTodosRol() {
        return rolRepository.findAll();
    }

    @Override
    public Rol buscarById(Integer id) {
        return rolRepository.findById(id).orElse(null);
    }
}