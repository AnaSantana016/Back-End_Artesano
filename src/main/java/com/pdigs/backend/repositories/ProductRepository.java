package com.pdigs.backend.repositories;

import com.pdigs.backend.models.Product;
import com.pdigs.backend.models.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    Iterable<Product> findByName(String name);
    Iterable<Product> findBySellerId(Integer id);
    Iterable<Product> findBySize(String size);
    Iterable<Product> findByType(String type);
    Iterable<Product> findByColor(String color);
    Iterable<Product> findByTag(String tag);
    Iterable<Product> findAll(Sort sort);
    Iterable<Product> findAllBySellerId(Integer id);
    default List<User> getUsersWhoLiked(Product product) {
        return findByProductLiked(product);
    }
    @Query("SELECT f.userWhoLiked FROM Likes f WHERE f.productLiked = :product")
    List<User> findByProductLiked(@Param("product") Product product);
}