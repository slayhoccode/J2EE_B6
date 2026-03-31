package com.example.b5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.b5.model.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
}
