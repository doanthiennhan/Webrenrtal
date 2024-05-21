package com.example.webrented.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.webrented.Model.User;
// import java.util.List;
import java.util.List;
// import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    List<User> findAll();

    User findByAccountId(String accountId);

    // Optional<Tenant> findById(String id);

}
