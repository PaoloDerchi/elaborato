package com.elaborato.algoritmorsa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elaborato.algoritmorsa.model.Docente;


public interface DocenteRepository extends JpaRepository<Docente, Integer>{
	
	Docente findByNomeAndCognomeAndMatricola(String nome,String cognome, int matricola);
}