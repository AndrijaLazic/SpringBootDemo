package com.example.demo.ordering.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity(name = "Order")
@Table(name = "orders", schema = "everyday_service_database")
@NamedEntityGraph(
        name = "Order.Full",
        attributeNodes = {
                @NamedAttributeNode(value = "orderItems",subgraph = "subgraph.product")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "subgraph.product",
                        attributeNodes = {
                                @NamedAttributeNode(value = "product",subgraph = "subgraph.productType")
                        }
                ),
                @NamedSubgraph(
                        name = "subgraph.productType",
                        attributeNodes = {
                                @NamedAttributeNode(value = "productType")
                        }
                )
        }
)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "order_number", nullable = false)
    private String orderNumber;

    @NotNull
    @Column(name = "date_of_creation", nullable = false)
    private LocalDate dateOfCreation;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    @JsonManagedReference
    private Set<OrderItem> orderItems = new LinkedHashSet<>();

}