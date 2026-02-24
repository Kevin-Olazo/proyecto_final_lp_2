package com.ciberedu.proyectolpii.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ciberedu.proyectolpii.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Buscar usuario por username para validar duplicados en registro
    @Query(value = "SELECT u.idusuario, u.nombres, u.apellidos, u.usuario, u.clave, u.idrol " +
                   "FROM usuario u WHERE u.usuario = :username", nativeQuery = true)
    Usuario findByUsuario(@Param("username") String username);

    // Buscar usuario por username y clave para login
    @Query(value = "SELECT u.idusuario, u.nombres, u.apellidos, u.usuario, u.clave, u.idrol " +
                   "FROM usuario u WHERE u.usuario = :username AND u.clave = :clave", nativeQuery = true)
    Usuario findByUsuarioAndClave(@Param("username") String username, @Param("clave") String clave);
}
