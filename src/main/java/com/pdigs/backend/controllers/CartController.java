package com.pdigs.backend.controllers;

import com.pdigs.backend.models.Cart;
import com.pdigs.backend.models.Product;
import com.pdigs.backend.repositories.CartRepository;
import com.pdigs.backend.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/cart")
public class CartController {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Cart> getAllCarts(){return (List<Cart>) cartRepository.findAll();}

    @GetMapping(params = "user_id", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Iterable<CartProduct> getCart(@RequestParam Integer user_id) {

        Iterable<Cart> carts = cartRepository.findCartsWithProductsByUserId(user_id);
        List<CartProduct> cartProducts = new ArrayList<>();

        for (Cart cart : carts) {
            cartProducts.add(new CartProduct(cart.getProduct(), cart.getAmount()));
        }
        return cartProducts;
    }

    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody Cart request) {
//        cartRepository.save(request);
        Cart cart = request;
        cartRepository.save(cart);
        return ResponseEntity.ok("Product added successfully to the sopping cart");
    }
}


