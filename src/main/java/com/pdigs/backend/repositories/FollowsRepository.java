package com.pdigs.backend.repositories;

import com.pdigs.backend.models.Follows;
import com.pdigs.backend.models.User;
import org.springframework.data.repository.CrudRepository;

public interface FollowsRepository extends CrudRepository<Follows, Long> {
    Iterable<Follows> getFollowsByFollower(User follower);
    Iterable<Follows> getFollowsByFollowed(User followed);
    Iterable<Follows> getFollowsByFollowerAndFollowed(User follower, User followed);

    void deleteById(Long id);
    Boolean existsFollowsByFollowerAndAndFollowed(User follower, User followed);
}

