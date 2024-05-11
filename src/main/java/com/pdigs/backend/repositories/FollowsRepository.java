package com.pdigs.backend.repositories;

import com.pdigs.backend.models.Follows;
import com.pdigs.backend.models.User;
import org.springframework.data.repository.CrudRepository;

public interface FollowsRepository extends CrudRepository<Follows, Long> {
    Iterable<Follows> getFollowsByFollower(User follower);
    Iterable<Follows> getFollowsByFollowed(User followed);
    Iterable<Follows> getFollowsByFollowerAndFollowed(User follower, User followed);

    Integer countByFollowed(User user);
    Integer countByFollower(User user);
    void deleteById(Long id);
    Boolean existsFollowsByFollowerAndAndFollowed(User follower, User followed);
}

