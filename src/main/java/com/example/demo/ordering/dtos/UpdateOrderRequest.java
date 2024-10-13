package com.example.demo.ordering.dtos;

import lombok.Value;

import java.util.List;

@Value
public class UpdateOrderRequest {
    Long orderId;
    List<OrderItemDto> orderItems;
}
