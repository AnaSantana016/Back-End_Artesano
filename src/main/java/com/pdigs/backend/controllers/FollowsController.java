package com.pdigs.backend.controllers;

import com.pdigs.backend.models.Follows;
import com.pdigs.backend.models.User;
import com.pdigs.backend.repositories.FollowsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    /*@DeleteMapping
    public ResponseEntity<String> removeFollow(@PathVariable Long id) {
        if (followsRepository.existsById(id)) {
            followsRepository.deleteById(id);
            return ResponseEntity.ok("Follow removed successfully");
        } else {
            return ResponseEntity.badRequest().body("Follow not found");
        }
    }*/

    @DeleteMapping
    @Transactional
    public ResponseEntity<String> removeFollow(@RequestBody Follows follows) {
        followsRepository.deleteByFollowerAndFollowed(follows.getFollower(), follows.getFollowed());
        return ResponseEntity.ok("Follow removed successfully");
    }
    @GetMapping("/isFollowedBy")
    public ResponseEntity<Boolean> isFollowedBy(@RequestParam(value = "follower") User follower,
                                                @RequestParam(value = "followed") User followed) {
        boolean isFollowedBy = followsRepository.existsFollowsByFollowerAndAndFollowed(follower, followed);
        return ResponseEntity.ok(isFollowedBy);
    }
    @GetMapping("/getAllFollows")
    public ResponseEntity<List<Follows>> getAllFollows(){
        return ResponseEntity.ok(followsRepository.getAllFollows());
    }
}