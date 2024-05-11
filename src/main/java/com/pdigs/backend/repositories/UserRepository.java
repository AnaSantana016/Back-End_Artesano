package com.pdigs.backend.repositories;

import com.pdigs.backend.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findById(Long aLong);

    Iterable<User> findByName(String name);
    Iterable<User> findByEmail(String email);
    User findUserByEmail(String email);
    Iterable<User> findByAddress(String type);;
    void deleteById(Long aLong);
}
