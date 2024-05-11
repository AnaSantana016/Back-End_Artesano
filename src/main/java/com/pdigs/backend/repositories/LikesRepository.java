package com.pdigs.backend.repositories;


import com.pdigs.backend.models.Likes;
import com.pdigs.backend.models.Product;
import com.pdigs.backend.models.User;
import org.springframework.data.repository.CrudRepository;

public interface LikesRepository extends CrudRepository<Likes, Long> {
    Iterable<Product> getLikesByProductLiked(Product product);
    Iterable<User> getLikesByUserWhoLiked(User user);
    Long countByProductLiked(Product product);
    void deleteById(Long id);
}

