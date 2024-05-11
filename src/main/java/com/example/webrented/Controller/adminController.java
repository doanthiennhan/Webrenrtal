package com.example.webrented.Controller;

import java.util.List;
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
import com.example.webrented.service.ListingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
<<<<<<< Updated upstream
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
=======

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

>>>>>>> Stashed changes
import com.example.webrented.Model.Listing;

import com.example.webrented.repository.ListingRepository;

<<<<<<< Updated upstream



=======
>>>>>>> Stashed changes
@Controller
public class adminController {

    private final ListingRepository listingRepository;
    private final ListingService listingService;

    public adminController(ListingRepository listingRepository, ListingService listingService) {
        this.listingRepository = listingRepository;
        this.listingService = listingService;
    }

    @GetMapping("/admin")
    public String admin(Model model) {

        return "admin_trangchu.html";
    }

    @GetMapping("/admin_quanlibaiviet")
    public String admin_quanlibaiviet(Model model) {
        List<Listing> listings = listingRepository.findByAvailableNull();
        model.addAttribute("listings", listings);
        return "admin_quanlibaiviet.html";
    }

    @GetMapping("/admin_quanlibaiviet_daxoa")
    public String admin_quanlibaiviet_daxoa(Model model) {
        List<Listing> listings = listingRepository.findByAvailableFalse();
        model.addAttribute("listings", listings);
        return "admin_quanlibaiviet_daxoa.html";
    }

    @GetMapping("/admin_quanlibaiviet_daduyet")
    public String admin_quanlibaiviet_daduyet(Model model) {
        List<Listing> listings = listingRepository.findByAvailableTrue();
        model.addAttribute("listings", listings);
        return "admin_quanlibaiviet_daduyet.html";
    }

    @PostMapping("/admin_quanlibaiviet")
    public String handleAction(@RequestParam("id") String id, @RequestParam("action") String action) {
        if ("duyet".equals(action)) {
            // Nếu hành động là "duyet", thực hiện duyệt
            listingService.updateListingAvailability(id, true);
        } else if ("xoa".equals(action)) {
            // Nếu hành động là "xoa", thực hiện xóa

        }
        return "redirect:/admin_quanlibaiviet";
    }

    @PostMapping("/admin_quanlibaiviet_daduyet")
    public String updateListingAvailability(@RequestParam("id") String id) {
        // Đảm bảo phương thức này được gọi khi form được submit
        listingService.updateListingAvailability(id, false);
        // Thực hiện các thao tác cần thiết
        return "redirect:/admin_quanlibaiviet_daduyet";
    }
}
