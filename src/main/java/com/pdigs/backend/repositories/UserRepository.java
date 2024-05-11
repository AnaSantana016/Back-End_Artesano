package com.pdigs.backend.repositories;

import com.pdigs.backend.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findById(Long aLong);

    Iterable<User> findByName(String name);
    Iterable<User> findByEmail(String email);
    User findUserByEmail(String email);
    Iterable<User> findByAddress(String type);;
    void deleteById(Long aLong);

    // Method to get all followers of a user
    default List<User> getFollowers(User user) {
        return findByFollowed(user);
    }

    // Method to get all followings of a user
    default List<User> getFollowing(User user) {
        return findByFollower(user);
    }

    // Override the JPQL query to get all users who are following a specific user
    @Query("SELECT f.follower FROM Follows f WHERE f.followed = :user")
    List<User> findByFollowed(@Param("user") User user);

    // Override the JPQL query to get all users who are followed by a specific user
    @Query("SELECT f.followed FROM Follows f WHERE f.follower = :user")
    List<User> findByFollower(@Param("user") User user);
}
