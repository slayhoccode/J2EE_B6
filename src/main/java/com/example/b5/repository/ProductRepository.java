package com.example.b5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.b5.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    
}
