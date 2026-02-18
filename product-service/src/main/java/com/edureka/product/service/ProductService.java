package com.edureka.product.service;

import java.util.List;

import com.edureka.product.entity.Product;
import com.edureka.product.exception.ProductNotFoundException;
import com.edureka.product.repository.ProductRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service

public class ProductService {
	private static final Logger log =
            LoggerFactory.getLogger(ProductService.class);
	private final ProductRepository repository;

	public ProductService(ProductRepository repository) {
		this.repository = repository;
	}

	public Product addProduct(Product product) {
        log.info("inside createOrder method {}", product);
        if(isCategoryPresent(product.getCategory())) {
        	throw new ProductNotFoundException("Product cannot be added bcz category already exists");
        }
		return repository.save(product);
	}

	public List<Product> getProducts() {
		return repository.findAll();
	}

	public Product getById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));
	}

	public String deleteProduct(Long id) {
		if (!repository.existsById(id)) {
			throw new ProductNotFoundException("Product not found");
		}
		repository.deleteById(id);
		return "Product with: "+id+":deleted";
	}

	public boolean isCategoryPresent(String category) {
	    return repository.findByCategory(category).isPresent();
	}
}
