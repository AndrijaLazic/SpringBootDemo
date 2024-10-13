package com.example.demo.ordering.repository;

import com.example.demo.ordering.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository  extends JpaRepository<OrderItem,Long> {
}
