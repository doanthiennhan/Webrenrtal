package com.example.webrented.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.webrented.Model.Listing;

public interface ListingRepository extends MongoRepository<Listing, String> {

}
