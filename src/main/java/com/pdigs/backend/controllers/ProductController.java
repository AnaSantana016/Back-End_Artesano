package com.pdigs.backend.controllers;

import com.pdigs.backend.models.Cart;
import com.pdigs.backend.models.Product;
import com.pdigs.backend.models.ProductImage;
import com.pdigs.backend.models.User;
import com.pdigs.backend.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        productRepository.save(product);
        return ResponseEntity.ok("Product updated successfully");
    }

    @GetMapping("/getProducts")
    public Iterable<Product> getProducts(
            @RequestParam(value = "filterField", required = false) String filterField,
            @RequestParam(value = "filterValue", required = false) String filterValue,
            @RequestParam(value = "sortOrder", required = false) String sortOrder) {

        Sort sort = sortOrder == null || sortOrder.isEmpty() || sortOrder.equals("asc")
                ? Sort.by(Sort.Direction.ASC, filterField != null && !filterField.isEmpty() ? filterField : "id")
                : Sort.by(Sort.Direction.DESC, filterField != null && !filterField.isEmpty() ? filterField : "id");

        Map<String, Function<String, Iterable<Product>>> filterMethods = new HashMap<>();
        filterMethods.put("name", productRepository::findByName);
        filterMethods.put("size", productRepository::findBySize);
        filterMethods.put("type", productRepository::findByType);
        filterMethods.put("color", productRepository::findByColor);
        filterMethods.put("tag", productRepository::findByTag);

        if (filterValue != null && !filterValue.isEmpty()) {
            Function<String, Iterable<Product>> filterMethod = filterMethods.get(filterField);
            if (filterMethod != null) {
                return filterMethod.apply(filterValue);
            } else {
                return new ArrayList<>();
            }
        }
        return productRepository.findAll(sort);
    }

    @PutMapping
    public ResponseEntity<String> updateProduct(@RequestParam (value = "id") Long id, @RequestBody Product product) {
        productRepository.save(product);
        return ResponseEntity.ok("Product Edited successfully");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProduct(@RequestParam (value = "id") Long id) {
        productRepository.deleteById(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

    @GetMapping("/getImages")
    public ResponseEntity<List<String>> getImages(@RequestParam(value = "id") Long id){
        return ResponseEntity.ok(productRepository.findImagesURLs(productRepository.findById(id).orElse(null)));
    }
    @GetMapping("/getUsersWhoLiked")
    public ResponseEntity<List<User>> getUsersWhoLiked(@RequestParam(value = "id") Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            List<User> usersWhoLiked = productRepository.findByProductLiked(product);
            return ResponseEntity.ok(usersWhoLiked);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}