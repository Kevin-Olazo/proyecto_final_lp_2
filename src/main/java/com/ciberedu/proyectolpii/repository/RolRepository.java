package com.ciberedu.proyectolpii.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ciberedu.proyectolpii.entity.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {

}
