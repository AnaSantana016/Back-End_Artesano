package com.pdigs.backend.controllers;

import com.pdigs.backend.models.User;
import com.pdigs.backend.repositories.FollowsRepository;
import com.pdigs.backend.repositories.LikesRepository;
import com.pdigs.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    private FollowsRepository followsRepository;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {
        userRepository.save(user);
        return ResponseEntity.ok("User created successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
        User user = userRepository.findUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) return ResponseEntity.ok("User logged in successfully");

        return ResponseEntity.ok("Email or password is incorrect");
    }

    @GetMapping
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    @PutMapping
    public ResponseEntity<String> updateUser(@RequestParam(value = "id") Long id, @RequestBody User user) {
        if (userRepository.existsById(id)) {
            userRepository.save(user);
            return ResponseEntity.ok("User updated successfully");
        } else {
            return ResponseEntity.badRequest().body("User not found");
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUser(@RequestParam(value = "id") Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.ok("User deleted successfully");
        } else {
            return ResponseEntity.badRequest().body("User not found");
        }
    }

    @GetMapping("/countFollowers")
    public ResponseEntity<Integer> countFollowers(@RequestParam(value = "id") Long id) {
        int followersCount = followsRepository.countByFollowed(userRepository.findById(id).orElse(null));
        return ResponseEntity.ok(followersCount);
    }
    @GetMapping("/countFolloweds")
    public ResponseEntity<Integer> countFolloweds(@RequestParam(value = "id") Long id) {
        int followedsCount = followsRepository.countByFollower(userRepository.findById(id).orElse(null));
        return ResponseEntity.ok(followedsCount);
    }


}