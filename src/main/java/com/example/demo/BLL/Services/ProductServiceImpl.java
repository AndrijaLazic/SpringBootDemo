package com.example.demo.BLL.Services;

import com.example.demo.BLL.Exceptions.ProductAlreadyExistsException;
import com.example.demo.DAL.Repo.ProductRepository;
import com.example.demo.DAL.Repo.ProductTypeRepository;
import com.example.demo.Domain.DTO.ProductRequest;
import com.example.demo.Domain.DatabaseEntity.Product;
import com.example.demo.Domain.DatabaseEntity.ProductType;
import com.example.demo.BLL.Exceptions.ProductNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class ProductServiceImpl{

    private static final String JEDINICA_MERE = "Jedinica mere";

    private final ProductRepository productRepository;

    private final ProductTypeRepository productTypeRepository;

    @Transactional
    public Product newProduct(ProductRequest productDTO) throws ProductAlreadyExistsException {
        String productName = productDTO.getName();
        String productType = productDTO.getProductType();

        List<ProductType> productTypes = productTypeRepository.findAll();

        Optional<Product> optionalProduct = productRepository.findProductByName(productName);

        if (optionalProduct.isPresent()) {
            throw new ProductAlreadyExistsException("Product with same name exist in database.");
        }

        Product product = new Product();

        product.setName(productName);
        product.setUnitOfMeasure(productDTO.getUnitOfMeasure());
        product.setProductType(productTypeRepository.findProductTypeByName(productType).orElse(new ProductType(productType)));

        return productRepository.save(product);
    }

    @Transactional
    public Product editProduct(Long id, ProductRequest productDTO) {
        Product product = productRepository.findProductByName(productDTO.getName()).orElseThrow(
            EntityNotFoundException::new);

        product.setName(productDTO.getName());
        product.setUnitOfMeasure(product.getUnitOfMeasure());

        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Transactional
    public void deleteProduct(Long id) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException("Product with this id doesn't exist.");
        }
        productRepository.deleteById(id);
    }

    @Transactional
    public void importProducts(byte[] file) throws ExcelException {
        List<Product> products = new ArrayList<>();

        try {
            InputStream inputStream = new ByteArrayInputStream(file);



            ProductType productType = null;


            if (!CollectionUtils.isEmpty(products)) {
                productRepository.saveAll(products);
            }
            throw new IOException();
        } catch (IOException e) {
            throw new ExcelException("Can not parse excel file");
        }
    }
}
