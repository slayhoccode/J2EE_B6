package com.example.b5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.b5.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}