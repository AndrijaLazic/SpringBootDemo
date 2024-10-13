package com.example.demo.ordering.controller;

import com.example.demo.ordering.dtos.DeleteProductResponse;
import com.example.demo.ordering.dtos.GetAllProductsResponse;
import com.example.demo.ordering.dtos.ProductDto;
import com.example.demo.ordering.dtos.ProductRequest;
import com.example.demo.ordering.mappers.ProductMapper;
import com.example.demo.ordering.model.Product;
import com.example.demo.ordering.model.ProductType;
import com.example.demo.ordering.service.ProductService;
import com.example.demo.ordering.validators.exceptions.ExcelException;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/products")
@AllArgsConstructor
@Tag(name = "#OrderService | Products Controller", description = "Ordering service management")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @PostMapping
    public Product addProducts(@RequestBody ProductRequest productDTO) {
        return productService.newProduct(productDTO);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody ProductRequest productDTO) {
        return productService.editProduct(id, productDTO);
    }

    @GetMapping
    public GetAllProductsResponse getAllProduct() {
        List<Product> products = productService.getAllProducts();
        List<ProductDto> productDtoList = productMapper.toDtoList(products);
        GetAllProductsResponse productDto = new GetAllProductsResponse(productDtoList.stream()
                .collect(Collectors.groupingBy(s -> s.getProductTypeName())));

        return productDto;
    }

    @GetMapping("/allProductTypes")
    public List<ProductType> getAllProductTypes() {
        return productService.getAllProductTypes();
    }

    @DeleteMapping("/{id}")
    public DeleteProductResponse deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new DeleteProductResponse(String.format("Successfully deleted product with id: %s", id));
    }

    @PostMapping("/import")
    public ResponseEntity<String> importProducts(@RequestParam MultipartFile file) {
        try {
            productService.importProducts(file.getBytes());
        } catch (IOException e) {
            throw new ExcelException("There was an error while trying to import products");
        }

        return ResponseEntity.ok("Products imported successfully");
    }
}