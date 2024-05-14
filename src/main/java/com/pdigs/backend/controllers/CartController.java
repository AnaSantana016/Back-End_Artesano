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
        Optional<Cart> existingCart = cartRepository.findByProductAndUser(request.getProduct(), request.getUser());

        Iterable<Cart> existingcarts = cartRepository.findCartsWithProductsByUserId(request.getUser().getId());

        if (existingcarts != null){
            for (Cart cart : existingcarts) {
                if (cart.getProduct().getId().equals(request.getProduct().getId())
                        && cart.getProduct().getTag().equals(request.getProduct().getTag())
                        && cart.getProduct().getSize().equals(request.getProduct().getSize())
                        && cart.getProduct().getType().equals(request.getProduct().getType())
                        && cart.getProduct().getColor().equals(request.getProduct().getColor())) {
                    cart.setAmount(cart.getAmount() + request.getAmount());
                    cartRepository.save(cart);
                    return ResponseEntity.ok("Product updated successfully the sopping cart");
                }
            }
        }
        cartRepository.save(request);
        return ResponseEntity.ok("Product added successfully to the sopping cart");
    }
    @PutMapping
    public ResponseEntity<String> updateProductAmount(@RequestBody Cart request, @RequestParam Integer product_id) {
        if (request.getAmount() == 0){
            deleteProductFromCart(request.getProduct().getId(), product_id);
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

    @DeleteMapping
    public ResponseEntity<String> deleteProductFromCart(@RequestBody Integer userId, @RequestParam Integer productId) {
        Iterable<Cart> userCarts= cartRepository.findCartsWithProductsByUserId(userId);

        for (Cart cart : userCarts){
            if (cart.getProduct().getId().equals(productId)){
                cartRepository.delete(cart);
            }
        }

        /*for (Cart cart : userCarts){
            if (cart.getProduct().getId().equals(request.getProduct().getId())
                    && cart.getProduct().getTag().equals(request.getProduct().getTag())
                    && cart.getProduct().getSize().equals(request.getProduct().getSize())
                    && cart.getProduct().getType().equals(request.getProduct().getType())
                    && cart.getProduct().getColor().equals(request.getProduct().getColor())){
                cartRepository.deleteByProduct(cart.getProduct());
            }
        }*/
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



