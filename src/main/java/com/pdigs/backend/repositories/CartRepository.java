package com.pdigs.backend.repositories;

import com.pdigs.backend.models.Cart;
import com.pdigs.backend.models.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends CrudRepository<Cart, Long> {
    Iterable<Cart> findCartsWithProductsByUserId(Integer user_id);
}

