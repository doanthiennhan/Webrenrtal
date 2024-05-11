package com.example.webrented.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.webrented.Model.Tenant;
// import java.util.List;
import java.util.List;
// import java.util.Optional;

public interface TennantRepository extends MongoRepository<Tenant, String> {
    List<Tenant> findAll();

    Tenant findByAccountId(String accountId);

    // Optional<Tenant> findById(String id);

}
