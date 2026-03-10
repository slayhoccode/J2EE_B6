package com.example.b5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.b5.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    
}
