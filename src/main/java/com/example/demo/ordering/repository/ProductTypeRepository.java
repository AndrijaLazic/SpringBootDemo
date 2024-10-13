package com.example.demo.ordering.repository;

import com.example.demo.ordering.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {

    Optional<ProductType> findProductTypeByName(String name);

}
