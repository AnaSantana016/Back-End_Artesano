package com.pdigs.backend.repositories;

import com.pdigs.backend.models.Follows;
import com.pdigs.backend.models.Product;
import com.pdigs.backend.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FollowsRepository extends CrudRepository<Follows, Long> {
    void deleteById(Long id);
    Boolean existsFollowsByFollowerAndAndFollowed(User follower, User followed);

    void deleteByFollowerAndFollowed(User follower, User followed);

    @Query("SELECT f FROM Follows f")
    List<Follows> getAllFollows();
}

