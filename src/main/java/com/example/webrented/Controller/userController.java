package com.example.webrented.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestParam;

import com.example.webrented.Model.Account;
import com.example.webrented.Model.Listing;
import com.example.webrented.service.AccountService;
import com.example.webrented.service.ListingService;

@Controller
public class userController {
    private ListingService listingService;
    private AccountService accountService;

    public userController(ListingService listingService, AccountService accountService) {
        this.listingService = listingService;
        this.accountService = accountService;
    }

    @GetMapping("/userProfile")
    public String getMethodName() {
        return "userProfile";
    }

    @GetMapping("/account")
    public String getAcount() {
        return "account";
    }

    @GetMapping("/userProfile/{id}")
    public String getMethodName1(@PathVariable("id") String id, Model model) {
        List<Listing> lists = listingService.findAllbaivietdaduyetByAccountId(id);
        Account tam = accountService.findById(id);
        String name = tam.getName();
        model.addAttribute("lists", lists);
        model.addAttribute("name", name);
        return "userProfile";
    }

}
