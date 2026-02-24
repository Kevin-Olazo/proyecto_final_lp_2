package com.ciberedu.proyectolpii.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ciberedu.proyectolpii.entity.Plan;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer> {

}