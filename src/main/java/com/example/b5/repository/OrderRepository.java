package com.example.b5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.b5.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
