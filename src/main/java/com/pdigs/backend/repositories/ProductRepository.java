package com.pdigs.backend.repositories;

import com.pdigs.backend.models.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

    Iterable<Product> findByNameContainingIgnoreCase(String name);
    Iterable<Product> findAll(Sort sort);
}