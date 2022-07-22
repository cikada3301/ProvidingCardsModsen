package com.practice.vpalagin.project.repository;

import com.practice.vpalagin.project.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> getByUserName(String username);
}
