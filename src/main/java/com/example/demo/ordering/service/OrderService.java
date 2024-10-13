package com.example.demo.ordering.service;

import com.example.demo.ordering.dtos.OrderRequest;
import com.example.demo.ordering.dtos.UpdateOrderRequest;
import com.example.demo.ordering.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;

public interface OrderService {
    Order createOrder(OrderRequest orderRequest);
    Page<Order> getAllOrders (Pageable pageable);
    Order getOrderById(Long id);
    void deleteOrderById(Long id);

    void deleteOrderItems(Long id,List<String> items);

    Order updateOrder(Long id, UpdateOrderRequest request);
}
