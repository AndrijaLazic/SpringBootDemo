package com.example.demo.ordering.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

/**
 * DTO for {@link com.example.demo.ordering.model.Order}
 */
@Value
public class OrderDto implements Serializable {
    Long id;
    @NotNull
    @Size(max = 255)
    String orderNumber;
    @NotNull
    LocalDate dateOfCreation;
    Set<OrderItemDto> orderItems;
}