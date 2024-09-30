package com.example.demo.DAL.Repo;

import com.example.demo.Domain.DatabaseEntity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface ProductRepository extends JpaRepository<Product, Long> {
  Optional<Product> findProductByName(String productName);

  Set<Product> findAllByNameIn(Set<String> strings);
}