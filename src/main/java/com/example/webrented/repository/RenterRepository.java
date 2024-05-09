package com.example.webrented.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.webrented.Model.Renter;
// import java.util.List;

public interface RenterRepository extends MongoRepository<Renter, String> {
    Renter findByAccountId(String accountId);
}
