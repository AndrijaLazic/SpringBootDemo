package com.example.demo.ordering.dtos;

import lombok.Data;

import java.util.Map;

@Data
public class OrderRequest {
    private Map<String, Integer> orderItems;
}
