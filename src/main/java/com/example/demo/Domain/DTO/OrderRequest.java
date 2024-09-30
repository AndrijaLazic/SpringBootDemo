package com.example.demo.Domain.DTO;

import com.example.demo.Domain.DatabaseEntity.OrderItem;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Set;

@Getter
@Setter
public class OrderRequest {
    Map<String, Integer> items;
}
