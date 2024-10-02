package com.example.demo.Controllers;

import com.example.demo.DAL.Repo.ProductRepository;
import com.example.demo.DAL.Repo.ProductTypeRepository;
import com.example.demo.Domain.DatabaseEntity.Product;
import com.example.demo.Domain.DatabaseEntity.ProductType;
import com.example.demo.Domain.Shared.Annotations.DBOperation;
import com.example.demo.Domain.Shared.Annotations.LogActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

@RestController
@RequestMapping("/Product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @GetMapping("/ProductTypes")
    public List<ProductType> selectUsers() {
        return productTypeRepository.findAll();
    }

    @GetMapping("/AllProducts")
    public List<Product> selectAllProducts() {
        return productRepository.findProductsByProductTypeId(1L,PageRequest.of(1,1, Sort.by(Sort.Order.asc("name"))));
    }

    @GetMapping("/InsertProizvoda")
    @LogActivity(operation = DBOperation.Insert)
    public String insertProizvoda(){
        return "Isertovan Proizvod";
    }
}
