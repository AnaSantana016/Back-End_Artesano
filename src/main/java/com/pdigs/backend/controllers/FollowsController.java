package com.pdigs.backend.controllers;

import com.pdigs.backend.models.Follows;
import com.pdigs.backend.repositories.FollowsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/follows")
public class FollowsController {

    @Autowired
    private FollowsRepository followsRepository;

    public FollowsController(FollowsRepository followsRepository) {
        this.followsRepository = followsRepository;
    }

    @PostMapping
    public ResponseEntity<String> addFollow(@RequestBody Follows follows) {
        followsRepository.save(follows);
        return ResponseEntity.ok("Follow added successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeFollow(@PathVariable Long id) {
        if (followsRepository.existsById(id)) {
            followsRepository.deleteById(id);
            return ResponseEntity.ok("Follow removed successfully");
        } else {
            return ResponseEntity.badRequest().body("Follow not found");
        }
    }
}