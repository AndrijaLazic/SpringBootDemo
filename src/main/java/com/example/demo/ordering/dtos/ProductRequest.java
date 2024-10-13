package com.example.demo.ordering.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequest {
    @NotNull
    private String name;
    @NotNull
    private String unitOfMeasure;
    @NotNull
    private String productType;
}
