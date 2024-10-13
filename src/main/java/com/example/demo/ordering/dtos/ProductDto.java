package com.example.demo.ordering.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.demo.ordering.model.Product}
 */
@Value
public class ProductDto implements Serializable {
    Long id;
    @NotNull
    @Size(max = 255)
    String name;
    @NotNull
    @Size(max = 255)
    String unitOfMeasure;
    Long productTypeId;
    String productTypeName;
}