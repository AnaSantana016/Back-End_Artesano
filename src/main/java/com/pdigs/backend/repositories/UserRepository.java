package com.pdigs.backend.repositories;

import com.pdigs.backend.models.Product;
import com.pdigs.backend.models.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends CrudRepository<User, Long> {
    Iterable<User> findByName(String name);
    Iterable<User> findByEmail(String email);
    Iterable<User> findByAddress(String type);;
    void deleteById(Long aLong);
}
