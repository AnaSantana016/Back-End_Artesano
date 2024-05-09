package com.pdigs.backend.controllers;

import com.pdigs.backend.models.Product;
import com.pdigs.backend.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/products")
public class    ProductController {

    @Autowired
    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping
    public ResponseEntity<String> updateProduct(@RequestBody Product product) {
        productRepository.save(product);
        return ResponseEntity.ok("Product updated successfully");
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Iterable<Product> getProducts() {
        return productRepository.findAll();
    }

    @GetMapping(path = "/asc", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Iterable<Product> getProductsSortedByNameASC() {
        Sort sort = Sort.by("name").ascending();
        return productRepository.findAll(sort);
    }
    @GetMapping(path = "/desc", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Iterable<Product> getProductsSortedByNameDESC() {
        Sort sort = Sort.by("name").descending();
        return productRepository.findAll(sort);
    }

    @GetMapping(params = "name", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Iterable<Product> getProductsByName(@RequestParam String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product existingProduct = productRepository.findById(id).orElseThrow();
        BeanUtils.copyProperties(product, existingProduct, "id");
        Product updatedProduct = productRepository.save(existingProduct);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }
}