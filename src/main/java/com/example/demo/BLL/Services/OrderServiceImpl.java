package com.example.demo.BLL.Services;

import com.example.demo.DAL.Repo.OrderRepository;
import com.example.demo.DAL.Repo.ProductRepository;
import com.example.demo.Domain.DTO.OrderRequest;
import com.example.demo.Domain.DatabaseEntity.Order;
import com.example.demo.Domain.DatabaseEntity.OrderItem;
import com.example.demo.Domain.DatabaseEntity.Product;
import jakarta.persistence.EntityNotFoundException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Map.Entry;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class OrderServiceImpl {

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;


    public Order createOrder(OrderRequest orderRequest) {
        Order order = new Order();
        List<OrderItem> orderItemList = new LinkedList<>();
        Map<String, Integer> orderItemsMap = orderRequest.getItems();
        Set< Product > products = productRepository.findAllByNameIn(orderItemsMap.keySet());

        for (Entry<String, Integer> entry : orderItemsMap.entrySet()) {

            Product product = products.stream().filter(p -> p.getName().equals(entry.getKey())).findFirst()
                .orElse(null);

            if (product != null) {
                OrderItem item = new OrderItem();
                item.setOrder(order);
                item.setQuantity(entry.getValue());
                item.setUnitOfMeasure(product.getUnitOfMeasure());
                item.setName(product.getName());

                orderItemList.add(item);
            }
        }
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setDateOfCreation(LocalDate.from(LocalDateTime.now()));
        order.setOrderItems(orderItemList);
        return orderRepository.save(order);
    }

    public Page<Order> getAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
