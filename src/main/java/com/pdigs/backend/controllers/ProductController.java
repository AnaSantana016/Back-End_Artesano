package com.pdigs.backend.controllers;

import com.pdigs.backend.models.Product;
import com.pdigs.backend.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
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

    @GetMapping
    public Iterable<Product> getProducts(@RequestParam(value = "filterBy", required = false) String filterBy,
                                         @RequestParam(value = "order", required = false) String order) {

        Sort sort;
        if (filterBy == null || filterBy.equals("")) {filterBy= "id";}
        Product product = new Product();

        if (order == null || order.isEmpty() || order.equals("asc")){
            sort = Sort.by(Sort.Direction.ASC, filterBy);
        }else if (order.equals("desc")){
            sort = Sort.by(Sort.Direction.DESC, filterBy);
        } else {
            return new ArrayList<>();
        }
        return productRepository.findAll(sort);
    }

    @PutMapping
    public ResponseEntity<String> updateProduct(@RequestParam (value = "id") Long id, @RequestBody Product product) {
        Product existingProduct = productRepository.findById(id).orElseThrow();
        BeanUtils.copyProperties(product, existingProduct, "id");
        Product updatedProduct = productRepository.save(existingProduct);
        return ResponseEntity.ok("Product Edited successfully");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProduct(@RequestParam (value = "id") Long id) {
        productRepository.deleteById(id);
        return ResponseEntity.ok("Product deleted successfully");
    }
}