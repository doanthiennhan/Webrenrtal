package com.example.webrented.repository;

import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.webrented.Model.Listing;

@Transactional
public interface ListingRepository extends MongoRepository<Listing, String> {


    List<Listing> findAll();


    List<Listing> findByAvailableFalse();

    List<Listing> findByAvailableTrue();

    List<Listing> findByAvailableNull();

    List<Listing> findByAvailable(String available);

    @Query("UPDATE Listing l SET l.available = true WHERE l.id = :id")
    void duyetBaiViet(@Param("id") String id);

    

}
