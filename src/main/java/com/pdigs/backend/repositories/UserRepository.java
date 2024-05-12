package com.pdigs.backend.repositories;

import com.pdigs.backend.models.Product;
import com.pdigs.backend.models.User;
import org.springframework.data.domain.Sort;
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

    default List<User> getFollowers(User user) {
        return findByFollowed(user);
    }

    default List<User> getFollowing(User user) {
        return findByFollower(user);
    }
    default List<Product> getProductsLiked(User user) {
        return findByUserWhoLiked(user);
    }

    @Query("SELECT f.productLiked FROM Likes f WHERE f.userWhoLiked = :user")
    List<Product> findByUserWhoLiked(@Param("user") User user);

    @Query("SELECT f.follower FROM Follows f WHERE f.followed = :user")
    List<User> findByFollowed(@Param("user") User user);

    @Query("SELECT f.followed FROM Follows f WHERE f.follower = :user")
    List<User> findByFollower(@Param("user") User user);

    default List<User> getSubscribers(User user) {
        return findBySubscriber(user);
    }

    default List<User> getSubscribeds(User user) {
        return findBySubscribed(user);
    }

    @Query("SELECT f.subscriber FROM Subscriptions f WHERE f.subscribedTo = :user")
    List<User> findBySubscribed(@Param("user") User user);

    @Query("SELECT f.suscribedTo FROM Subscriptions f WHERE f.subscriber = :user")
    List<User> findBySubscriber(@Param("user") User user);

    default List<Product> getProducts(User user) {
        return findBySeller(user.getId());
    }

    @Query("SELECT f FROM Product f WHERE f.sellerId = :user")
    List<Product> findBySeller(@Param("user") Integer userId);


    Iterable<User> findAll(Sort sort);

    default Integer countByProduct(User user){
        return findBySeller(user.getId()).size();
    }
}
