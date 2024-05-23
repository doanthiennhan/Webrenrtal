package com.example.webrented.Controller;

import org.springframework.stereotype.Controller;

import com.example.webrented.repository.BookingRepository;

import ch.qos.logback.core.model.Model;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class bookRoomController {
    private final BookingRepository bookingRepository;

    public bookRoomController(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @PostMapping("/bookroom/{id}")
    public String postBooking(@PathVariable("id") String id, Model model) {

        return "redirect:/postDetail    /{id}";
    }

}
