package com.pdigs.backend.controllers;

import com.pdigs.backend.models.Cart;
import com.pdigs.backend.models.ProductImage;
import com.pdigs.backend.repositories.FollowsRepository;
import com.pdigs.backend.repositories.ProductImageRepository;
import com.pdigs.backend.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/productImage")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductImageController {

    @Autowired
    private ProductImageRepository productImageRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private FollowsRepository followsRepository;

    @GetMapping
    public List<ProductImage> getAllProductImages(){return (List<ProductImage>) productImageRepository.findAll();}

    @GetMapping(params = "product_id", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Iterable<ProductImage> getProductImages(@RequestParam Long productId) {

        Iterable<ProductImage> productImages = productImageRepository.findAllByProductID(productId);
        return productImages;
    }

    @PostMapping
    public ResponseEntity<String> addImages(@RequestBody ProductImage productImage) {
        productImageRepository.save(productImage);
        return ResponseEntity.ok("Image added successfully");
    }
    @PutMapping(params = "product_id")
    public ResponseEntity<String> updateProductImage(@RequestBody Cart request, @RequestParam Integer product_id) {
        return ResponseEntity.ok("Image Updated successfully");
    }
    @DeleteMapping
    public ResponseEntity<String> deleteProductImage(@RequestBody ProductImage request) {
        productImageRepository.deleteById(request.getId());
        return ResponseEntity.ok("Product image deleted successfully from sopping cart");
    }
}



