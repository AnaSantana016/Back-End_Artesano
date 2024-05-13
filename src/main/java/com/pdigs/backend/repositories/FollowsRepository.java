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
    Iterable<Follows> getFollowsByFollower(User follower);
    Iterable<Follows> getFollowsByFollowed(User followed);
    Iterable<Follows> getFollowsByFollowerAndFollowed(User follower, User followed);
    Integer countByFollowed(User user);
    Integer countByFollower(User user);
    void deleteById(Long id);
    Boolean existsFollowsByFollowerAndAndFollowed(User follower, User followed);

    @Query("SELECT f FROM Follows f")
    List<Follows> getAllFollows();
}

