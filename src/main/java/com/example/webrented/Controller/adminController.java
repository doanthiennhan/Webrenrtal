package com.example.webrented.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.webrented.Model.Listing;

import com.example.webrented.repository.ListingRepository;




@Controller
public class adminController {

    private final ListingRepository listingRepository;

    public adminController(ListingRepository listingRepository)
    {
        this.listingRepository = listingRepository;
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        
        return "admin_trangchu";
    }

    @GetMapping("/admin_quanlibaiviet")
    public String admin_quanlibaiviet(Model model) {
        List<Listing> listings = listingRepository.findAll();
        model.addAttribute("listings", listings);
        return "admin_quanlibaiviet";
    }

    @GetMapping("/admin_quanlibaiviet_daxoa")
    public String admin_quanlibaiviet_daxoa(Model model) {
        List<Listing> listings = listingRepository.findAll();
        model.addAttribute("listings", listings);
        return "admin_quanlibaiviet_daxoa";
    }

    @GetMapping("/admin_quanlibaiviet_daduyet")
    public String admin_quanlibaiviet_daduyet(Model model) {
        List<Listing> listings = listingRepository.findAll();
        model.addAttribute("listings", listings);
        return "admin_quanlibaiviet_daduyet";
    }

}
