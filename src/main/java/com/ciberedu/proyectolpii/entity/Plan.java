package com.ciberedu.proyectolpii.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "plan")
public class Plan {

    @Id
    @Column(name = "idplan")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPlan;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "duracion_dias")
    private Integer duracionDias;

    @Column(name = "precio")
    private BigDecimal precio;
}


