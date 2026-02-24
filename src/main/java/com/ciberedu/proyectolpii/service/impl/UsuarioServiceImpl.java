package com.ciberedu.proyectolpii.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ciberedu.proyectolpii.entity.Usuario;
import com.ciberedu.proyectolpii.repository.UsuarioRepository;
import com.ciberedu.proyectolpii.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> listarTodosUsuario() {
        return usuarioRepository.findAll();
    }

    @Override
    public boolean login(Usuario usuario) {
        // Si la consulta no encuentra coincidencia devuelve null,
        // con eso sabemos si las credenciales son correctas o no
        Usuario entidad = usuarioRepository.findByUsuarioAndClave(
                usuario.getUsername(), usuario.getClave());
        return entidad != null;
    }

    @Override
    public Usuario buscarByUsuario(String username) {
    	// Despues de login, cargamos el usuario completo para obtener el rol
    	// y guardar la sesion
        return usuarioRepository.findByUsuario(username);
    }
}
