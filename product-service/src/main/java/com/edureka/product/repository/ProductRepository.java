package com.edureka.product.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edureka.product.entity.Product;

public interface ProductRepository
extends JpaRepository<Product, Long> {

Optional<Product> findByCategory(String category);

}

