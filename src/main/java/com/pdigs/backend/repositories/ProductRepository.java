package com.pdigs.backend.repositories;

import com.pdigs.backend.models.Product;
import com.pdigs.backend.models.ProductImage;
import com.pdigs.backend.models.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Long> {

    Optional<Product> findById(Long aLong);

    Iterable<Product> findByName(String name);
    Iterable<Product> findBySize(String size);
    Iterable<Product> findByType(String type);
    Iterable<Product> findByColor(String color);
    Iterable<Product> findByTag(String tag);
    Iterable<Product> findAll(Sort sort);
    default List<User> getUsersWhoLiked(Product product) {
        return findByProductLiked(product);
    }
    /*
    @Query("SELECT f.userWhoLiked FROM Likes f WHERE f.productLiked = :product")
    List<Integer> findByProductLiked(@Param("product") Product product);
    */
    @Query("SELECT f.userWhoLiked FROM Likes f WHERE f.productLiked = :product")
    List<User> findByProductLiked(@Param("product") Product product);

    @Query("SELECT f.imagePath FROM ProductImage f WHERE f.product = :product")
    List<String> findImagesURLs(@Param("product") Product product);

    @Query("SELECT f FROM ProductImage f WHERE f.product = :product")
    List<ProductImage> findImages(@Param("product") Product product);
}