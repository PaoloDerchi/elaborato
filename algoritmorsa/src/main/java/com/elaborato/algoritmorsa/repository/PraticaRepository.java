package com.elaborato.algoritmorsa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elaborato.algoritmorsa.model.Materia;



public interface PraticaRepository extends JpaRepository<Materia, Integer>{
}