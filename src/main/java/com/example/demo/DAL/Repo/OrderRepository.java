package com.example.demo.DAL.Repo;

import com.example.demo.Domain.DatabaseEntity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}