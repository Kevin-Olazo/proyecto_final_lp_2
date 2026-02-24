package com.ciberedu.proyectolpii.service;

import java.util.List;

import com.ciberedu.proyectolpii.entity.Usuario;

public interface UsuarioService {

    Usuario guardarUsuario(Usuario usuario);

    List<Usuario> listarTodosUsuario();

 // Retorna true/false para no exponer el objeto usuario directamente al controller
    boolean login(Usuario usuario);

 // Buscamos el usuario para cargar el rol
    Usuario buscarByUsuario(String username);
}


