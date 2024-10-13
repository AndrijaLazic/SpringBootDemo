package com.example.demo.ordering.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllProductsResponse {
    private Map<String, List<ProductDto>> productsByCategory;
}
