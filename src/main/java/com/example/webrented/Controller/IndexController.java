package com.example.webrented.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import com.example.webrented.Model.Listing;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import com.example.webrented.repository.ListingRepository;

@Controller
public class IndexController {
    private final ListingRepository listingRepository;

    public IndexController(ListingRepository listingRepository) {
        this.listingRepository = listingRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Listing> listings = listingRepository.findAll();
        model.addAttribute("listings", listings);
        return "index";
    }

    @GetMapping("/login")
    public String Login(Model model) {
        return "login";
    }

}
