package com.example.demo.HTEC.services;

import com.example.demo.BLL.Services.OrderServiceImpl;
import com.example.demo.DAL.Repo.OrderItemRepository;
import com.example.demo.DAL.Repo.OrderRepository;
import com.example.demo.Domain.DTO.OrderRequest;
import com.example.demo.Domain.DatabaseEntity.Order;
import com.example.demo.Domain.DatabaseEntity.OrderItem;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = "spring.h2.console.enabled=true")
@Sql(scripts = "/test-data/services/orderService.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@TestPropertySource("/test.properties")
//@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderServiceImplTest {

    @Autowired
    OrderServiceImpl orderService;
    
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Test
    void createOrder_success() {
        OrderRequest request = new OrderRequest();
        Map<String, Integer> items = new HashMap<>();
        items.put("Sprite",20);
        request.setItems(items);
        Order order = orderService.createOrder(request);

        Optional<Order> newOrder = orderRepository.findById(order.getId());
        Assertions.assertTrue(newOrder.isPresent());

        List<OrderItem> orderItems = order.getOrderItems();
        List<OrderItem> newOrderItems = orderItemRepository.findAll().stream().filter(x->x.getOrder().getId().equals(newOrder.get().getId())).toList();

        for (int i = 0; i < orderItems.size(); i++) {
            OrderItem orderItem = orderItems.get(i);
            OrderItem newOrderItem = newOrderItems.stream().filter(x->x.getOrder().getId().equals(orderItem.getId())).findFirst().orElse(null);
            Assertions.assertNotNull(newOrderItem);

            Assertions.assertEquals(orderItem.getId(), newOrderItem.getId());
            Assertions.assertEquals(orderItem.getName(), newOrderItem.getName());
            Assertions.assertEquals(orderItem.getUnitOfMeasure(), newOrderItem.getUnitOfMeasure());
            Assertions.assertEquals(orderItem.getQuantity(), newOrderItem.getQuantity());
        }
    }

    @Test
    void getAllOrders_success() {
        Page<Order> orders = orderService.getAllOrders(PageRequest.of(0,10));
        Assertions.assertEquals(2, orders.getTotalElements());
    }

    @Test
    void getOrderById_failure() {
        Assertions.assertThrows(EntityNotFoundException.class,()->orderService.getOrderById(-1L));
    }

    @Test
    void getOrderById_success() {
        Order order = orderService.getOrderById(1L);
        Assertions.assertNotNull(order);
        Assertions.assertEquals(1, order.getOrderNumber());
    }
}