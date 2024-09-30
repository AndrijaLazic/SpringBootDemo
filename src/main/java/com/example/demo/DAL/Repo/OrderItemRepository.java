package com.example.demo.DAL.Repo;

import com.example.demo.Domain.DatabaseEntity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}