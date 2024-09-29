package com.example.demo.Controllers;

import com.example.demo.DAL.Repo.ProductRepository;
import com.example.demo.DAL.Repo.ProductTypeRepository;
import com.example.demo.Domain.DatabaseEntity.ProductType;
import com.example.demo.Domain.DatabaseEntity.User;
import org.springframework.beans.factory.annotation.Autowired;
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
}
