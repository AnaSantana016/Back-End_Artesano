package com.pdigs.backend.controllers;


import com.pdigs.backend.models.Likes;
import com.pdigs.backend.repositories.LikesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/likes")
public class LikeController {

    @Autowired
    private LikesRepository likeRepository;

    @PostMapping("/newLike")
    public ResponseEntity<String> addLike(@RequestBody Likes like) {
        likeRepository.save(like);
        return ResponseEntity.ok("Like added successfully");
    }

    @DeleteMapping("/removeLike")
    public ResponseEntity<String> removeLike(@PathVariable Long id) {
        if (likeRepository.existsById(id)) {
            likeRepository.deleteById(id);
            return ResponseEntity.ok("Like removed successfully");
        } else {
            return ResponseEntity.badRequest().body("Like not found");
        }
    }
}