package com.example.webrented.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.webrented.Model.Renter;

public interface RenterRepository extends MongoRepository<Renter, String> {

}
