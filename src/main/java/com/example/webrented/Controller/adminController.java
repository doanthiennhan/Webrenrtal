package com.example.webrented.Controller;

import java.util.HashMap;
import java.util.List;

import com.example.webrented.service.AccountService;
import com.example.webrented.service.ListingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;




import com.example.webrented.Model.Listing;


import com.example.webrented.Model.Account;

import com.example.webrented.repository.AccountRepository;
import com.example.webrented.repository.ListingRepository;

@Controller
public class adminController {

    private final ListingRepository listingRepository;
    private final ListingService listingService;
    private final AccountRepository accountRepository;
    private final AccountService accountService;



    public adminController(ListingRepository listingRepository, ListingService listingService, AccountRepository accountRepository ,AccountService accountService) {

        this.listingRepository = listingRepository;
        this.listingService = listingService;
        this.accountService = accountService;
        this.accountRepository = accountRepository;
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

    @GetMapping("/admin_quanlitaikhoan")
    public String admin_quanlitk(Model model) {
        List<Account> accounts = accountRepository.findAll();
        HashMap<Account, Integer> idMap = new HashMap<Account, Integer>();
        for (Account account : accounts) {
            idMap.put(account, listingService.accountCount(account.getId()));
        }
        model.addAttribute("accounts", idMap);
        return "qltk.html";
    }

    @PostMapping("/admin_quanlitaikhoan")
    public String quanlibaiviet(@RequestParam("id") String id, @RequestParam("action") String action) {
        if ("cam".equals(action)) {
            accountService.updateAccount(id, "cấm");
        } else if ("xoa".equals(action)) {
            accountService.xoaAcount(id);
        }
        return "redirect:/admin_quanlitaikhoan";
    }

}
