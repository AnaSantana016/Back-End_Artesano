package com.pdigs.backend.repositories;


import com.pdigs.backend.models.Follows;
import com.pdigs.backend.models.Likes;
import org.springframework.data.repository.CrudRepository;

public interface LikesRepoository extends CrudRepository<Likes, Long> {
    Iterable<Likes> getLikesByProductLiked(Integer productId);
    Iterable<Follows> getLikesByUserWhoLiked(Integer userWhoLikedId);
    Integer countByProductLiked(Integer productId);
}

