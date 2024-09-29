package com.example.demo.DAL.Repo;

import com.example.demo.Domain.DatabaseEntity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
    Optional<ProductType> findProductTypeByName(String productType);
}