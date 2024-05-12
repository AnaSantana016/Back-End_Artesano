package com.pdigs.backend.controllers;

import com.pdigs.backend.models.Cart;
import com.pdigs.backend.models.User;
import com.pdigs.backend.repositories.CartRepository;
import com.pdigs.backend.repositories.FollowsRepository;
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
@CrossOrigin(origins = "http://localhost:4200")
public class CartController {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private FollowsRepository followsRepository;

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
        /*Optional<Cart> existingCart = cartRepository.findByProductAndUser(request.getProduct(), request.getUser());


        if (existingCart.isPresent()) {
            Cart cart = existingCart.get();
            cart.setAmount(request.getAmount());
            cartRepository.save(cart);
            return ResponseEntity.ok("Product amount updated successfully");
        } else {
            cartRepository.save(request);
            return ResponseEntity.ok("Product added successfully");
        }*/
        Cart cart = cartRepository.findByProductAndUser(request.getProduct(),request.getUser()).orElse(null);
        if (cart!=null){
            cart.setAmount(cart.getAmount() + request.getAmount());
            return ResponseEntity.ok("Product amount updated successfully");
        }else {
            cartRepository.save(request);
            return ResponseEntity.ok("Product added successfully");
        }
    }
    @PutMapping(params = "product_id")
    public ResponseEntity<String> updateProductAmount(@RequestBody Cart request, @RequestParam Integer product_id) {
        if (request.getAmount() == 0){
            deleteProductFromCart(request, product_id);
            return ResponseEntity.ok("Product updated successfully to the sopping cart");
        }
        Iterable<Cart> userCarts= cartRepository.findCartsWithProductsByUserId(request.getUser().getId());

        for (Cart cart : userCarts){
            if (cart.getProduct().getId().equals(product_id)){
                cart.setAmount(request.getAmount());
                cartRepository.save(cart);
            }
        }
        return ResponseEntity.ok("Product updated successfully in sopping cart");
    }

    @DeleteMapping(params = "product_id")
    public ResponseEntity<String> deleteProductFromCart(@RequestBody Cart request, @RequestParam Integer product_id) {
        Iterable<Cart> userCarts= cartRepository.findCartsWithProductsByUserId(request.getUser().getId());

        for (Cart cart : userCarts){
            if (cart.getProduct().getId().equals(product_id)){
                cartRepository.delete(cart);
            }
        }
        return ResponseEntity.ok("Product deleted successfully from sopping cart");
    }
    @GetMapping("/getTotalAmount")
    public ResponseEntity<Integer> getTotalAmount (User user){
        Iterable<Cart> productsByUserId= cartRepository.findCartsWithProductsByUserId(user.getId());
        Integer totalAmount=0;
        for(Cart product : productsByUserId){
            totalAmount = (int) (product.getAmount() * product.getProduct().getPrice());
        }
        return ResponseEntity.ok(totalAmount);
    }
}



