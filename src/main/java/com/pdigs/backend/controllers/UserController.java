package com.pdigs.backend.controllers;

import com.pdigs.backend.models.Follows;
import com.pdigs.backend.models.Product;
import com.pdigs.backend.models.Subscriptions;
import com.pdigs.backend.models.User;
import com.pdigs.backend.repositories.FollowsRepository;
import com.pdigs.backend.repositories.ProductRepository;
import com.pdigs.backend.repositories.SubscriptionsRepository;
import com.pdigs.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FollowsRepository followsRepository;
    @Autowired
    private SubscriptionsRepository subscriptionsRepository;
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/signup")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        System.out.println("user: "+ user);
        userRepository.save(user);
        return ResponseEntity.ok("User created successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
        User user = userRepository.findUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) return ResponseEntity.ok("User logged in successfully");

        return ResponseEntity.ok("Email or password is incorrect");
    }

    @GetMapping("/getUsers")
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    @PutMapping("/updateUser")
    public ResponseEntity<String> updateUser(@RequestParam(value = "id") Long id, @RequestBody User user) {
        if (userRepository.existsById(id)) {
            userRepository.save(user);
            return ResponseEntity.ok("User updated successfully");
        } else {
            return ResponseEntity.badRequest().body("User not found");
        }
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestParam(value = "id") Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.ok("User deleted successfully");
        } else {
            return ResponseEntity.badRequest().body("User not found");
        }
    }

    @GetMapping("/followers")
    public Iterable<User> countFollowers(@RequestParam(value = "id") Long id) {
        Optional<User> user = userRepository.findById(id);
        return userRepository.getFollowers(user.orElse(null));
    }
    @GetMapping("/following")
    public Iterable<User> countFollowing(@RequestParam(value = "id") Long id) {
        Optional<User> user = userRepository.findById(id);
        return userRepository.getFollowing(user.orElse(null));
    }

    @GetMapping("/countFollowers")
    public Integer countFollowersCount(@RequestParam(value = "id") Long id) {
        Optional<User> user = userRepository.findById(id);
        return userRepository.getFollowers(user.orElse(null)).size();
    }
    @GetMapping("/countFollowing")
    public Integer countFollowingCount(@RequestParam(value = "id") Long id) {
        Optional<User> user = userRepository.findById(id);
        return userRepository.getFollowing(user.orElse(null)).size();
    }

    @GetMapping("/getSubscribedsTo")
    public ResponseEntity<List<User>> getSubscribedsTo(@RequestParam(value = "id") Long id) {
        Iterable<Subscriptions> subscribedsTo = subscriptionsRepository.getSubscriptionsBySubscribedTo(userRepository.findById(id).orElse(null));
        List<User> subscribeds = new ArrayList<>();
        for(Subscriptions subscriber : subscribedsTo){
            subscribeds.add(subscriber.getSubscriber());
        }
        return ResponseEntity.ok(subscribeds);
    }

    @GetMapping("/getSuscribers")
    public ResponseEntity<List<User>> getSubscribers(@RequestParam(value = "id") Long id) {
        Iterable<Subscriptions> subscribers = subscriptionsRepository.getSubscriptionsBySuscriber(userRepository.findById(id).orElse(null));
        List<User> subscribersTo = new ArrayList<>();
        for(Subscriptions subscriber : subscribers){
            subscribersTo.add(subscriber.getSubscriber());
        }
        return ResponseEntity.ok(subscribersTo);
    }

    @GetMapping("/getProducts")
    public ResponseEntity<List<Integer>> getProducts(@RequestParam(value = "id") Integer id) {
        Iterable<Product> products = productRepository.findAllBySellerId(id);
        List<Integer> productsList = new ArrayList<>();
        for(Product product : products){
            productsList.add(product.getId());
        }
        return ResponseEntity.ok(productsList);
    }
}