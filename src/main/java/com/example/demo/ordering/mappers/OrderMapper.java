package com.example.demo.ordering.mappers;

import com.example.demo.ordering.dtos.OrderDto;
import com.example.demo.ordering.dtos.OrderItemDto;
import com.example.demo.ordering.model.Order;
import com.example.demo.ordering.model.OrderItem;
import org.mapstruct.*;

import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {
    Order toEntity(OrderDto orderDto);
    OrderItemDto orderItemToOrderItemDto(OrderItem orderItem);
    Set<OrderItemDto> orderItemsToOrderItemDtos(Set<OrderItem> orderItems);
    OrderDto toDto(Order order);
}