package com.example.demo.ordering.controller;

import com.example.demo.ordering.dtos.OrderDto;
import com.example.demo.ordering.dtos.OrderItemDto;
import com.example.demo.ordering.dtos.UpdateOrderRequest;
import com.example.demo.ordering.mappers.OrderMapper;
import com.example.demo.ordering.dtos.OrderRequest;
import com.example.demo.ordering.model.Order;
import com.example.demo.ordering.repository.OrderRepository;
import com.example.demo.ordering.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
@Tag(name = "#OrderService | Orders Controller", description = "Ordering service management")
public class OrderController {

    private final OrderService orderService;
    OrderRepository repository;
    private OrderMapper orderMapper;

    @PostMapping
    public void createOrder(@RequestBody OrderRequest orderRequest) {
        orderService.createOrder(orderRequest);
    }

    @GetMapping
    public Page<OrderDto> getAllOrders(@RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
                                       @RequestParam(name = "pageSize", defaultValue = "20") @Min(1) Integer pageSize) {
        Page<Order> orders = orderService.getAllOrders(PageRequest.of(pageNumber, pageSize));
        List<OrderDto> orderDtoList = new LinkedList<>();
        for (Order order : orders.getContent()) {
            orderDtoList.add(orderMapper.toDto(order));
        }

        return new PageImpl<>(orderDtoList, PageRequest.of(pageNumber, pageSize), orders.getTotalElements());
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable("id") Long id) {
        Order order = orderService.getOrderById(id);
        return order;
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable("id") Long id) {
        orderService.deleteOrderById(id);
    }

    @DeleteMapping("/{id}/orderItems")
    public void deleteOrderItems(@PathVariable("id") Long id,@RequestBody List<String> items) {
        orderService.deleteOrderItems(id,items);
    }

    @PutMapping("/{id}/orderItems")
    public OrderDto updateOrderItems(@PathVariable("id") Long id, @RequestBody UpdateOrderRequest request) {
        Order order = orderService.updateOrder(id,request);
        return orderMapper.toDto(order);
    }
}

