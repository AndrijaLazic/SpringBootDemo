package com.example.demo.HTEC.services;

import com.example.demo.BLL.Exceptions.ProductAlreadyExistsException;
import com.example.demo.BLL.Exceptions.ProductNotFoundException;
import com.example.demo.BLL.Services.ProductServiceImpl;
import com.example.demo.DAL.Repo.ProductRepository;
import com.example.demo.DAL.Repo.ProductTypeRepository;
import com.example.demo.Domain.DTO.ProductRequest;
import com.example.demo.Domain.DatabaseEntity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import liquibase.integration.spring.SpringLiquibase;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = "spring.h2.console.enabled=true")
@Sql(scripts = "/test-data/services/productService.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@TestPropertySource("/test.properties")
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Autowired
    private EntityManager entityManager;
//    @Test
//    @Rollback(true)
//    void newProduct_success() {
//        ProductRequest productDTO = new ProductRequest("Sljivovica","Litar","Rakija");
//        try {
//            productService.newProduct(productDTO);
//        } catch (ProductAlreadyExistsException e) {
//            Assertions.fail("Product already exists");
//        }
//        Optional<Product> newProduct = productRepository.findProductByName("Sljivovica");
//        Assertions.assertTrue(newProduct.isPresent());
//        Assertions.assertEquals("Sljivovica",newProduct.get().getName());
//        Assertions.assertEquals("Rakija",newProduct.get().getProductType().getName());
//        Assertions.assertEquals("Litar",newProduct.get().getUnitOfMeasure());
//    }
//
//    @Test
//    void newProduct_failure_duplicateName() {
//        ProductRequest productDTO = new ProductRequest("Sprite","Komad","Sok");
//        Assertions.assertThrows(ProductAlreadyExistsException.class, ()->productService.newProduct(productDTO));
//    }
//
//    @Test
//    @Rollback(true)
//    void editProduct_success() {
//        ProductRequest productDTO = new ProductRequest("Koka kola","Flasa","Sok");
//        productService.editProduct(2L,productDTO);
//
//        Product newProduct = productRepository.findById(2L).get();
//        Assertions.assertNotNull(newProduct);
//        Assertions.assertEquals("Koka kola",newProduct.getName());
//        Assertions.assertEquals("Flasa",newProduct.getUnitOfMeasure());
//        Assertions.assertEquals("Sok",newProduct.getProductType().getName());
//    }
//
    @Test
    void editProduct_failure_productNotFound() {
        ProductRequest productDTO = new ProductRequest("Koka kola","Flasa","Sok");
        Assertions.assertThrows(EntityNotFoundException.class, ()->productService.editProduct(-1L,productDTO));
    }

    @Test
    void getAllProducts_success() {
        List<Product> products = productService.getAllProducts();
        Assertions.assertEquals(2, products.size());
    }

    @Test
    void deleteProduct_success() {
        try {
            productService.deleteProduct(2L);
        } catch (ProductNotFoundException e) {
            Assertions.fail("ProductNotFoundException");
        }
        Optional<Product> deleteProduct = productRepository.findById(2L);
        Assertions.assertFalse(deleteProduct.isPresent());
    }

    @Test
    void deleteProduct_failure_productNotFound() {
        Assertions.assertThrows(ProductNotFoundException.class, ()->productService.deleteProduct(-1L));
    }
}