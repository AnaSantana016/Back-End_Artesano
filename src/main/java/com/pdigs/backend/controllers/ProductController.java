package com.pdigs.backend.controllers;
import com.pdigs.backend.models.Product;
import com.pdigs.backend.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Fetch all products
     *
     * @return a list of products
     */
    @GetMapping
    public @ResponseBody Iterable<Product> getProducts() {
        return productRepository.findAll();
    }

    /**
     * Fetch products by name
     *
     * @param name the product name to search for
     * @return a list of products matching the name
     */
    @GetMapping(params = "name")
    public @ResponseBody Iterable<Product> getProductsByName(@RequestParam String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }
}