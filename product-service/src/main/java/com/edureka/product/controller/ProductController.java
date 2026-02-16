package com.edureka.product.controller;


import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.edureka.product.dto.ProductResponse;
import com.edureka.product.entity.Product;
import com.edureka.product.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	 private static final Logger logger =
	            LoggerFactory.getLogger(ProductController.class);
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping("/newProduct")
    public ResponseEntity<ProductResponse> addProduct(@RequestBody Product product) {
    	logger.info("The product is received");
         Product newProduct=service.addProduct(product);
         String msg="New product inserted:"+newProduct.getName();
         return ResponseEntity.ok(new ProductResponse(msg));
    }

    @GetMapping
    public List<Product> getProducts() {
        return service.getProducts();
    }
    @GetMapping("/{id}")
    public Product getById(@PathVariable("id") Long id) {
        return service.getById(id);
    }
    
    @GetMapping("/exists/{category}")
    public Boolean isCategoryPresent(@PathVariable("category") String category) {
        return service.isCategoryPresent(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {
        String message=service.deleteProduct(id);
        return ResponseEntity.ok(message);
    }
}
