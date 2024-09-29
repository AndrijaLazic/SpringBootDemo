package com.example.demo.Domain.DTO;

import com.example.demo.Domain.DatabaseEntity.Product;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Product}
 */
@Value
public class ProductRequest implements Serializable {
    @NotNull
    @Size(max = 255)
    String name;
    @NotNull
    @Size(max = 255)
    String unitOfMeasure;
    String productType;
}