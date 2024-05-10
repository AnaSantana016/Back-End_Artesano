package com.pdigs.backend.repositories;

import com.pdigs.backend.models.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, Long> {
    Iterable<Cart> findCartsWithProductsByUserId(Integer user_id);
}

