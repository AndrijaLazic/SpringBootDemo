package com.example.demo.ordering.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "Product")
@Table(name = "product", schema = "everyday_service_database", indexes = {
        @Index(name = "product_type_id", columnList = "product_type_id")
})
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 255)
    @NotNull
    @Column(name = "unit_of_measure", nullable = false)
    private String unitOfMeasure;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_type_id")
    private ProductType productType;

    public Product(String name, String unitOfMeasure, ProductType productType) {
        this.name = name;
        this.unitOfMeasure = unitOfMeasure;
        this.productType = productType;
    }
}