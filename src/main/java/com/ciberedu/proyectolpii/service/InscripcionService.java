package com.ciberedu.proyectolpii.service;

import java.util.List;

import com.ciberedu.proyectolpii.entity.Inscripcion;

public interface InscripcionService {

    // Este método hace más que solo guardar — calcula fechas y total automáticamente
    Inscripcion registrarInscripcion(Inscripcion inscripcion);

    List<Inscripcion> listarTodasInscripciones();

    List<Inscripcion> buscarInscripcionesActivas();

    List<Inscripcion> buscarPorCliente(Integer idCliente);
    
 // Para validar antes de eliminar
    boolean clienteTieneInscripciones(Integer idCliente);
    boolean planTieneInscripciones(Integer idPlan);
}