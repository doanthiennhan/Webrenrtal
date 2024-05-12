package com.example.webrented.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.webrented.Model.Account;
import com.example.webrented.Model.Listing;
import com.example.webrented.repository.AccountRepository;
import com.example.webrented.repository.ListingRepository;
import com.example.webrented.service.AccountService;
import com.example.webrented.service.ListingService;

import org.springframework.ui.Model;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;

// import com.example.webrented.repository.ListingRepository;

@Controller
public class postController {
    // private final ListingRepository listingRepository;
    private final ListingService listingService;
    // private final AccountRepository accountRepository;
    private final AccountService accountService;



    public postController(ListingRepository listingRepository, ListingService listingService, AccountRepository accountRepository ,AccountService accountService) {

        // this.listingRepository = listingRepository;
        this.listingService = listingService;
        this.accountService = accountService;
        // this.accountRepository = accountRepository;
    }

    @GetMapping("/post")
    public String post(Model model, HttpSession session) {
        try {
            if (session.getAttribute("account") != null) {
                return "post";
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý ngoại lệ ở đây
        }
        return "redirect:/login";
    }

    @GetMapping("/postDetail")
    public String postDetail() {
        return "shop_product_detail";
    }

    @GetMapping("/postDetail/{id}")
    public String postDetail(@PathVariable("id") String id, Model model) {
    
        Listing listings = listingService.findById(id);
        System.out.println(listings.toString());
        if (listings != null) {
            String accountId = listings.getAccountId();
            System.out.println(accountId);
            Account accounts = accountService.findById(accountId);
            
            if (accounts != null) {
                // Đưa dữ liệu vào model
                model.addAttribute("listings", listings);
                model.addAttribute("accounts", accounts); 
                
                return "shop_product_detail";
            }
        }

        return "error"; 
    }
}
