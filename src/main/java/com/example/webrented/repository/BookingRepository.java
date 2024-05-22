package com.example.webrented.repository;


import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.webrented.Model.Booking;


public interface BookingRepository extends MongoRepository<Booking, String> {
    List<Booking> findAll();
}
