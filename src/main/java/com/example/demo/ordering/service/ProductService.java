package com.example.demo.ordering.service;

import com.example.demo.ordering.dtos.ProductRequest;
import com.example.demo.ordering.model.Product;
import com.example.demo.ordering.model.ProductType;

import java.util.List;

public interface ProductService {
    Product newProduct(ProductRequest productDTO);

    Product editProduct(Long id, ProductRequest productDTO);

    List<Product> getAllProducts();

    void deleteProduct(Long id);

    void importProducts(byte[] file);

    List<ProductType> getAllProductTypes();
}
