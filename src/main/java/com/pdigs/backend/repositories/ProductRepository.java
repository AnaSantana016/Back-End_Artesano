package com.pdigs.backend.repositories;

import com.pdigs.backend.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

    /**
     * Find products by name (case-insensitive)
     *
     * @param name the product name to search for
     * @return a list of products matching the name
     */
    Iterable<Product> findByNameContainingIgnoreCase(String name);
}