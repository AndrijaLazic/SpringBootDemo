package com.example.demo.ordering.dtos;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.demo.ordering.model.OrderItem}
 */
@Value
public class OrderItemDto implements Serializable {
    Long id;
    Integer quantity;
    String productName;
    String productUnitOfMeasure;
}