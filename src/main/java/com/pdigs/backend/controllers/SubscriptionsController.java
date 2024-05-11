package com.pdigs.backend.controllers;

import com.pdigs.backend.models.Subscriptions;
import com.pdigs.backend.repositories.SubscriptionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/subscriptions")
public class SubscriptionsController {

    @Autowired
    private SubscriptionsRepository subscriptionsRepository;

    @PostMapping
    public ResponseEntity<String> addSubscription(@RequestBody Subscriptions subscription) {
        subscriptionsRepository.save(subscription);
        return ResponseEntity.ok("Subscription added successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeSubscription(@PathVariable Long id) {
        if (subscriptionsRepository.existsById(id)) {
            subscriptionsRepository.deleteById(id);
            return ResponseEntity.ok("Subscription removed successfully");
        } else {
            return ResponseEntity.badRequest().body("Subscription not found");
        }
    }
}