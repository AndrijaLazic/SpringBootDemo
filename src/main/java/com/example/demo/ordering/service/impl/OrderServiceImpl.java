package com.example.demo.ordering.service.impl;

import com.example.demo.ordering.dtos.OrderRequest;
import com.example.demo.ordering.dtos.UpdateOrderRequest;
import com.example.demo.ordering.model.Order;
import com.example.demo.ordering.model.OrderItem;
import com.example.demo.ordering.model.Product;
import com.example.demo.ordering.repository.OrderRepository;
import com.example.demo.ordering.repository.ProductRepository;
import com.example.demo.ordering.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.Map.Entry;

@Service
@Transactional
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    @Override
    public Order createOrder(OrderRequest orderRequest) {
        Order order = new Order();
        Set<OrderItem> orderItemList = new HashSet<>();
        Map<String, Integer> orderItemsMap = orderRequest.getOrderItems();
        Set<Product> products = productRepository.findAllByNameIn(orderItemsMap.keySet());

        order.setDateOfCreation(LocalDate.now());
        order.setOrderNumber(UUID.randomUUID().toString());

        for (Entry<String, Integer> entry : orderItemsMap.entrySet()) {

            Product product = products.stream().filter(p -> p.getName().equals(entry.getKey())).findFirst()
                .orElse(null);

            if (product != null) {
                OrderItem item = new OrderItem();
                item.setProduct(product);
                item.setQuantity(entry.getValue());
                item.setOrder(order);
                orderItemList.add(item);
            }
        }
        order.setOrderItems(orderItemList);
        return orderRepository.save(order);
    }

    @Override
    public Page<Order> getAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public void deleteOrderItems(Long id, List<String> items) {
        Order order = getOrderById(id);
        for (String item : items) {
            order.getOrderItems().stream().filter(x->x.getProduct().getName().equals(item))
                    .findFirst().ifPresent(x->{
                        order.getOrderItems().remove(x);
            });
        }
        orderRepository.saveAndFlush(order);

    }

    @Override
    public Order updateOrder(Long id, UpdateOrderRequest request) {
        Order order = orderRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        for (OrderItem orderItem : order.getOrderItems()) {
            request.getOrderItems().stream().filter(x->x.getId().equals(orderItem.getId())).
                    findFirst().ifPresent(x->{
                        orderItem.setQuantity(x.getQuantity());
            });
        }
        return orderRepository.saveAndFlush(order);
    }


}
