package com.pdigs.backend.repositories;

import com.pdigs.backend.models.Follows;
import com.pdigs.backend.models.User;
import org.springframework.data.repository.CrudRepository;

public interface FollowsRepository extends CrudRepository<Follows, Long> {

    Iterable<User> findByFollowed_Id(Integer followed_id);
    Iterable<User> findByFollower_Id(Integer followers_Id);

    void deleteById(Long id);
    Boolean existsFollowsByFollowerAndAndFollowed(User follower, User followed);
}

