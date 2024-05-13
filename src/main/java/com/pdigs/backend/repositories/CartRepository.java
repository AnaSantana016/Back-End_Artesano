package com.pdigs.backend.repositories;

import com.pdigs.backend.models.Cart;
import com.pdigs.backend.models.Product;
import com.pdigs.backend.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CartRepository extends CrudRepository<Cart, Long> {
    Iterable<Cart> findCartsWithProductsByUserId(Integer user_id);

    Optional<Cart> findByProductAndUser(Product product, User User);

    void deleteByProduct(Product product);
}

