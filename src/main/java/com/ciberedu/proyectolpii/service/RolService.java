package com.ciberedu.proyectolpii.service;

import java.util.List;

import com.ciberedu.proyectolpii.entity.Rol;

public interface RolService {

    List<Rol> listarTodosRol();

    Rol buscarById(Integer id);
}



