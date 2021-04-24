package com.elaborato.algoritmorsa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elaborato.algoritmorsa.model.Docente;


public interface ImpiegatoRepository extends JpaRepository<Docente, Integer>{
}