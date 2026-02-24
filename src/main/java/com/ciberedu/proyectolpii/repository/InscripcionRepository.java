package com.ciberedu.proyectolpii.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ciberedu.proyectolpii.entity.Inscripcion;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {

    // CURDATE() devuelve la fecha de hoy en MySQL — así filtramos solo las vigentes
    @Query(value = "SELECT * FROM inscripcion i WHERE i.fecha_fin >= CURDATE()",
           nativeQuery = true)
    List<Inscripcion> buscarInscripcionesActivas();

    // Historial completo de un cliente, lo usamos en la consulta por cliente
    @Query(value = "SELECT * FROM inscripcion i WHERE i.idcliente = :idCliente",
           nativeQuery = true)
    List<Inscripcion> buscarPorCliente(@Param("idCliente") Integer idCliente);

    // Verificamos si el cliente esta inscrito a un plan
    @Query(value = "SELECT COUNT(*) FROM inscripcion i WHERE i.idcliente = :idCliente",
           nativeQuery = true)
    int contarPorCliente(@Param("idCliente") Integer idCliente);

    // Verificamos que el plan tiene clientes inscritos
    @Query(value = "SELECT COUNT(*) FROM inscripcion i WHERE i.idplan = :idPlan",
           nativeQuery = true)
    int contarPorPlan(@Param("idPlan") Integer idPlan);
}