package com.ciberedu.proyectolpii.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ciberedu.proyectolpii.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	// esto permite buscar por nombre, apellido o DNI desde un solo campo
	@Query(value = "SELECT * FROM cliente c WHERE " + "c.nombres LIKE CONCAT('%',:termino,'%') OR "
			+ "c.apellidos LIKE CONCAT('%',:termino,'%') OR "
			+ "c.dni LIKE CONCAT('%',:termino,'%')", nativeQuery = true)
	List<Cliente> buscarPorTermino(@Param("termino") String termino);
	
	
	// Validacion para que no se registren dos clientes con el mismo DNI
	@Query(value = "SELECT * FROM cliente c WHERE c.dni = :dni", nativeQuery = true)
	Cliente buscarPorDni(@Param("dni") String dni);
}