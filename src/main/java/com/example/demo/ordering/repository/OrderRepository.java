package com.example.demo.ordering.repository;

import com.example.demo.ordering.model.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    @EntityGraph(value = "Order.Full", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Order> findById(Long orderId);
}
