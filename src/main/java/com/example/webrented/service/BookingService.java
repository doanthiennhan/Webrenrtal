package com.example.webrented.service;

import org.springframework.stereotype.Service;

import com.example.webrented.Model.Booking;
import com.example.webrented.repository.BookingRepository;

@Service
public class BookingService {
    final private BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public void addBooking(Booking booking) {
        bookingRepository.save(booking);
    }

}
