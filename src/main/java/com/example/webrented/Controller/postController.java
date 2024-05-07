package com.example.webrented.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// import com.example.webrented.repository.ListingRepository;

@Controller
public class postController {
    // private ListingRepository listingRepository;

    @GetMapping("/post")
    public String post() {
        return "post";
    }
}
