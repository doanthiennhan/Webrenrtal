package com.example.webrented.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.webrented.Model.Listing;

public interface ListingRepository extends MongoRepository<Listing, String> {
    
    List <Listing> findAll();
        
}
