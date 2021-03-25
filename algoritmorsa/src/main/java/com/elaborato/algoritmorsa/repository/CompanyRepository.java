package com.elaborato.algoritmorsa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elaborato.algoritmorsa.model.Company;


public interface CompanyRepository extends JpaRepository<Company, Integer>{
}