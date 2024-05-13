package com.pdigs.backend.repositories;

import com.pdigs.backend.models.Cart;
import com.pdigs.backend.models.Product;
import com.pdigs.backend.models.ProductImage;
import com.pdigs.backend.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductImageRepository extends CrudRepository<ProductImage, Long> {
    @Query("SELECT f FROM ProductImage f WHERE f.product = :productId")
    Iterable<ProductImage> findAllByProductID(Long productId);

}

