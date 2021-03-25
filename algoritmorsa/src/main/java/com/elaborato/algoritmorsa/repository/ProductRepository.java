package com.elaborato.algoritmorsa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elaborato.algoritmorsa.model.Product;



public interface ProductRepository extends JpaRepository<Product, Integer>{
}