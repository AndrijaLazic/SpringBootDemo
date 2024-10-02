package com.example.demo.DAL.Repo;

import com.example.demo.Domain.DatabaseEntity.Product;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProductRepository extends JpaRepository<Product, Long> {
  Optional<Product> findProductByName(String productName);

  Set<Product> findAllByNameIn(Set<String> strings);

  @Query("SELECT product from Product product JOIN FETCH product.productType where product.productType.id = :id")
  List<Product> findProductsByProductTypeId(@Param("id") Long id, PageRequest pageRequest);

//  @Query("SELECT u FROM User u WHERE u.name IN :names")
//  List<User> findUserByNameList(@Param("names") Collection<String> names);
}