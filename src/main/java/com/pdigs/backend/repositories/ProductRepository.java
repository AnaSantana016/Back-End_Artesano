package com.pdigs.backend.repositories;

import com.pdigs.backend.models.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends CrudRepository<Product, Long> {

    Iterable<Product> findByName(String name);
    Iterable<Product> findBySellerId(Integer id);
    Iterable<Product> findBySize(String size);
    Iterable<Product> findByType(String type);
    Iterable<Product> findByColor(String color);
    Iterable<Product> findByTag(String tag);
    Iterable<Product> findAll(Sort sort);
}