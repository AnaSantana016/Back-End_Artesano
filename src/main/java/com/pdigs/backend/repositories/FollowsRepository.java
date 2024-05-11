package com.pdigs.backend.repositories;

import com.pdigs.backend.models.Follows;
import org.springframework.data.repository.CrudRepository;

public interface FollowsRepository extends CrudRepository<Follows, Long> {
    Iterable<Follows> getFollowsByFollower(Integer followerId);
    Iterable<Follows> getFollowsByFollowed(Integer followedID);
    Integer countFollowers(Integer userId);
    Integer countFollows(Integer userId);
}

