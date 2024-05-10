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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Iterable<Product> getProducts() {
        return productRepository.findAll();
    }

    @GetMapping
    public Iterable<Product> getProducts(@RequestParam(value = "filterBy", required = false) String filterBy,
                                      @RequestParam(value = "order", required = false) String order) {

        Sort sort;

        if (order == null || order.isEmpty() || order.equals("asc")){
            sort = Sort.by(Sort.Direction.ASC, filterBy);
        }else if (order.equals("desc")){
            sort = Sort.by(Sort.Direction.DESC, filterBy);
        } else {
            return new ArrayList<>();
        }
        return productRepository.findAll(sort);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product existingProduct = productRepository.findById(id).orElseThrow();
        BeanUtils.copyProperties(product, existingProduct, "id");
        Product updatedProduct = productRepository.save(existingProduct);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return ResponseEntity.ok("Product deleted successfully");
    }
}